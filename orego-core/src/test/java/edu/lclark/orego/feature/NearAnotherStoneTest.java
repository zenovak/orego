package edu.lclark.orego.feature;

import static edu.lclark.orego.core.StoneColor.BLACK;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.lclark.orego.core.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NearAnotherStoneTest {
	
	/** Delegate method to call at on board. */
	private short at(String label) {
		return board.getCoordinateSystem().at(label);
	}

	private Board board;
	
	private NearAnotherStone nearAnotherStone;
	
	@BeforeEach
	public void setUp() throws Exception {
		board = new Board(5);
		nearAnotherStone = new NearAnotherStone(board);
	}

	@Test
	public void testAt() {
		String[] before = {
				"#....",
				".....",
				".....",
				".....",
				".....",
		};
		board.setUpProblem(before, BLACK);
		assertFalse(nearAnotherStone.at(at("e4")));
		assertTrue(nearAnotherStone.at(at("b5")));
		assertTrue(nearAnotherStone.at(at("c3")));
		assertTrue(nearAnotherStone.at(at("b2")));
	}
	
	@Test
	public void testClear() {
		String[] before = {
				"#....",
				".....",
				".....",
				".....",
				".....",
		};
		board.setUpProblem(before, BLACK);
		board.play("a2");
		assertFalse(nearAnotherStone.at(at("e5")));
		assertTrue(nearAnotherStone.at(at("b5")));
		assertTrue(nearAnotherStone.at(at("c3")));
		board.clear();
		assertFalse(nearAnotherStone.at(at("a4")));
	}

}
