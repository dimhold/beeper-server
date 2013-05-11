package com.eucsoft.beeper.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerUtil;
import io.netty.channel.ChannelInboundByteHandlerAdapter;
import io.netty.channel.ChannelOption;

import com.eucsoft.beeper.handler.ClientHandler;

public class ClientConnectionHandler extends ChannelInboundByteHandlerAdapter {

	private ClientHandler clientHandler  = new ClientHandler();
	
    public ClientConnectionHandler() {
		super();
	}

	@Override
    public void inboundBufferUpdated(final ChannelHandlerContext ctx,
            final ByteBuf in) {
		//TODO: invoke Transport layer

        /*final ByteBuf out = ctx.nextOutboundByteBuffer();
        out.discardReadBytes();
        out.writeBytes(in);
        ctx.flush();*/
    	
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx,
            final Throwable cause) {
        ctx.close();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
    	ByteBuf out = ctx.nextOutboundByteBuffer();
    	clientHandler.setResponse(new Response(out));
    }

    @Override
    public ByteBuf newInboundBuffer(final ChannelHandlerContext ctx)
            throws Exception {
        return ChannelHandlerUtil.allocate(ctx,
                ctx.channel().config().getOption(ChannelOption.SO_RCVBUF));
    }
}
