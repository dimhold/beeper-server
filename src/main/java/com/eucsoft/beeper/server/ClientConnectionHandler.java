package com.eucsoft.beeper.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerUtil;
import io.netty.channel.ChannelInboundByteHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.udt.nio.NioUdtProvider;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.eucsoft.beeper.handler.ClientHandler;
import com.eucsoft.beeper.server.command.Command;
import com.eucsoft.beeper.server.util.CommandsProcessor;

public class ClientConnectionHandler extends ChannelInboundByteHandlerAdapter {

    private static final Logger log = Logger.getLogger(ClientConnectionHandler.class.getName());
    
    private ClientHandler clientHandler = new ClientHandler();
    private CommandsProcessor commandsProcessor = new CommandsProcessor();
    
    private ByteBuf out;
    
	@Override
    public void inboundBufferUpdated(final ChannelHandlerContext ctx,
            final ByteBuf in) {
		List<Command> commands = commandsProcessor.getCommands(in.array());

		for (Command command : commands){
			dispatchCommand(command);
		}
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx,
            final Throwable cause) {
        log.log(Level.WARNING, "close the connection when an exception is raised", cause);
        ctx.close();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
    	out = ctx.nextOutboundByteBuffer();
		log.info("ECHO active "
				+ NioUdtProvider.socketUDT(ctx.channel()).toStringOptions());
    }

    @Override
    public ByteBuf newInboundBuffer(final ChannelHandlerContext ctx)
            throws Exception {
        return ChannelHandlerUtil.allocate(ctx,
                ctx.channel().config().getOption(ChannelOption.SO_RCVBUF));
    }
    
    public void sendResponse(byte[] message) {
    	out.writeBytes(message, 0, message.length);
    }
    
    private void dispatchCommand(Command command){
    	switch (command.getType()) {
		case CONNECT:
			clientHandler.onConnect(new String(command.getData()));
			break;
		case GET_ROOM:
			clientHandler.onGetRoom();
			break;
		case MESSAGE_BEGIN:
			clientHandler.onMessageBegin();
			break;
		case MESSAGE:
			clientHandler.onMessage(command.getData());
			break;
		case MESSAGE_END:
			clientHandler.onMessageEnd();
			break;
		case DISCONNECT:
			clientHandler.onDisconnect();
			break;
		}
    }
	
}
