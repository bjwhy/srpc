package com.why.srpc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZkConfig {
	@Value("${srpc.zk.server}")
	private String server;

	@Value("${srpc.zk.sessionTimeoutMs}")
	private int sessionTimeoutMs;

	@Value("${srpc.zk.connectionTimeoutMs}")
	private int connectionTimeoutMs;

	@Value("${srpc.zk.register}")
	private String register;

	@Value("${srpc.zk.discover}")
	private String discover;

	public String getServer() {
		return server;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public String getRegister() {
		return register;
	}

	public String getDiscover() {
		return discover;
	}

}
