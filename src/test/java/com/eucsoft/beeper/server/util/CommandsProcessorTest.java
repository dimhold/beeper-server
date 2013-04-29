package com.eucsoft.beeper.server.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.eucsoft.beeper.server.command.Command;

public class CommandsProcessorTest {

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsPartCommandTest")
	public void getCommandsPartCommandTest(String testString) {
		CommandsProcessor commandsProcessor = new CommandsProcessor();
		List<Command> res = commandsProcessor.getCommands(testString.getBytes());
		
		assertTrue(res.isEmpty());
	}

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsEntireCommandTest")
	public void getCommandsEntireCommandTest(String testString, Command expected) {
		CommandsProcessor commandsProcessor = new CommandsProcessor();
		List<Command> res = commandsProcessor.getCommands(testString.getBytes());
		
		assertEquals(res.size(), 1);
		assertTrue(res.get(0).equals(expected));
	}

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsMoreThanCommandTest")
	public void getCommandsMoreThanOneCommandTest(String testString,
			Command expected) {
		CommandsProcessor commandsProcessor = new CommandsProcessor();
		List<Command> res = commandsProcessor.getCommands(testString.getBytes());
		
		assertEquals(res.size(), 1);
		assertTrue(res.get(0).equals(expected));
	}

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsSeveralCommandsTest")
	public void getCommandsSeveralCommandsTest(String testString,
			List<Command> expected) {
		CommandsProcessor commandsProcessor = new CommandsProcessor();
		List<Command> res = commandsProcessor.getCommands(testString.getBytes());
		assertEquals(res.size(), expected.size());
		for (int i = 0; i < res.size(); i++) {
			assertTrue(res.get(i).equals(expected.get(i)));
		}
	}

	@Test(dataProviderClass = CommandsProcessorTestDP.class, dataProvider = "getCommandsComplexTest")
	public void getCommandsComplexTest(String[] testStrings, List<Command> expected) {
		CommandsProcessor commandsProcessor = new CommandsProcessor();
		// ByteBuf byteBuf = Unpooled.buffer();
		List<Command> res = new ArrayList<>();
		for (String s : testStrings) {
			// byteBuf.writeBytes(s.getBytes());
			res.addAll(commandsProcessor.getCommands(s.getBytes()));
		}
		assertEquals(expected.size(), res.size());
		for (int i = 0; i < res.size(); i++) {
			assertTrue(res.get(i).equals(expected.get(i)));
		}
	}
}
