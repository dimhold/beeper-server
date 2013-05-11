package com.eucsoft.bench;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.eucsoft.beeper.Beeper;
import com.eucsoft.beeper.model.User;

public class BenchDemonTest {

	@BeforeMethod
	public void cleanBench() {
		List<User> users = new ArrayList<User>();
		users.addAll(Beeper.getInstance().getBench().keySet());
		for (User user: users) {
			Beeper.getInstance().removeUserFromBench(user);
		}
	}
	
	@Test
	public void run_without_users() {
		Map<User, Date> bench = Beeper.getInstance().getBench();
		
		assertEquals(bench.size(), 0);
		
		BenchDemon benchDemon = new BenchDemon(0);
		benchDemon.stop();
		benchDemon.run();
		
		assertEquals(bench.size(), 0);
	}
	
	@Test
	public void run_with_one_user() {
		Beeper.getInstance().addUserToBench(new User("user1"));
		Map<User, Date> bench = Beeper.getInstance().getBench();
		
		assertEquals(bench.size(), 1);
		
		BenchDemon benchDemon = new BenchDemon(0);
		benchDemon.stop();
		benchDemon.run();
		
		assertEquals(bench.size(), 1);
	}
	
	@Test
	public void run_with_one_even_users() {
		Beeper.getInstance().addUserToBench(new User("user1"));
		Beeper.getInstance().addUserToBench(new User("user2"));
		Map<User, Date> bench = Beeper.getInstance().getBench();
		
		assertEquals(bench.size(), 2);
		
		BenchDemon benchDemon = new BenchDemon(0);
		benchDemon.stop();
		benchDemon.run();
		
		assertEquals(bench.size(), 0);
	}
	
	@Test
	public void run_with_one_odd_users() {
		Beeper.getInstance().addUserToBench(new User("user1"));
		Beeper.getInstance().addUserToBench(new User("user2"));
		Beeper.getInstance().addUserToBench(new User("user3"));
		Map<User, Date> bench = Beeper.getInstance().getBench();
		
		assertEquals(bench.size(), 3);
		
		
		BenchDemon benchDemon = new BenchDemon(0);
		benchDemon.stop();
		benchDemon.run();
		
		assertEquals(bench.size(), 1);
	}

}
