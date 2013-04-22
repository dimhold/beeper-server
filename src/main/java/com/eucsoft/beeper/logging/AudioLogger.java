package com.eucsoft.beeper.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.eucsoft.beeper.model.User;

public class AudioLogger {
	
	private static final String PREFIX_DELIMITER = "_";
	private FileOutputStream logOutputStream;

	public void init(User sender) {
		String filename = getPrefix(sender) + ".beep";
		File logFile = new File(getFilePath() + filename);
		try {
			logOutputStream = new FileOutputStream(logFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void append(byte[] msgBytes) {
		try {
			logOutputStream.write(msgBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			logOutputStream.flush();
			logOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//TODO: use config constant here!
	private String getFilePath() {
		return "d:/temp/";
	}
	
	private String getPrefix(User sender) {
		StringBuilder prefixes = new StringBuilder();
		prefixes.append(new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date())).append(PREFIX_DELIMITER);
		prefixes.append(String.valueOf(sender.getRoom().hashCode())).append(PREFIX_DELIMITER);
		prefixes.append(sender.getDeviceInfo());
		for (User nextReceiver : sender.getRoom().getUsers()) {
			prefixes.append(PREFIX_DELIMITER).append(nextReceiver.getDeviceInfo());
		}
		
		return prefixes.toString();
	}
	
}
