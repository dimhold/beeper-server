package com.eucsoft.beeper.server;

//import static org.powermock.api.mockito.PowerMockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import io.netty.buffer.ByteBuf;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.Test;

import com.eucsoft.beeper.handler.ClientHandler;
@PrepareForTest(ClientHandler.class)
public class ClientConnectionHandlerTest {
	@Test
	public void inboundBufferUpdated() throws Exception {
		ClientHandler clientHandler = mock(ClientHandler.class);
		PowerMockito.whenNew(ClientHandler.class).withNoArguments().thenReturn(clientHandler);
		ClientConnectionHandler handler = new ClientConnectionHandler();
		ByteBuf in = mock(ByteBuf.class);
		
		handler.inboundBufferUpdated(null, in);
		//ClientHandler clientHandler = Beeper.getInstance().getClientHandler(user);
		verify(clientHandler, times(1)).onMessageBegin();
		
	}
}
