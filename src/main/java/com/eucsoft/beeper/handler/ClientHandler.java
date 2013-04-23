package com.eucsoft.beeper.handler;

import com.eucsoft.beeper.model.User;

 public class ClientHandler {
	
	public void onConnect(User user, String userInfo) {
	}
	
	public void onGetRoom(User user) {
	}
	
	public void onMessageBegin(User user) {
	}
	
	public void onMessage(byte[] message, User user) {
	}
	
	public void onMessageEnd(User user) {
	}
	
	public void onDisconnect(User user) {
	}
	
	
	public void sendMessageBegin(User user) {
	}
	
	public void sendMessage(byte[] message, User user) {
	}
	
	public void sendMessageEnd(User user) {
	}
	
	public void sendChangeRoom(User user) {
	}
	
}
