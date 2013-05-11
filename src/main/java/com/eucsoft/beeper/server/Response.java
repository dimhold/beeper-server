package com.eucsoft.beeper.server;

import io.netty.buffer.ByteBuf;

public class Response {
	
	private ByteBuf out;
	
	public Response(ByteBuf out) {
		this.out = out;
	}
	
	public void send(String message) {
		this.send(message.getBytes());
	}
	
	public void send(byte[] message) {
		//TODO: Invoke Transport layer adapter
		out.writeBytes(message, 0, message.length);
	}

}
