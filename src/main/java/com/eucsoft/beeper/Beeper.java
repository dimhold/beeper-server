package com.eucsoft.beeper;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.eucsoft.beeper.handler.ClientHandler;
import com.eucsoft.beeper.model.Room;
import com.eucsoft.beeper.model.User;

public class Beeper {

	private static Beeper beeper = new Beeper();
	
	private Collection<User> users = new HashSet<User>(); 
	
	private Collection<Room> rooms = new HashSet<Room>();
	
	private Map<User, Date> bench = new HashMap<User, Date>(); 
	
	private Map<User, ClientHandler> clientHandlers = new HashMap<User, ClientHandler>();
	
	private Beeper(){}
	
	public static Beeper getInstance() {
		return beeper;
	}
	
	public Collection<User> getUsers() {
		return Collections.unmodifiableCollection(users);
	}

	public void addUser(User user) {
		synchronized (users) {
			users.add(user);
		}
	}
	
	public void removeUser(User user) {
		synchronized (users) {
			users.remove(user);
		}
	}
	
	public Collection<Room> getRooms() {
		return Collections.unmodifiableCollection(rooms);
	}
	
	public void addRoom(Room room) {
		synchronized (rooms) {
			rooms.add(room);
		}
	}
	
	public void removeRoom(Room room) {
		synchronized (rooms) {
			rooms.remove(room);
		}
	}
	
	public Map<User, Date> getBench() {
		return Collections.unmodifiableMap(bench);
	}
	
	public void addUserToBench(User user) {
		synchronized (bench) {
			bench.put(user,  new Date());
		}
	}
	
	public void removeUserFromBench(User user) {
		synchronized (bench) {
			bench.remove(user);
		}
	}
	
	public void addClientHandler(User user, ClientHandler handler) {
		synchronized (clientHandlers) {
			clientHandlers.put(user, handler);
		}
	}
	
	public ClientHandler getClientHandler(User user) {
		synchronized (clientHandlers) {
			return clientHandlers.get(user);
		}
	}

}
