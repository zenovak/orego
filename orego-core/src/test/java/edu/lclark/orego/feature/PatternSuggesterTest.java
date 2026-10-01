package edu.lclark.orego.feature;

import static edu.lclark.orego.core.StoneColor.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.lclark.orego.core.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatternSuggesterTest {

	private Board board;
	
	private HistoryObserver history;
	
	private PatternSuggester patterns;
	
	@BeforeEach
	public void setUp() throws Exception {
		board = new Board(5);
		history = new HistoryObserver(board);
		patterns = new PatternSuggester(board, history);
	}

	@Test
	public void test() {
		String[] diagram = {
				"O....",
				".....",
				"O....",
				".....",
				".....",
		};
		board.setUpProblem(diagram, BLACK);
		board.play("b4");
		assertTrue(patterns.getMoves().contains(board.getCoordinateSystem().at("a4")));
		board.play("c3");
		board.play("c1");
		assertFalse(patterns.getMoves().contains(board.getCoordinateSystem().at("b2")));
	}
	
	@Test
	public void testTigersMouth() {
		String[] diagram = {
				".....",
				"..O#.",
				"...O.",
				".....",
				".....",
		};
		board.setUpProblem(diagram, WHITE);
		board.play("b3");
		assertFalse(patterns.getMoves().contains(board.getCoordinateSystem().at("c3")));
	}

}
