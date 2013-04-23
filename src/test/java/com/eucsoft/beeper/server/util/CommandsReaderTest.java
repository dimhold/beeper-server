package com.eucsoft.beeper.server.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class CommandsReaderTest {

	@Test(dataProviderClass = CommandsReaderTestDP.class, dataProvider = "getCommandsPartCommandTest")
	public void getCommandsPartCommandTest(String testString) {
		CommandsReader bufUtils = new CommandsReader();
		List<byte[]> res = bufUtils.getCommands(testString.getBytes());
		
		assertTrue(res.isEmpty());
	}

	@Test(dataProviderClass = CommandsReaderTestDP.class, dataProvider = "getCommandsEntireCommandTest")
	public void getCommandsEntireCommandTest(String testString) {
		CommandsReader bufUtils = new CommandsReader();
		List<byte[]> res = bufUtils.getCommands(testString.getBytes());
		
		assertEquals(res.size(), 1);
		assertTrue(Arrays.equals(res.get(0), testString.getBytes()));
	}

	@Test(dataProviderClass = CommandsReaderTestDP.class, dataProvider = "getCommandsMoreThanCommandTest")
	public void getCommandsMoreThanOneCommandTest(String testString,
			String expected) {
		CommandsReader bufUtils = new CommandsReader();
		List<byte[]> res = bufUtils.getCommands(testString.getBytes());
		
		assertEquals(res.size(), 1);
		assertTrue(Arrays.equals(res.get(0), expected.getBytes()));
	}

	@Test(dataProviderClass = CommandsReaderTestDP.class, dataProvider = "getCommandsSeveralCommandsTest")
	public void getCommandsSeveralCommandsTest(String testString,
			List<String> expected) {
		CommandsReader bufUtils = new CommandsReader();
		List<byte[]> res = bufUtils.getCommands(testString.getBytes());
		assertEquals(res.size(), expected.size());
		for (int i = 0; i < res.size(); i++) {
			assertTrue(Arrays.equals(res.get(i), expected.get(i)
					.getBytes()));
		}
	}

	@Test(dataProviderClass = CommandsReaderTestDP.class, dataProvider = "getCommandsComplexTest")
	public void getCommandsComplexTest(String[] testStrings, String[] expected) {
		CommandsReader bufUtils = new CommandsReader();
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
