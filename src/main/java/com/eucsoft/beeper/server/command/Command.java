package com.eucsoft.beeper.server.command;

import java.util.Arrays;

public class Command {

	private CommandType type;
	private byte[] data;
	
	public Command(CommandType commandType) {
		setType(commandType);
	}
	
	public Command() {
	}

	public CommandType getType() {
		return type;
	}

	public void setType(CommandType type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
