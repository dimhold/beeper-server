package com.eucsoft.beeper.server.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandsReaderTest {
	
	@Test(dataProviderClass=CommandsReaderTestDP.class,dataProvider="getCommandsPartCommandTest")
	public void getCommandsPartCommandTest(String testString){
		CommandsReader bufUtils = new CommandsReader();
		List<ByteBuf> res = bufUtils.getCommands(toByteBuf(testString));
		Assert.assertTrue(res.isEmpty());
	}
	
	@Test(dataProviderClass=CommandsReaderTestDP.class,dataProvider="getCommandsEntireCommandTest")
	public void getCommandsEntireCommandTest(String testString){
		CommandsReader bufUtils = new CommandsReader();
		List<ByteBuf> res = bufUtils.getCommands(toByteBuf(testString));
		Assert.assertTrue(res.get(0).equals(toByteBuf(testString)));
	}
	
	@Test(dataProviderClass=CommandsReaderTestDP.class,dataProvider="getCommandsMoreThanCommandTest")
	public void getCommandsMoreThanOneCommandTest(String testString, String expected){
		CommandsReader bufUtils = new CommandsReader();
		List<ByteBuf> res = bufUtils.getCommands(toByteBuf(testString));
		Assert.assertEquals(res.size(), 1);
		Assert.assertTrue(res.get(0).equals(toByteBuf(expected)));
	}
	
	@Test(dataProviderClass=CommandsReaderTestDP.class,dataProvider="getCommandsSeveralCommandsTest")
	public void getCommandsSeveralCommandsTest(String testString, List<String> expected){
		CommandsReader bufUtils = new CommandsReader();
		List<ByteBuf> res = bufUtils.getCommands(toByteBuf(testString));
		Assert.assertEquals(res.size(), expected.size());
		for (int i=0; i< res.size();i++){
			Assert.assertTrue(res.get(i).equals(toByteBuf(expected.get(i))));
		}
	}
	
	@Test(dataProviderClass=CommandsReaderTestDP.class,dataProvider="getCommandsComplexTest")
	public void getCommandsComplexTest(String[] testStrings,String[] expected){
		CommandsReader bufUtils = new CommandsReader();
		//ByteBuf byteBuf = Unpooled.buffer();
		List<ByteBuf> res = new ArrayList<ByteBuf>();
		for(String s: testStrings){
			//byteBuf.writeBytes(s.getBytes());
			res.addAll(bufUtils.getCommands(toByteBuf(s)));
		}
		
		Assert.assertEquals(expected.length, res.size());
		
		for (int i=0; i< res.size();i++){
			Assert.assertTrue(res.get(i).equals(toByteBuf(expected[i])));
		}
		
	}
	
	
	private ByteBuf toByteBuf(String string){
		return Unpooled.buffer().writeBytes(string.getBytes());
	}

}
