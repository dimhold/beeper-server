package com.eucsoft.beeper.server;

//import static org.powermock.api.mockito.PowerMockito.*;

import static org.mockito.Mockito.*;
import io.netty.buffer.ByteBuf;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.Test;

import com.eucsoft.beeper.handler.ClientHandler;
import com.eucsoft.beeper.model.User;
@PrepareForTest(ClientHandler.class)
public class ClientConnectionHandlerTest {
	@Test
	public void inboundBufferUpdated() throws Exception {
		User user = mock(User.class);
		ClientHandler clientHandler = mock(ClientHandler.class);
		PowerMockito.whenNew(ClientHandler.class).withNoArguments().thenReturn(clientHandler);
		ClientConnectionHandler handler = new ClientConnectionHandler(user);
		ByteBuf in = mock(ByteBuf.class);
		
		handler.inboundBufferUpdated(null, in);
		//ClientHandler clientHandler = Beeper.getInstance().getClientHandler(user);
		verify(clientHandler, times(1)).onMessageBegin();
		
	}
}
