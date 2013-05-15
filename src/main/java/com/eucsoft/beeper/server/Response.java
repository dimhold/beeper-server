package com.eucsoft.beeper.server;

import com.eucsoft.beeper.server.command.Command;
import com.eucsoft.beeper.server.util.CommandsProcessor;

import io.netty.buffer.ByteBuf;

public class Response {
	
	private ByteBuf out;
	
	public Response(ByteBuf out) {
		this.out = out;
	}
	
	public void send(Command command) {
		CommandsProcessor commandsProcessor = new CommandsProcessor();
		this.send(commandsProcessor.buildByteArray(command));
	}
	
	public void send(byte[] message) {
		out.writeBytes(message, 0, message.length);
	}

}
