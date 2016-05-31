package com.why.srpc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig {

	@Value("${srpc.tcp.port}")
	private int port;

	private int bossThreads = 1;
	private int workerThreads = Runtime.getRuntime().availableProcessors();
	private int userThreads = Runtime.getRuntime().availableProcessors() << 3;

	@Value("${srpc.tcp.socketTimeoutMills}")
	private int socketTimeoutMills;

	@Value("${srpc.tcp.shutdownTimeoutMills}")
	private int shutdownTimeoutMills;

	@Value("${srpc.tcp.sendBuff}")
	private int sendBuff;

	@Value("${srpc.tcp.recvBuff}")
	private int recvBuff;

	public int getPort() {
		return port;
	}

	public int getBossThreads() {
		return bossThreads;
	}

	public int getWorkerThreads() {
		return workerThreads;
	}

	public int getUserThreads() {
		return userThreads;
	}

	public int getSocketTimeoutMills() {
		return socketTimeoutMills;
	}

	public int getShutdownTimeoutMills() {
		return shutdownTimeoutMills;
	}

	public int getSendBuff() {
		return sendBuff;
	}

	public int getRecvBuff() {
		return recvBuff;
	}
}