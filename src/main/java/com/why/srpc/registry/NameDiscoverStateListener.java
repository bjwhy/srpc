package com.why.srpc.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.stereotype.Component;

@Component
public class NameDiscoverStateListener implements ConnectionStateListener {

	public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
		if (connectionState == ConnectionState.LOST) {
			while (true) {
				try {
					curatorFramework.getZookeeperClient().blockUntilConnectedOrTimedOut();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
