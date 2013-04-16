package com.eucsoft.beeper.handler;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eucsoft.beeper.Beeper;
import com.eucsoft.beeper.model.User;

public class ClientHandlerTest {
	
	@Test
	public void onConnect() {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect(new User(), "some user information");
		
		int expected = 1;
		int actual = Beeper.getInstance().getUsers().size();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void onGetRoom(User user) {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onGetRoom(new User());

		int expected = 1;
		int actual = Beeper.getInstance().getBench().size();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void onMessageBegin(User user) {
	}
	
	@Test
	public void onMessage(byte[] message, User user) {
	}
	
	@Test
	public void onMessageEnd(User user) {
	}
	
	@Test
	public void onDisconnect(User user) {
	}
	
	
	@Test
	public void sendMessageBegin(User user) {
	}
	
	@Test
	public void sendMessage(byte[] message) {
	}
	
	@Test
	public void sendMessageEnd(User user) {
	}
	
	@Test
	public void sendChangeRoom(User user) {
	}

}
