package com.eucsoft.beeper;

import java.util.regex.Pattern;

import com.eucsoft.beeper.config.Config;

public class Main {

    public static void main(String[] args) {
    	if (args.length > 0 && Pattern.matches("^\\d+$", args[0])) {
    		Beeper.run(Integer.parseInt(args[0]));
    	} else {
    		Beeper.run(Config.PORT);	
    	}
    }

}
