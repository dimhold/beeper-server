package com.eucsoft.beeper.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;

public class Server {

	public Server() {
		super();
		
		  ServerBootstrap bootstrap = new ServerBootstrap();
				             try {
				               bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup());
				               bootstrap.c
				   
				                 ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080)).sync();
				   
				                 future.channel().closeFuture().sync();
				           } finally {
				                 b.shutdown();
				           }
	}

}
