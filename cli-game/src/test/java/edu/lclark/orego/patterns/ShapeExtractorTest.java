package edu.lclark.orego.patterns;

import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.lclark.orego.core.Board;
import edu.lclark.orego.feature.HistoryObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShapeExtractorTest {

	public ShapeExtractor extractor;

	@BeforeEach
	public void setUp() throws Exception {
		extractor = new ShapeExtractor(false, 0.9f, 2);
	}

	@Test
	public void test() {
		String outputFile = "test-books/3x3PatternTest.data";
		extractor.buildPatternData("sgf-test-files/19/PatternTest.sgf", outputFile);
		ShapeTable table = new ShapeTable(outputFile, 0.99f);
		Board board = new Board(9);
		HistoryObserver history = new HistoryObserver(board);
		board.play("d5");
		board.play("a1");
		board.play("f5");
		assertTrue(table.getWinRate(PatternFinder.getHash(board,
				board.getCoordinateSystem().at("e4"), 2, history.get(board.getTurn()-1))) > 0.5);
		assertTrue(table.getWinRate(PatternFinder.getHash(board,
				board.getCoordinateSystem().at("e6"), 2, history.get(board.getTurn()-1))) > 0.5);
	}
}
