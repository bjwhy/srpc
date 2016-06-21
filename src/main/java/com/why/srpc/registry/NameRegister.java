package com.why.srpc.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.why.srpc.config.ZkConfig;
import com.why.srpc.utils.NetUtils;

@Component
public class NameRegister {

	@Autowired
	private ZkConfig config;

	@Autowired
	private NameRegisterStateListener nrListener;

	private CuratorFramework client;
	private static final Logger logger = LoggerFactory.getLogger(NameRegister.class);

	public void connectZookeeper() throws Exception {
		// TODO Auto-generated method stub
		client = CuratorFrameworkFactory.builder().connectString(config.getServer())
				.sessionTimeoutMs(config.getSessionTimeoutMs()).connectionTimeoutMs(config.getConnectionTimeoutMs())
				.retryPolicy(new ExponentialBackoffRetry(2000, 3)).build();
		client.getConnectionStateListenable().addListener(nrListener);
		client.start();
	}

	public void close() {
		client.close();
	}

	public void autoCreateNode() throws Exception {
		String regisInfo = config.getRegister();
		String[] nodes = regisInfo.split(" ");
		createNodePersistent(nodes[0]);
		createNodeEphemeral(nodes[0] + "/" + NetUtils.getLocalAddress().getHostAddress() + ":" + nodes[1]);
	}

	private void createNodePersistent(String nodeName) throws Exception {
		Stat stat = client.checkExists().forPath(nodeName);
		if (stat == null) {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(nodeName);
			logger.info("{} register success", nodeName);
		}
	}

	private void createNodeEphemeral(String nodeName) throws Exception {
		Stat stat = client.checkExists().forPath(nodeName);
		if (stat == null) {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(nodeName);
			logger.info("{} register success", nodeName);
		}
	}
}
