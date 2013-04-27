package com.eucsoft.beeper.server.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.eucsoft.beeper.server.command.Command;

public class CommandsProcessor {

	private byte[] reminder = new byte[0];

	public static final String COMMAND_START = "<<<<";
	public static final String COMMAND_END = ">>>>";
	public static final String DATA_DELIMETER = "****";

	private int commandStartIndex = 0;
	private int commandEndIndex = 0;

	public List<byte[]> getCommands(byte[] buffer) {
		List<byte[]> result = new ArrayList<>();
		int lastEndIndex = 0;
		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] == COMMAND_END.charAt(commandEndIndex)) {
				commandEndIndex++;
				if (commandEndIndex == COMMAND_END.length()) {
					commandEndIndex = 0;
					// TODO: change array creation using System.arraycopy
					byte[] res = new byte[1 + reminder.length + i
							- lastEndIndex];
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
		}

		reminder = joinArrays(reminder,
				Arrays.copyOfRange(buffer, lastEndIndex, buffer.length));
		return result;
	}
	
	private Command buildCommand(byte[] commandBytes) {
		
		return null;
	}

	private byte[] joinArrays(byte[] a, byte[] b) {
		byte[] result = new byte[a.length + b.length];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}
}
