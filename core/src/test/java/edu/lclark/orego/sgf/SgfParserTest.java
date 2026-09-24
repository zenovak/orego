package edu.lclark.orego.sgf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import edu.lclark.orego.core.Board;
import edu.lclark.orego.core.CoordinateSystem;
import edu.lclark.orego.core.StoneColor;
import edu.lclark.orego.util.TestingTools;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SgfParserTest {

	private SgfParser parser;

	private Board board;

	private CoordinateSystem coords;

	@BeforeEach
	public void setUp() {
		board = new Board(19);
		coords = board.getCoordinateSystem();
		parser = new SgfParser(coords, true);
	}

	/** Delegate method to call at on coords. */
	private short at(String label) {
		return coords.at(label);
	}

	@Test
	public void testSgfToPoint() {
		// Test conversion of sgf (and human-readable strings) to ints
		assertEquals(at("e15"), parser.sgfToPoint("ee"));
		assertEquals(at("t1"), parser.sgfToPoint("ss"));
		assertEquals(at("a19"), parser.sgfToPoint("aa"));
		assertEquals(at("t19"), parser.sgfToPoint("sa"));
		assertEquals(at("a1"), parser.sgfToPoint("as"));
	}

	@SuppressWarnings("boxing")
	@Test
	public void testSgfToMoves() throws URISyntaxException {
		URL url = getClass().getClassLoader().getResource("sgf-test-files/19/1977-02-27.sgf");
		final List<List<Short>> games = parser.parseGamesFromFile(new File(url.toURI()), 500);
		assertEquals(1, games.size());
		final List<Short> game = games.get(0);
		assertEquals(180, game.size());
		assertEquals(coords.at("R16"), (short) game.get(0));
		assertEquals(coords.at("N11"), (short) game.get(179));
	}

	@Test
	public void testSgfToMovesPrint() throws URISyntaxException {
		URL url = getClass().getClassLoader().getResource("sgf-test-files/19/1977-02-27.sgf");
		final List<List<Short>> games = parser.parseGamesFromFile(new File(url.toURI()), 179);
		for (final List<Short> game : games) {
			for (final Short move : game) {
				System.out.println(coords.toString(move));
			}
		}
	}
	
	@Test
	public void testBreakOnPass() throws URISyntaxException {
		URL url = getClass().getClassLoader().getResource("sgf-test-files/19/Orego4-Magisus.sgf");
		List<Short> moves = parser.parseGameFromFile(new File(url.toURI()));
		assertEquals(174, moves.size());
		parser = new SgfParser(coords, false);
		moves = parser.parseGameFromFile(new File(url.toURI()));
		assertEquals(254, moves.size());
	}
	
	@Test
	public void testCgtc() throws URISyntaxException {
		String[] diagram = {
				"...................",
				"..........#........",
				".....O......#.#....",
				"...O....O.O....#...",
				"...................",
				"...#............#..",
				"...............O#..",
				"..O.............O..",
				"...............#...",
				"..O................",
				"..............#.#..",
				"...O...............",
				"...................",
				"...................",
				"..O................",
				"......O.......#.#..",
				"...O....O..#.......",
				"...................",
				"...................",
		};
		URL url = getClass().getClassLoader().getResource("sgf-test-files/19/blunder.1.sgf");
		parser.sgfToBoard(new File(url.toURI()), board);
		assertEquals(StoneColor.BLACK, board.getColorToPlay());
		assertEquals(TestingTools.asOneString(diagram), board.toString());
	}

}
