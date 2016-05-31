package com.why.srpc.core;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

public class TaskWorker implements Runnable {

	private ChannelHandlerContext ctx;
	private Request request;
	private RequestMapping mapping;

	public TaskWorker(ChannelHandlerContext ctx, Request request, RequestMapping mapping) {
		this.ctx = ctx;
		this.request = request;
		this.mapping = mapping;
	}

	public void run() {

		try {
			String uri = request.getUri();
			ActionMethod actionMethod = mapping.getMethod(uri);
			Map<String, String> params = request.getParam();
			String uuid = request.getUuid();

			Response rs = new Response();
			rs.setUuid(uuid);

			if (actionMethod == null) {
				rs.setResult("method not found");
			} else {
				Object result = actionMethod.call(params);
				rs.setResult((String) result);
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			rs.write(new TCompactProtocol(new TIOStreamTransport(out)));

			ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();

			buf.writeBytes(out.toByteArray());

			ctx.writeAndFlush(buf, ctx.voidPromise());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
