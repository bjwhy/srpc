package com.why.srpc.boot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.why.srpc.core.Request;
import com.why.srpc.registry.NameDiscover;

public class Cmain {
	public static void main(String[] args) throws Exception {
		// NettyClient sc = new NettyClient();
		// sc.tcpConnect("127.0.0.1", 9999);
		//
		// Map<String, String> param = new HashMap<String, String>();
		// Request re = new Request();
		// re.setUri("/hello");
		// re.setName("11");
		// re.setUuid("22222");
		// re.setParam(param);
		// sc.sendMsg(re);

		ApplicationContext ctx = new FileSystemXmlApplicationContext("config/applicationContext.xml");

		Map<String, String> param = new HashMap<String, String>();

		Request msg = new Request("", "", "/hello", param);

		NameDiscover cli = (NameDiscover) ctx.getBean("client");
		cli.connectZookeeper();
		cli.initServers();
		for (int i = 0; i < 100; i++) {
			cli.sendMsg("/srpc/test/admin", msg);
			Thread.sleep(1000);
		}

		Thread.sleep(100000000);
	}
}
