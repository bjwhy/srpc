package com.why.srpc.registry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.why.srpc.core.Request;
import com.why.srpc.core.Response;
import com.why.srpc.tcp.NettyClient;

public class ClientGroup {
	private List<NettyClient> servers = new CopyOnWriteArrayList<NettyClient>();

	private LoadBalance lb = new LoadBalance(0);

	public List<NettyClient> getServers() {
		return servers;
	}

	public void setServers(List<NettyClient> servers) {
		this.servers = servers;
	}

	public void add(NettyClient cli) {
		servers.add(cli);
		lb.addCount();
	}

	public void remove(NettyClient cli) {
		servers.remove(cli);
		lb.reduceCount();
	}

	public Response send(Request msg) {
		NettyClient cli = servers.get(lb.roundIndex());
		return cli.sendMsg(msg);
	}
}
