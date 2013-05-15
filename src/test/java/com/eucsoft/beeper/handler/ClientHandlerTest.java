package com.eucsoft.beeper.handler;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.Test;

import com.eucsoft.beeper.Beeper;
import com.eucsoft.beeper.bench.BenchDemon;
import com.eucsoft.beeper.logging.AudioLogger;
import com.eucsoft.beeper.model.User;

@PrepareForTest(ClientHandler.class)
public class ClientHandlerTest {

	@Test
	public void onConnect() {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect("some user information");

		int expected = 1;
		int actual = Beeper.getInstance().getUsers().size();
		assertEquals(actual, expected);
	}

	@Test
	public void onConnect_user_has_device_info() {
		String expected = "some user information";

		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect(expected);

		User user = Beeper.getInstance().getUsers().iterator().next();

		String actual = user.getDeviceInfo();

		assertEquals(actual, expected);
	}

	@Test
	public void onGetRoom() {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect("user");
		clientHandler.onGetRoom();

		int expected = 1;
		int actual = Beeper.getInstance().getBench().size();
		assertEquals(actual, expected);
	}

	@Test
	public void onMessageBegin() {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect("user1");
		clientHandler.onGetRoom();
		clientHandler.logger = mock(AudioLogger.class);
		doNothing().when(clientHandler.logger).append(null);
		
		ClientHandler clientHandler2 = mockClientHandler();
		
		clientHandler.onMessageBegin();
		
		verify(clientHandler2).sendMessageBegin();
	}

	@Test
	public void onMessage() {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect("user1");
		clientHandler.onGetRoom();
		clientHandler.logger = mock(AudioLogger.class);
		doNothing().when(clientHandler.logger).append(null);

		ClientHandler clientHandler2 = mockClientHandler();
		
		clientHandler.onMessage("{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}".getBytes());
		
		verify(clientHandler2).sendMessage("{\"command\":\"message\",\"data\":ldjkfdlfjlfkj}".getBytes());
	}

	@Test
	public void onMessageEnd() {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect("user1");
		clientHandler.onGetRoom();
		clientHandler.logger = mock(AudioLogger.class);
		doNothing().when(clientHandler.logger).append(null);

		ClientHandler clientHandler2 = mockClientHandler();
		
		clientHandler.onMessageEnd();

		verify(clientHandler2).sendMessageEnd();
	}

	@Test
	public void onDisconnect() {
		ClientHandler clientHandler = new ClientHandler();
		clientHandler.onConnect("some user information");

		assertEquals(Beeper.getInstance().getUsers().size(), 1);
		
		clientHandler.onDisconnect();
		assertEquals(Beeper.getInstance().getUsers().size(), 0);
	}

	private ClientHandler mockClientHandler () {
		ClientHandler clientHandler2 = mock(ClientHandler.class);
		
		Mockito.doCallRealMethod().when(clientHandler2).onConnect("user2");
		Mockito.doCallRealMethod().when(clientHandler2).onGetRoom();
		clientHandler2.onConnect("user2");
		clientHandler2.onGetRoom();
		
		BenchDemon benchDemon = new BenchDemon(0);
		benchDemon.stop();
		benchDemon.run();
		return clientHandler2;
	}

}
