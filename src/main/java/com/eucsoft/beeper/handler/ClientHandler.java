package com.eucsoft.beeper.handler;

import com.eucsoft.beeper.Beeper;
import com.eucsoft.beeper.logging.AudioLogger;
import com.eucsoft.beeper.model.Room;
import com.eucsoft.beeper.model.User;
import com.eucsoft.beeper.server.Response;

 public class ClientHandler {
	
	private User user;
	//TODO: should be private, but needs PowerMock in ClientHandlerTest
	AudioLogger logger;
	
	private Response response;
	
	public void setResponse(Response response) {
		this.response = response;
	}
	
	public void onConnect(String userInfo) {
		user = new User(userInfo);
		Beeper.getInstance().addUser(user);
		Beeper.getInstance().addClientHandler(user, this);
	}
	
	public void onGetRoom() {
		Room currentRoom = user.getRoom();
		if (currentRoom != null) {
			currentRoom.removeUser(user);
		}
		Beeper.getInstance().addUserToBench(user);
	}
	
	public void onMessageBegin() {
		logger = new AudioLogger();
		logger.init(user);
		
		for (User nextUserInRoom : user.getRoom().getUsers()) {
			if (! nextUserInRoom.equals(user)) {
				ClientHandler nextListener = Beeper.getInstance().getClientHandler(nextUserInRoom);
				nextListener.sendMessageBegin();
			}
		}
	}
	
	public void onMessage(byte[] message) {
		logger.append(message);
		for (User nextUserInRoom : user.getRoom().getUsers()) {
			if (! nextUserInRoom.equals(user)) {
				ClientHandler nextListener = Beeper.getInstance().getClientHandler(nextUserInRoom);
				nextListener.sendMessage(message);
			}
		}
	}
	
	public void onMessageEnd() {
		for (User nextUserInRoom : user.getRoom().getUsers()) {
			if (! nextUserInRoom.equals(user)) {
				ClientHandler nextListener = Beeper.getInstance().getClientHandler(nextUserInRoom);
				nextListener.sendMessageEnd();
			}
		}
		
		logger.close();
	}
	
	public void onDisconnect() {
		if (user.getRoom() != null) {
			user.getRoom().removeUser(user);
		}
		Beeper.getInstance().removeUserFromBench(user);
		Beeper.getInstance().removeUser(user);
		//TODO: remove clinetHandler!
	}
	
	
	public void sendMessageBegin() {
		//TODO: User protocol specification
		response.send("begin");
	}
	
	public void sendMessage(byte[] message) {
		//TODO: User protocol specification
		response.send(message);
	}
	
	public void sendMessageEnd() {
		//TODO: User protocol specification
		response.send("end");
	}
	
	public void sendChangeRoom() {
		//TODO: User protocol specification
		response.send("changeRoom");
	}
}
