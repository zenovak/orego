package edu.lclark.orego.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleTimeManagerTest {

	private SimpleTimeManager manager;
	
	@BeforeEach
	public void setUp() throws Exception {
		manager = new SimpleTimeManager(123);
	}

	@Test
	public void testGetTime() {
		assertEquals(123, manager.getMsec());
		assertEquals(0, manager.getMsec());
		manager.startNewTurn();
		assertEquals(123, manager.getMsec());
	}

}
