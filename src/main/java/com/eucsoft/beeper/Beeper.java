package com.eucsoft.beeper;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.nio.NioUdtProvider;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Beeper {

	public static void run(int port) {
		NioEventLoopGroup acceptGroup = new NioEventLoopGroup(1,  new UtilThreadFactory("accept"),
				NioUdtProvider.BYTE_PROVIDER);
		NioEventLoopGroup connectGroup = new NioEventLoopGroup(1,
				new UtilThreadFactory("connect"), NioUdtProvider.BYTE_PROVIDER);
		ServerBootstrap boot = new ServerBootstrap();
		try {
			//boot.group(acceptGroup, connectGroup)
			boot.group(null, null)
					.channelFactory(NioUdtProvider.BYTE_ACCEPTOR)
					.option(ChannelOption.SO_BACKLOG, 10)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<UdtChannel>() {
						@Override
						public void initChannel(UdtChannel ch) throws Exception {
							ch.pipeline().addLast(
									new LoggingHandler(LogLevel.INFO),
									new ByteEchoServerHandler());
						}
					});
			// Start the server.
			ChannelFuture future = boot.bind(port).sync();
			// Wait until the server socket is closed.
			future.channel().closeFuture().sync();
		} finally {
			// Shut down all event loops to terminate all threads.
			boot.shutdown();
		}
	}

}
