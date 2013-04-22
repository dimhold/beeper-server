package com.eucsoft.beeper.handler;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eucsoft.beeper.Beeper;
import com.eucsoft.beeper.MainTest;
import com.eucsoft.beeper.model.Room;
import com.eucsoft.beeper.model.User;

@PrepareForTest(MainTest.class)
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
	public void onConnect_user_has_device_info() {
		String expected = "some user information";

		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect(new User(), expected);

		User user = Beeper.getInstance().getUsers().iterator().next();

		String actual = user.getDeviceInfo();

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
		ClientHandler clientHandler = new ClientHandler();
		Room room = new Room();
		User user1 = new User();
		User user2 = new User();
		room.addUser(user1);
		room.addUser(user2);

		ClientHandler clientHandler2 = Beeper.getInstance().getClientHandler(user2);

		// Check that clientHandler2.sendMessageBigein is triggered;
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
