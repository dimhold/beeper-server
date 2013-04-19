package com.eucsoft.beeper.server.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ByteBufUtilsTest {
	
	@Test(dataProviderClass=ByteBufUtilsTestDP.class,dataProvider="nextCommandPartCommandTest")
	public void nextCommandPartCommandTest(String testString){
		ByteBufUtils bufUtils = new ByteBufUtils();
		ByteBuf res = bufUtils.nextCommand(toByteBuf(testString));
		Assert.assertNull(res);
	}
	
	@Test(dataProviderClass=ByteBufUtilsTestDP.class,dataProvider="nextCommandEntireCommandTest")
	public void nextCommandEntireCommandTest(String testString){
		ByteBufUtils bufUtils = new ByteBufUtils();
		ByteBuf res = bufUtils.nextCommand(toByteBuf(testString));
		Assert.assertTrue(res.equals(toByteBuf(testString)));
	}
	
	@Test(dataProviderClass=ByteBufUtilsTestDP.class,dataProvider="nextCommandMoreThanCommandTest")
	public void nextCommandMoreThanCommandTest(String testString, String expected){
		ByteBufUtils bufUtils = new ByteBufUtils();
		ByteBuf res = bufUtils.nextCommand(toByteBuf(testString));
		Assert.assertTrue(res.equals(toByteBuf(expected)));
	}
	
	@Test(dataProviderClass=ByteBufUtilsTestDP.class,dataProvider="nextCommandComplexTest")
	public void nextCommandComplexTest(String[] testStrings,String[] expected){
		ByteBufUtils bufUtils = new ByteBufUtils();
		ByteBuf byteBuf = Unpooled.buffer();
		List<ByteBuf> result = new ArrayList<ByteBuf>();
		for(String s: testStrings){
			byteBuf.writeBytes(s.getBytes());
			result.add(bufUtils.nextCommand(byteBuf));
		}
		
		Assert.assertEquals(expected.length, result.size());
		Assert.assertEquals(result.toArray(new String[result.size()]), expected);
		
	}
	
	
	private ByteBuf toByteBuf(String string){
		return Unpooled.buffer().writeBytes(string.getBytes());
	}

}
