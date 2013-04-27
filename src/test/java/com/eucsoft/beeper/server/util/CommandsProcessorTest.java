package com.eucsoft.beeper.server.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class CommandsProcessorTest {

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsPartCommandTest")
	public void getCommandsPartCommandTest(String testString) {
		CommandsProcessor bufUtils = new CommandsProcessor();
		List<byte[]> res = bufUtils.getCommands(testString.getBytes());
		
		assertTrue(res.isEmpty());
	}

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsEntireCommandTest")
	public void getCommandsEntireCommandTest(String testString) {
		CommandsProcessor bufUtils = new CommandsProcessor();
		List<byte[]> res = bufUtils.getCommands(testString.getBytes());
		
		assertEquals(res.size(), 1);
		assertTrue(Arrays.equals(res.get(0), testString.getBytes()));
	}

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsMoreThanCommandTest")
	public void getCommandsMoreThanOneCommandTest(String testString,
			String expected) {
		CommandsProcessor bufUtils = new CommandsProcessor();
		List<byte[]> res = bufUtils.getCommands(testString.getBytes());
		
		assertEquals(res.size(), 1);
		assertTrue(Arrays.equals(res.get(0), expected.getBytes()));
	}

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsSeveralCommandsTest")
	public void getCommandsSeveralCommandsTest(String testString,
			List<String> expected) {
		CommandsProcessor bufUtils = new CommandsProcessor();
		List<byte[]> res = bufUtils.getCommands(testString.getBytes());
		assertEquals(res.size(), expected.size());
		for (int i = 0; i < res.size(); i++) {
			assertTrue(Arrays.equals(res.get(i), expected.get(i)
					.getBytes()));
		}
	}

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsComplexTest")
	public void getCommandsComplexTest(String[] testStrings, String[] expected) {
		CommandsProcessor bufUtils = new CommandsProcessor();
		// ByteBuf byteBuf = Unpooled.buffer();
		List<byte[]> res = new ArrayList<byte[]>();
		for (String s : testStrings) {
			// byteBuf.writeBytes(s.getBytes());
			res.addAll(bufUtils.getCommands(s.getBytes()));
		}

		assertEquals(expected.length, res.size());

		for (int i = 0; i < res.size(); i++) {
			assertTrue(Arrays.equals(res.get(i), expected[i].getBytes()));
		}

	}

}
