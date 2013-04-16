package com.eucsoft.beeper.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundByteHandlerAdapter;

public class ClientConnectionHandler extends ChannelInboundByteHandlerAdapter {

	@Override
	protected void inboundBufferUpdated(ChannelHandlerContext channel, ByteBuf buf)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
