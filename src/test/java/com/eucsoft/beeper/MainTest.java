package com.eucsoft.beeper;

import static org.powermock.api.mockito.PowerMockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@PrepareForTest(MainTest.class)
public class MainTest {
	
	public String test() {
		return "work";
	}

	@Test
	public void demoStaticMethodMocking() throws Exception {
		String expected = "successful";
		MainTest main = mock(MainTest.class);
		when(main.test()).thenReturn(expected);
		
		String actual = main.test();

		Assert.assertEquals(actual, expected);
	}

}
