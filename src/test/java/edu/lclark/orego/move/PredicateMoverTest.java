package edu.lclark.orego.move;

import static edu.lclark.orego.core.StoneColor.BLACK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.lclark.orego.core.Board;
import edu.lclark.orego.core.CoordinateSystem;
import edu.lclark.orego.feature.NotEyeLike;
import edu.lclark.orego.thirdparty.MersenneTwisterFast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredicateMoverTest {

	private Board board;
	
	private CoordinateSystem coords;

	private Mover mover;
	
	/** Delegate method to call at on board. */
	private short at(String label) {
		return coords.at(label);
	}


	@BeforeEach
	public void setUp() throws Exception {
		board = new Board(5);
		coords = board.getCoordinateSystem();
		mover = new PredicateMover(board, new NotEyeLike(board));
	}

	@Test
	public void testSelectAndPlayOneMove() {
		String[] diagram = {
				".###.",
				"#####",
				"#O.O#",
				"OOOOO",
				"O...O",
		};
		int[] counts = new int[coords.getFirstPointBeyondBoard()];
		MersenneTwisterFast random = new MersenneTwisterFast();
		int trials = 1000;
		for (int i = 0; i < trials; i++) {
			board.setUpProblem(diagram, BLACK);
			counts[mover.selectAndPlayOneMove(random, true)]++;
		}
		assertEquals(trials/4.0, counts[at("b1")], trials/20.0);
		assertEquals(trials/4.0, counts[at("c1")], trials/20.0);
		assertEquals(trials/4.0, counts[at("c3")], trials/20.0);
		assertEquals(trials/4.0, counts[at("d1")], trials/20.0);
	}

}
