package com.eucsoft.beeper.server;

//import static org.powermock.api.mockito.PowerMockito.*;

import io.netty.buffer.ByteBuf;

import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.internal.verification.Times;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.Test;

import com.eucsoft.beeper.Beeper;
import com.eucsoft.beeper.handler.ClientHandler;
import com.eucsoft.beeper.model.User;
@PrepareForTest(User.class)
public class ClientConnectionHandlerTest {
	@Test
	public void inboundBufferUpdated() {
		User user = mock(User.class);
		ClientConnectionHandler handler = new ClientConnectionHandler(user);
		ByteBuf in = mock(ByteBuf.class);
		
		handler.inboundBufferUpdated(null, in);
		ClientHandler clientHandler = Beeper.getInstance().getClientHandler(user);
		verify(user, times(2)).getRoom();
		
	}
}
