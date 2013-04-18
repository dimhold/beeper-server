package com.eucsoft.beeper.server.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ByteBufUtilsTest {
	
	@Test(dataProviderClass=ByteBufUtilsTestDP.class,dataProvider="fileDataProvider")
	public void nextCommand(String[] testStrings,String[] expected){
		ByteBuf byteBuf = Unpooled.buffer();
		List<ByteBuf> result = new ArrayList<ByteBuf>();
		for(String s: testStrings){
			byteBuf.writeBytes(s.getBytes());
			result.add(ByteBufUtils.nextCommand(byteBuf));
		}
		
		Assert.assertEquals(expected.length, result.size());
		Assert.assertEquals(result.toArray(new String[result.size()]), expected);
		
	}

}
