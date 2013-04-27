package com.eucsoft.beeper.server.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.eucsoft.beeper.server.command.CommandType;

public class CommandsProcessorTestDP {

	private static final int TEST_DATA_COUNT = 10;

	@DataProvider
	public static Iterator<Object[]> getCommandsComplexTest(ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		
		for (CommandType command : CommandType.values()) {
			String[] expected = new String[] {
					CommandsProcessor.COMMAND_START
					+ command.toString() + CommandsProcessor.DATA_DELIMETER
					+ "sddgfdgsdfg" + CommandsProcessor.COMMAND_END,
					CommandsProcessor.COMMAND_START
					+ command.toString() + CommandsProcessor.DATA_DELIMETER
					+ "sddgfdgsdfg" + CommandsProcessor.COMMAND_END,
					CommandsProcessor.COMMAND_START
					+ command.toString() + CommandsProcessor.DATA_DELIMETER
					+ "sddgfdgsdfg" + CommandsProcessor.COMMAND_END };

			StringBuilder entireString = new StringBuilder();
			for (String s : expected) {
				entireString.append(s);
			}

			Random rand = new Random();
			List<String> testData;
			for (int i = 0; i < TEST_DATA_COUNT; i++) {
				testData = new ArrayList<>();
				int k = 0;
				int max = entireString.length();
				while (k < max) {
					int r = rand.nextInt(max - k) + 1;
					testData.add(entireString.substring(k, k + r));
					k += r;
				}
				dataToBeReturned
						.add(new Object[] {
								testData.toArray(new String[testData.size()]),
								expected });
			}
		}
		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> getCommandsPartCommandTest(
			ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

		for (CommandType command : CommandType.values()) {
			dataToBeReturned.add(new Object[] { CommandsProcessor.COMMAND_START
					+ command.toString() });
			dataToBeReturned.add(new Object[] { CommandsProcessor.COMMAND_START
					+ command.toString() + CommandsProcessor.DATA_DELIMETER });
			dataToBeReturned.add(new Object[] { CommandsProcessor.COMMAND_START
					+ command.toString() + CommandsProcessor.DATA_DELIMETER
					+ "sddgfdgsdfg" });
			dataToBeReturned.add(new Object[] { CommandsProcessor.COMMAND_START
					+ command.toString() + CommandsProcessor.DATA_DELIMETER
					+ "dsfasdifjalsdjfdsoijf dsfa dofjsflaskjd fdsofj" });
		}

		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> getCommandsEntireCommandTest(
			ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (CommandType command : CommandType.values()) {
			dataToBeReturned.add(new Object[] { CommandsProcessor.COMMAND_START
					+ command.toString() + CommandsProcessor.DATA_DELIMETER
					+ "sddgfdgsdfg" + CommandsProcessor.COMMAND_END });
		}
		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> getCommandsMoreThanCommandTest(
			ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (CommandType command : CommandType.values()) {
			dataToBeReturned.add(new Object[] {
					CommandsProcessor.COMMAND_START + command.toString()
							+ CommandsProcessor.DATA_DELIMETER + "sddgfdgsdfg"
							+ CommandsProcessor.COMMAND_END
							+ CommandsProcessor.COMMAND_START
							+ command.toString(),
					CommandsProcessor.COMMAND_START + command.toString()
							+ CommandsProcessor.DATA_DELIMETER + "sddgfdgsdfg"
							+ CommandsProcessor.COMMAND_END });
		}

		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> getCommandsSeveralCommandsTest(
			ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (CommandType command : CommandType.values()) {
			List<String> expected = new ArrayList<>();
			expected.add(CommandsProcessor.COMMAND_START + command.toString()
					+ CommandsProcessor.DATA_DELIMETER + "sddgfdgsdfg"
					+ CommandsProcessor.COMMAND_END);
			expected.add(CommandsProcessor.COMMAND_START + command.toString()
					+ CommandsProcessor.DATA_DELIMETER + "sddgfdgsdfg"
					+ CommandsProcessor.COMMAND_END);

			dataToBeReturned.add(new Object[] {
					CommandsProcessor.COMMAND_START + command.toString()
							+ CommandsProcessor.DATA_DELIMETER + "sddgfdgsdfg"
							+ CommandsProcessor.COMMAND_END
							+ CommandsProcessor.COMMAND_START
							+ command.toString()
							+ CommandsProcessor.DATA_DELIMETER + "sddgfdgsdfg"
							+ CommandsProcessor.COMMAND_END, expected });

			dataToBeReturned.add(new Object[] {
					CommandsProcessor.COMMAND_START + command.toString()
							+ CommandsProcessor.DATA_DELIMETER + "sddgfdgsdfg"
							+ CommandsProcessor.COMMAND_END
							+ CommandsProcessor.COMMAND_START
							+ command.toString()
							+ CommandsProcessor.DATA_DELIMETER + "sddgfdgsdfg"
							+ CommandsProcessor.COMMAND_END
							+ CommandsProcessor.COMMAND_START
							+ command.toString()
							+ CommandsProcessor.DATA_DELIMETER
							+ "dsiljudicohiu hdsiudhodi hjsdii j oisj",
					expected });
		}

		return dataToBeReturned.iterator();
	}

}
