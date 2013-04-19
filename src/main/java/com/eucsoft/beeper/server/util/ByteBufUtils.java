package com.eucsoft.beeper.server.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufUtils {
	
	private static ByteBuf reminder = Unpooled.buffer(1000); 
	
	private static final byte START = '{';
	private static final byte END = '}';
	
	public static ByteBuf nextCommand(ByteBuf byteBuf){
		ByteBuf result = Unpooled.buffer();
		boolean messageStarted = false;
		if (reminder.capacity()>0){
			result.writeBytes(reminder);
			messageStarted = true;
		}
		for (int i = 0; i < byteBuf.capacity(); i ++) {
		     byte b = byteBuf.getByte(i);
		     result.writeByte(b);
		     if(b == END){
		    	 reminder.clear();
		    	 //byteBuf.getBytes(i,reminder);
		    	 return result;
		     }
		     //System.out.println((char) b);
		}
		reminder.clear().writeBytes(result);
		
		return null;
	}
}
