package com.eucsoft.beeper.server.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class CommandsReaderTestDP {

	private static final int TEST_DATA_COUNT = 10;

	@DataProvider
	public static Iterator<Object[]> getCommandsComplexTest(ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		
		String[] strings = new String[] {
				"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}",
				"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}",
				"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}" };
		
		String[] testStrings = new String[] {
				"{\"comm",
				"and\":\"message\",\"data\":",
				"ldjkfdl",
				"fjlfk",
				"j}",
				"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}",
				"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}" };
		
		dataToBeReturned.add(new Object[] {testStrings,strings});
		
		StringBuilder entireString = new StringBuilder();
		for (String s : strings) {
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
			dataToBeReturned.add(new Object[] {
					testData.toArray(new String[testData.size()]), strings });
		}
		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> getCommandsPartCommandTest(
			ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

		dataToBeReturned.add(new Object[] { "{\"command\":\"message\"" });

		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> getCommandsEntireCommandTest(
			ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

		dataToBeReturned
				.add(new Object[] { "{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}" });

		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> getCommandsMoreThanCommandTest(
			ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

		dataToBeReturned
				.add(new Object[] {
						"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}{\"command\":\"message\"",
						"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}" });

		return dataToBeReturned.iterator();
	}

	@DataProvider
	public static Iterator<Object[]> getCommandsSeveralCommandsTest(
			ITestContext context) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

		List<String> expected = new ArrayList<>();
		expected.add("{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}");
		expected.add("{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}");

		dataToBeReturned
				.add(new Object[] {
						"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}",
						expected });

		dataToBeReturned
				.add(new Object[] {
						"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}{dsdfkljdfnv,msdklj",
						expected });

		return dataToBeReturned.iterator();
	}

}
