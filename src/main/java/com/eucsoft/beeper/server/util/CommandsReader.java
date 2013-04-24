package com.eucsoft.beeper.server.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandsReader {

	private byte[] reminder = new byte[0];
	
	public static final String MESSAGE_START = "<<<<";
	public static final String MESSAGE_END = ">>>>";
	public static final String DATA_DELIMETER = "****";
	
	private static final byte START = '{';
	private static final byte END = '}';

	public List<byte[]> getCommands(byte[] buffer) {
		List<byte[]> result = new ArrayList<>();
		int lastEndIndex = 0;
		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] == END) {
				// TODO: change array creation using System.arraycopy
				byte[] res = new byte[1 + reminder.length + i - lastEndIndex];
				int index = 0;
				for (; index < reminder.length; index++) {
					res[index] = reminder[index];
				}
				for (int k = lastEndIndex; k < i + 1; k++) {
					res[index] = buffer[k];
					index++;
				}

				result.add(res);
				reminder = new byte[0];
				lastEndIndex = i + 1;
			}
		}

		reminder = joinArrays(reminder,
				Arrays.copyOfRange(buffer, lastEndIndex, buffer.length));
		return result;
	}

	private byte[] joinArrays(byte[] a, byte[] b) {
		byte[] result = new byte[a.length + b.length];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}
}
