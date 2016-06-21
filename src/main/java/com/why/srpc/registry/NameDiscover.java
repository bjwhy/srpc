package com.why.srpc.registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.why.srpc.config.ZkConfig;
import com.why.srpc.core.Request;
import com.why.srpc.tcp.NettyClient;

public class NameDiscover {

	@Autowired
	private ZkConfig config;

	@Autowired
	private NameDiscoverStateListener ndListener;

	private CuratorFramework client;

	private static Map<String, ClientGroup> servers = new HashMap<String, ClientGroup>();

	private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

	private static final Logger logger = LoggerFactory.getLogger(NameDiscover.class);

	public void connectZookeeper() throws Exception {
		// TODO Auto-generated method stub
		client = CuratorFrameworkFactory.builder().connectString(config.getServer())
				.sessionTimeoutMs(config.getSessionTimeoutMs()).connectionTimeoutMs(config.getConnectionTimeoutMs())
				.retryPolicy(new ExponentialBackoffRetry(2000, 3)).build();
		client.getConnectionStateListenable().addListener(ndListener);
		client.start();
	}

	public void close() {
		client.close();
	}

	public void initServers() throws Exception {

		String[] nodeNames = config.getDiscover().split(",");

		for (String nodeName : nodeNames) {

			List<String> ips = client.getChildren().watched().forPath(nodeName);

			if (ips != null) {
				logger.info("watch success {}", nodeName);

				if (!servers.containsKey(nodeName)) {
					ClientGroup group = new ClientGroup();
					servers.put(nodeName, group);
				}

				for (String ip : ips) {
					NettyClient ncli = new NettyClient();
					String[] info = ip.split(":");
					ncli.tcpConnect(info[0], Integer.valueOf(info[1]));
					servers.get(nodeName).add(ncli);
				}
			}

			@SuppressWarnings("resource")
			PathChildrenCache childrenCache = new PathChildrenCache(client, nodeName, true);
			childrenCache.start(StartMode.BUILD_INITIAL_CACHE);

			childrenCache.getListenable().addListener(new PathChildrenCacheListener() {

				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					switch (event.getType()) {
					case CHILD_ADDED:
						String value = event.getData().getPath();
						int index = value.lastIndexOf("/");
						NettyClient ncli = new NettyClient();

						ncli.tcpConnect(value.substring(index + 1, value.length()), 9999);
						servers.get(value.substring(0, index)).add(ncli);
						System.out.println("CHILD_ADDED: " + value);
						break;
					case CHILD_REMOVED:
						value = event.getData().getPath();
						index = value.lastIndexOf("/");
						List<NettyClient> clis = servers.get(value.substring(0, index)).getServers();
						String ip = value.substring(index + 1, value.length());
						for (NettyClient cli : clis) {
							if (cli.serverAdress.equals(ip)) {
								cli.stop();
								clis.remove(cli);
								break;
							}
						}
						System.out.println("CHILD_REMOVED: " + value);
						break;
					case CHILD_UPDATED:
						break;
					default:
						break;
					}
				}
			}, pool);
		}
	}

	public void sendMsg(String nodeName, Request msg) {
		servers.get(nodeName).send(msg);
	}
}
