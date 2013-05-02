package com.eucsoft.beeper.server;

//import static org.powermock.api.mockito.PowerMockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import io.netty.buffer.ByteBuf;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.Test;

import com.eucsoft.beeper.handler.ClientHandler;
@PrepareForTest(ClientHandler.class)
public class ClientConnectionHandlerTest {
	@Test
	public void inboundBufferUpdatedTest() throws Exception {
		ClientHandler clientHandler = mock(ClientHandler.class);
		PowerMockito.whenNew(ClientHandler.class).withNoArguments().thenReturn(clientHandler);
		
		ClientConnectionHandler handler = new ClientConnectionHandler();
		PowerMockito.verifyNew(ClientHandler.class).withNoArguments();
		ByteBuf in = mock(ByteBuf.class);
		Mockito.when(in.array()).thenReturn(null);
		handler.inboundBufferUpdated(null, in);
		//verify(clientHandler, times(1)).onConnect("");
		
	}
}
