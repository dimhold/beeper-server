package com.eucsoft.beeper.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerUtil;
import io.netty.channel.ChannelInboundByteHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.udt.nio.NioUdtProvider;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Meter;

public class ByteEchoClientHandler extends ChannelInboundByteHandlerAdapter {

    private static final Logger log = Logger.getLogger(ByteEchoClientHandler.class.getName());

    private final ByteBuf message;

    final Meter meter = Metrics.newMeter(ByteEchoClientHandler.class, "rate",
            "bytes", TimeUnit.SECONDS);

    public ByteEchoClientHandler(final int messageSize) {
        message = Unpooled.buffer(messageSize);

        for (int i = 0; i < message.capacity(); i++) {
            message.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        log.info("ECHO active " + NioUdtProvider.socketUDT(ctx.channel()).toStringOptions());
        ctx.write(message);
    }

    @Override
    public void inboundBufferUpdated(final ChannelHandlerContext ctx,
            final ByteBuf in) {
        meter.mark(in.readableBytes());
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
    public ByteBuf newInboundBuffer(final ChannelHandlerContext ctx)
            throws Exception {
        return ChannelHandlerUtil.allocate(ctx,
                ctx.channel().config().getOption(ChannelOption.SO_RCVBUF));
    }

}