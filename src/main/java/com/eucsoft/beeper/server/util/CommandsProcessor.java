package com.eucsoft.beeper.server.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.eucsoft.beeper.server.command.Command;
import com.eucsoft.beeper.server.command.CommandType;

public class CommandsProcessor {

	private byte[] reminder = new byte[0];

	public static final String COMMAND_START = "<<<<COMMANDSTART";
	public static final String COMMAND_END = "COMMANDEND>>>>";
	public static final String DATA_DELIMETER = "****";

	private int commandStartIndex = 0;
	private int commandEndIndex = 0;

	public List<Command> getCommands(byte[] buffer) {
		List<byte[]> byteCommands = new ArrayList<>();
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

					byteCommands.add(res);
					reminder = new byte[0];
					lastEndIndex = i + 1;
				}
			} else {
				commandEndIndex = 0;
				if (buffer[i] == COMMAND_END.charAt(commandEndIndex)){
					commandEndIndex++;
				}
			}
		}

		reminder = joinArrays(reminder,
				Arrays.copyOfRange(buffer, lastEndIndex, buffer.length));
		
		List<Command> result = new ArrayList<>();
		
		for(byte[] commandBytes: byteCommands){
			result.add(buildCommand(commandBytes));
		}
		
		return result;
	}
	
	private Command buildCommand(byte[] commandBytes) {
		int delimeterIndex = 0;
		Command result = new Command();
		for(int i = COMMAND_START.length()-1; i< commandBytes.length;i++){
			if (commandBytes[i] == DATA_DELIMETER.charAt(delimeterIndex)){
				delimeterIndex++;
				if (delimeterIndex == DATA_DELIMETER.length()) {
					delimeterIndex = 0;
					byte[] command = new byte[i-DATA_DELIMETER.length()-COMMAND_START.length()+1];
					System.arraycopy(commandBytes, COMMAND_START.length(), command, 0, command.length);
					result.setType(CommandType.valueOf(new String(command)));
					byte[] data = new byte[commandBytes.length-DATA_DELIMETER.length()-COMMAND_END.length()-COMMAND_START.length()-command.length];
					System.arraycopy(commandBytes, COMMAND_START.length()+command.length+DATA_DELIMETER.length(), data, 0, data.length);
					result.setData(data);
				}
			}
		}
		return result;
	}
	
	public byte[] buildByteArray(Command command){
		int length = COMMAND_START.length() + command.getType().toString().length() + DATA_DELIMETER.length() + command.getData().length + COMMAND_END.length();
		byte[] result = new byte[length];
		int index = 0;
		byte[] src = COMMAND_START.getBytes();
		System.arraycopy(src, 0, result, index, src.length);
		index += src.length;
		src = command.getType().toString().getBytes();
		System.arraycopy(src, 0, result, index, src.length);
		index += src.length;
		src = DATA_DELIMETER.getBytes();
		System.arraycopy(src, 0, result, index, src.length);
		index += src.length;
		src = command.getData();
		System.arraycopy(src, 0, result, index, src.length);
		index += src.length;
		src = COMMAND_END.getBytes();
		System.arraycopy(src, 0, result, index, src.length);
		index += src.length;
		
		return result;
	}

	private byte[] joinArrays(byte[] a, byte[] b) {
		byte[] result = new byte[a.length + b.length];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}
}
