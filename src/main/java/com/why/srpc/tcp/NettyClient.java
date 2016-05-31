package com.why.srpc.tcp;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.why.srpc.core.Request;
import com.why.srpc.core.Response;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient extends ChannelInboundHandlerAdapter {
	private Channel ch;
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup(
			Runtime.getRuntime().availableProcessors());;

	public String serverAdress;

	private final Object obj = new Object();

	private Response rs;

	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

	public void tcpConnect(String serverAdress, int serverPort) {
		this.serverAdress = serverAdress;

		Bootstrap bootstrap = new Bootstrap();

		bootstrap.group(workerGroup).channel(NioSocketChannel.class)
				.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ThriftFrameDecoder()).addLast(new ThriftFrameEncoder())
								.addLast(NettyClient.this);
					}
				});

		try {
			ch = bootstrap.connect(serverAdress, serverPort).sync().channel();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stop() {
		workerGroup.shutdownGracefully();
	}

	public Response sendMsg(Request msg) {

		try {
			// TByteArrayOutputStream out = new TByteArrayOutputStream();
			// msg.write(new TCompactProtocol(new TIOStreamTransport(out)));

			ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();

			msg.write(new TCompactProtocol(new ByteBufTransport(buf)));

			ch.writeAndFlush(buf, ch.voidPromise());

			synchronized (obj) {
				obj.wait(5000);// 未收到响应，使线程等待
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return rs;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws TException {

		ByteBuf buf = (ByteBuf) msg;

		ByteBufTransport bt = new ByteBufTransport(buf);

		Response rs = new Response();

		rs.read(new TCompactProtocol(bt));

		buf.release();

		this.rs = rs;

		synchronized (obj) {
			obj.notifyAll(); // 收到响应，唤醒线程
		}

		logger.info("receice the result: {}", rs.getResult());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
	}
}