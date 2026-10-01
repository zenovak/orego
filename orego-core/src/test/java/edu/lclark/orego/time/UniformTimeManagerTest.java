package edu.lclark.orego.time;

import edu.lclark.orego.core.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniformTimeManagerTest {

	private TimeManager manager;
	
	@BeforeEach
	public void setUp() throws Exception {
		Board board = new Board(5);
		manager = new UniformTimeManager(board);
	}

	@Test
	public void testSetRemainingSeconds() {
		manager.setRemainingSeconds(100);
		assertEquals(9000, manager.getMsec());
		manager.setRemainingSeconds(1000);
		manager.startNewTurn();
		assertEquals(99000, manager.getMsec());
	}

}
