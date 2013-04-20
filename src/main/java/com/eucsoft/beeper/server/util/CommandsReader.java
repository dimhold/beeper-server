package com.eucsoft.beeper.server.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

public class CommandsReader {
	
	private ByteBuf reminder = Unpooled.buffer(); 
	
	private static final byte START = '{';
	private static final byte END = '}';
	
	public List<ByteBuf> getCommands(ByteBuf byteBuf){
		List<ByteBuf> result = new ArrayList<>();
		ByteBuf buf = Unpooled.buffer();
		
		/*while (!reminder.getBoolean(0)){
			reminder.readByte();
		}*/
			
		reminder.writeBytes(byteBuf);
			/*System.out.println("true");;
		} else {
			System.out.println("false");
		}*/
		
		
		boolean messageStarted = false;
		int lastEndIndex = 0;
		int dataEndIndex = 0;
		for (int i = 0; i < reminder.capacity(); i ++) {
		     byte b = byteBuf.getByte(i);
		     buf.writeByte(b);
		     if(b == END){
		    	 lastEndIndex = i+1;
		    	 result.add(buf);
		    	 buf = Unpooled.buffer();
		    	 //buf = reminder.copy();
		    	 //reminder = byteBuf.copy(i+1,byteBuf.capacity()-1-i);
		     }
		     if(b==0){
		    	 dataEndIndex=i;
		    	 break;
		     }
		     //System.out.println((char) b);
		}
		reminder = reminder.copy(lastEndIndex, dataEndIndex);
		reminder.setIndex(0, reminder.capacity()-1-lastEndIndex);
		return result;
	}
}
