package com.eucsoft.beeper;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;

import com.eucsoft.beeper.handler.ClientHandler;
import com.eucsoft.beeper.model.Room;
import com.eucsoft.beeper.model.User;

public class Beeper {

	private static Beeper beeper = new Beeper();
	
	private Collection<User> users = Collections.synchronizedSet(new HashSet<User>()); 
	
	private Collection<Room> rooms = Collections.synchronizedSet(new HashSet<Room>());
	
	private Map<User, Date> bench = new Hashtable<User, Date>(); 
	
	private Map<User, ClientHandler> clientHandlers = new Hashtable<User, ClientHandler>();
	
	private Beeper(){}
	
	public static Beeper getInstance() {
		return beeper;
	}
	
	public Collection<User> getUsers() {
		return Collections.unmodifiableCollection(users);
	}

	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
	
	public Collection<Room> getRooms() {
		return Collections.unmodifiableCollection(rooms);
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public void removeRoom(Room room) {
		rooms.remove(room);
	}
	
	public Map<User, Date> getBench() {
		return Collections.unmodifiableMap(bench);
	}
	
	public void addUserToBench(User user) {
		bench.put(user,  new Date());
	}
	
	public void removeUserFromBench(User user) {
		bench.remove(user);
	}
	
	public void addClientHandler(User user, ClientHandler handler) {
		clientHandlers.put(user, handler);
	}
	
	public ClientHandler getClientHandler(User user) {
		return clientHandlers.get(user);
	}
	
	public void removeClientHandler(User user) {
		clientHandlers.remove(user);
	}

}
