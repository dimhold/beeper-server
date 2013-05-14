package com.eucsoft.beeper.server;

//import static org.powermock.api.mockito.PowerMockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import io.netty.buffer.ByteBuf;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eucsoft.beeper.handler.ClientHandler;
import com.eucsoft.beeper.server.command.Command;
import com.eucsoft.beeper.server.command.CommandType;

@PrepareForTest(ClientConnectionHandler.class)
public class ClientConnectionHandlerTest extends PowerMockTestCase {
	@Test
	public void inboundBufferUpdatedTest() throws Exception {
		ClientHandler clientHandler = mock(ClientHandler.class);
		PowerMockito.whenNew(ClientHandler.class).withNoArguments()
				.thenReturn(clientHandler);
		ClientConnectionHandler handler = mock(ClientConnectionHandler.class);
		//PowerMockito.verifyNew(ClientHandler.class).withNoArguments();

		for (CommandType commandType : CommandType.values()) {
			Command command = new Command();
			command.setType(commandType);
			String testData = "testData";
			command.setData(testData.getBytes());
			PowerMockito.verifyPrivate(handler).invoke("dispatchCommand",command);
			
			switch (commandType) {
			case CONNECT:
				verify(clientHandler, times(1)).onConnect(testData);
				break;
			// case
			default:
				Assert.assertTrue(false);
				break;
			}
		}

		ByteBuf in = mock(ByteBuf.class);
		Mockito.when(in.array()).thenReturn(null);
		handler.inboundBufferUpdated(null, in);

	}
}
