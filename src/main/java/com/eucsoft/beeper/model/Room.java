package com.eucsoft.beeper.model;

import java.util.Collection;
import java.util.HashSet;

public class Room {

	private Collection<User> users = new HashSet<User>();
	
	public Collection<User> getUsers() {
		return users;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
	
}
