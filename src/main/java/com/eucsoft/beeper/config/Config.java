package com.eucsoft.beeper.config;

import java.util.ResourceBundle;

public class Config {
	
	private static final ResourceBundle Properties = ResourceBundle.getBundle("com.eucsoft.beeper.config.config");

	public static final int PORT = Integer.parseInt(Properties.getString("port"));

}
