package com.eucsoft.beeper.handler;

import static org.mockito.Mockito.*;

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
	public void onGetRoom() {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onGetRoom(new User());

		int expected = 1;
		int actual = Beeper.getInstance().getBench().size();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void onMessageBegin() {
		ClientHandler clientHandler = new ClientHandler();
		Room room = new Room();
		User user1 = new User();
		User user2 = new User();
		user1.setRoom(room);
		user2.setRoom(room);
		room.addUser(user1);
		room.addUser(user2);

		ClientHandler clientHandler2 = Beeper.getInstance().getClientHandler(user2);
		verify(clientHandler2, times(1)).sendMessageBegin(user2);
		
		clientHandler.onMessageBegin(user1);
	}

	@Test
	public void onMessage() {
		ClientHandler clientHandler = new ClientHandler();
		Room room = new Room();
		User user1 = new User();
		User user2 = new User();
		user1.setRoom(room);
		user2.setRoom(room);
		room.addUser(user1);
		room.addUser(user2);

		ClientHandler clientHandler2 = Beeper.getInstance().getClientHandler(user2);
		verify(clientHandler2, times(1)).sendMessage("{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}".getBytes(), user2);
		
		clientHandler.onMessage("{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}".getBytes(), user1);
	}

	@Test
	public void onMessageEnd() {
		ClientHandler clientHandler = new ClientHandler();
		Room room = new Room();
		User user1 = new User();
		User user2 = new User();
		user1.setRoom(room);
		user2.setRoom(room);
		room.addUser(user1);
		room.addUser(user2);

		ClientHandler clientHandler2 = Beeper.getInstance().getClientHandler(user2);
		verify(clientHandler2, times(1)).sendMessageEnd(user2);
		
		clientHandler.onMessageEnd(user1);
	}

	@Test
	public void onDisconnect() {
		User user = new User();

		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect(user, "some user information");

		Assert.assertEquals(Beeper.getInstance().getUsers().size(), 1);
		
		clientHandler.onDisconnect(user);
		Assert.assertEquals(Beeper.getInstance().getUsers().size(), 0);
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
