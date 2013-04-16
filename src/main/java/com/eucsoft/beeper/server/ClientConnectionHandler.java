package com.eucsoft.beeper.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerUtil;
import io.netty.channel.ChannelInboundByteHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.udt.nio.NioUdtProvider;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnectionHandler extends ChannelInboundByteHandlerAdapter {

    private static final Logger log = Logger.getLogger(ClientConnectionHandler.class.getName());

    @Override
    public void inboundBufferUpdated(final ChannelHandlerContext ctx,
            final ByteBuf in) {
        final ByteBuf out = ctx.nextOutboundByteBuffer();
        out.discardReadBytes();
        out.writeBytes(in);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx,
            final Throwable cause) {
        log.log(Level.WARNING, "close the connection when an exception is raised", cause);
        ctx.close();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		log.info("ECHO active "
				+ NioUdtProvider.socketUDT(ctx.channel()).toStringOptions());
    }

    @Override
    public ByteBuf newInboundBuffer(final ChannelHandlerContext ctx)
            throws Exception {
        return ChannelHandlerUtil.allocate(ctx,
                ctx.channel().config().getOption(ChannelOption.SO_RCVBUF));
    }
	
}
