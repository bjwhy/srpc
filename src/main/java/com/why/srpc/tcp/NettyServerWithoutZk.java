package com.why.srpc.tcp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.why.srpc.config.ServerConfig;
import com.why.srpc.core.ServerContext;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

public class NettyServerWithoutZk {

	@Autowired
	private ServerConfig args;

	@Autowired
	private ServerContext context;

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private ExecutorService userThreadPool;

	public void serve() {

		ServerBootstrap b = configServer();

		try {
			// start server
			ChannelFuture f = b.bind(args.getPort()).sync();

			// register shutown hook
			Runtime.getRuntime().addShutdownHook(new ShutdownThread());

			// blocking to wait for close
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {

		bossGroup.shutdownGracefully();
		gracefulShutdown(userThreadPool, args.getShutdownTimeoutMills(), args.getShutdownTimeoutMills(),
				TimeUnit.SECONDS);
		workerGroup.shutdownGracefully();
	}

	private ServerBootstrap configServer() {
		bossGroup = new NioEventLoopGroup(args.getBossThreads(), new DefaultThreadFactory("NettyBossGroup", true));
		workerGroup = new NioEventLoopGroup(args.getWorkerThreads(),
				new DefaultThreadFactory("NettyWorkerGroup", true));
		userThreadPool = Executors.newFixedThreadPool(args.getUserThreads(),
				new DefaultThreadFactory("UserThreads", true));

		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childOption(ChannelOption.SO_REUSEADDR, true).childOption(ChannelOption.SO_KEEPALIVE, true)
				.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
				.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
				.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

		if (args.getSocketTimeoutMills() > 0) {
			b.option(ChannelOption.SO_TIMEOUT, args.getSocketTimeoutMills());
		}

		if (args.getRecvBuff() > 0) {
			b.childOption(ChannelOption.SO_RCVBUF, args.getRecvBuff());
		}

		if (args.getSendBuff() > 0) {
			b.childOption(ChannelOption.SO_SNDBUF, args.getSendBuff());
		}

		b.childHandler(new ChannelInitializer<SocketChannel>() {

			private final ThriftServerHandler thriftHandler = new ThriftServerHandler(userThreadPool, context);

			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ThriftFrameDecoder()).addLast(new ThriftFrameEncoder())
						.addLast(thriftHandler);
			}
		});

		return b;
	}

	public static void gracefulShutdown(ExecutorService pool, int shutdownTimeout, int shutdownNowTimeout,
			TimeUnit timeUnit) {
		pool.shutdown(); // Disable new tasks from being submitted
		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(shutdownTimeout, timeUnit)) {
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(shutdownNowTimeout, timeUnit)) {
					System.err.println("Pool did not terminated");
				}
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

	public void setContext(ServerContext context) {
		this.context = context;
	}

	class ShutdownThread extends Thread {
		@Override
		public void run() {
			NettyServerWithoutZk.this.stop();
		}
	}
}