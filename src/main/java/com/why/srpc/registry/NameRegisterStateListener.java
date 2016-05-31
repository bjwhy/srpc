package com.why.srpc.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.why.srpc.config.ZkConfig;

@Component
public class NameRegisterStateListener implements ConnectionStateListener {

	@Autowired
	private ZkConfig config;

	public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
		if (connectionState == ConnectionState.LOST) {
			String regisInfo = config.getRegister();
			String[] nodes = regisInfo.split(" ");
			String nodeName = nodes[0] + "/" + nodes[1];
			while (true) {
				try {
					if (curatorFramework.getZookeeperClient().blockUntilConnectedOrTimedOut()) {
						curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
								.forPath(nodeName);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
