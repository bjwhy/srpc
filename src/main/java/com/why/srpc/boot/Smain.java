package com.why.srpc.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.why.srpc.tcp.NettyServerWithoutZk;

public class Smain {
	public static void main(String[] args) throws Exception {
		// ServerConfig sc = new ServerConfig();
		// NettyServer server = new NettyServer(sc);
		// server.serve();

		// NameRegister nr = new NameRegister();
		// nr.connectZookeeper(Constants.zk, 5000);
		// nr.createNode(Constants.root_path + "/test", CreateMode.PERSISTENT);
		// nr.createNode(Constants.root_path + "/test/1", "1.1.1.1",
		// CreateMode.EPHEMERAL);
		//
		// Thread.sleep(100000000);

		ApplicationContext ctx = new FileSystemXmlApplicationContext("config/applicationContext.xml");

		NettyServerWithoutZk server = (NettyServerWithoutZk) ctx.getBean("server");
		server.serve();
	}
}
