package com.eucsoft.bench;

import java.util.ArrayList;
import java.util.List;

import com.eucsoft.beeper.Beeper;
import com.eucsoft.beeper.model.Room;
import com.eucsoft.beeper.model.User;

public class BenchDemon implements Runnable {
	
	private long sleepDuration = 1000;
	private boolean stop = false;
	
	public BenchDemon(long sleepDuration) {
		super();
		this.sleepDuration = sleepDuration;
	}
	
	public BenchDemon() {
		super();
	}
	
	public void stop() {
		this.stop = true;
	}

	@Override
	public void run() {
		do {
			processBench();
			sleep();
		} while (!stop);
	}
	
	private void processBench() {
		List<User> bench = new ArrayList<User>();
		bench.addAll(Beeper.getInstance().getBench().keySet());
		
		if (bench.size() > 1) {
			for (int i = 1; i < bench.size(); i+=2) {
				connectUsers(bench.get(i-1), bench.get(i));
			}
		}
	}
	
	private void connectUsers(User user1, User user2) {
		Room room = new Room();
		room.addUser(user1);
		room.addUser(user2);
		Beeper.getInstance().addRoom(room);
		
		Beeper.getInstance().removeUserFromBench(user1);
		Beeper.getInstance().removeUserFromBench(user2);
	}
	
	private void sleep() {
		try {
			Thread.sleep(sleepDuration);
		} catch (InterruptedException e) {
			stop();
		}
	}

}
