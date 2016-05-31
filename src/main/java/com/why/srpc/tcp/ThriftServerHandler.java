package com.why.srpc.tcp;

import java.util.concurrent.ExecutorService;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;

import com.why.srpc.core.Request;
import com.why.srpc.core.ServerContext;
import com.why.srpc.core.TaskWorker;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public class ThriftServerHandler extends ChannelInboundHandlerAdapter {

	private ExecutorService userExecutor;
	private ServerContext context;

	public ThriftServerHandler(ExecutorService executor, ServerContext context) {
		this.userExecutor = executor;
		this.context = context;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws TException {

		ByteBuf buf = (ByteBuf) msg;

		ByteBufTransport bt = new ByteBufTransport(buf);

		Request ss = new Request();

		ss.read(new TCompactProtocol(bt));

		buf.release();

		userExecutor.submit(new TaskWorker(ctx, ss, context.getMapping()));

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
	}
}