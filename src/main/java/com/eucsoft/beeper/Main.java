package com.eucsoft.beeper;

import com.eucsoft.beeper.server.Server;

public class Main {

    public static void main( String[] args ) {
    	System.out.println("main");
    	Server server = new Server(1234);
    	try {
			server.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
