package com.eucsoft.beeper.server.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class ByteBufUtilsTestDP {
	
	private static final int TEST_DATA_COUNT = 10;

	@DataProvider
	public static Iterator<Object[]> fileDataProvider(ITestContext context) {
		// Get the input file path from the ITestContext
		
		/*String inputFile = context.getCurrentXmlTest().getParameter("filenamePath");
		// Get a list of String file content (line items) from the test file.
		List<String> testData = getFileContentList(inputFile);

		// We will be returning an iterator of Object arrays so create that
		// first.
		 */
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

		String[] strings = new String[] {
				"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}",
				"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}",
				"{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}" };
		
		StringBuilder entireString = new StringBuilder();
		for(String s : strings){
			entireString.append(s);
		}
		
		Random rand = new Random();
		List<String> testData;
		for (int i =0;i<TEST_DATA_COUNT;i++){
			testData = new ArrayList<>();
			int k = 0;
			int max = entireString.length();
			while(k<max){
				int r = rand.nextInt(max-k)+1;
				testData.add(entireString.substring(k, k+r));
				k +=r;
			}
			dataToBeReturned.add(new Object[] {testData.toArray(new String[testData.size()]),strings});
		}

		

		// Populate our List of Object arrays with the file content.
		/*
		 * for (String userData : testData) { dataToBeReturned.add(new Object[]
		 * { userData } ); }
		 */
		// return the iterator - testng will initialize the test class and calls
		// the
		// test method with each of the content of this iterator.
		return dataToBeReturned.iterator();
	}

	private static List<String> getFileContentList(String filenamePath) {
		// Sample utility method to get the file content, any version of
		// this can be adapted, this is just one way of achieving the result.
		InputStream is;
		List<String> lines = new ArrayList<String>();
		try {
			is = new FileInputStream(new File(filenamePath));
			DataInputStream in = new DataInputStream(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				lines.add(strLine);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
