package com.eucsoft.beeper.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.nio.NioUdtProvider;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.eucsoft.beeper.server.util.UtilThreadFactory;

public class ByteEchoClient {

    private static final Logger log = Logger.getLogger(ByteEchoClient.class.getName());

    private final String host;
    private final int port;
    private final int messageSize;

    public ByteEchoClient(final String host, final int port,
            final int messageSize) {
        this.host = host;
        this.port = port;
        this.messageSize = messageSize;
    }

    public void run() throws Exception {
        // Configure the client.
        final ThreadFactory connectFactory = new UtilThreadFactory("connect");
        final NioEventLoopGroup connectGroup = new NioEventLoopGroup(1,
                connectFactory, NioUdtProvider.BYTE_PROVIDER);
        try {
            final Bootstrap boot = new Bootstrap();
            boot.group(connectGroup)
                    .channelFactory(NioUdtProvider.BYTE_CONNECTOR)
                    .handler(new ChannelInitializer<UdtChannel>() {
                        @Override
                        public void initChannel(final UdtChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new LoggingHandler(LogLevel.INFO),
                                    new ByteEchoClientHandler(messageSize));
                        }
                    });
            // Start the client.
            final ChannelFuture f = boot.connect(host, port).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            connectGroup.shutdown();
        }
    }

    public static void main(final String[] args) throws Exception {
        log.info("init");

        // client is reporting metrics
        //UtilConsoleReporter.enable(3, TimeUnit.SECONDS);

        final String host = "localhost";
        final int port = 1234;

        final int messageSize = 2 * 1024;

        new ByteEchoClient(host, port, messageSize).run();

        log.info("done");
    }

}