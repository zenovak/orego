package edu.lclark.orego.mcts;


import edu.lclark.orego.core.Board;
import edu.lclark.orego.core.CoordinateSystem;
import edu.lclark.orego.feature.LgrfTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.lclark.orego.core.CoordinateSystem.NO_POINT;
import static edu.lclark.orego.core.StoneColor.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LgrfUpdaterTest {
	
	private Player player;
	
	private CoordinateSystem coords;
	
	private LgrfUpdater updater;
	
	private LgrfTable lgrfTable;

	@BeforeEach
	public void setUp() throws Exception {
		player = new PlayerBuilder().msecPerMove(100).threads(4).boardWidth(5).lgrf2(true).memorySize(1).rave(false).build();
		Board board = player.getBoard();
		coords = board.getCoordinateSystem();
		updater = (LgrfUpdater)player.getUpdater();
		lgrfTable = updater.getTable();
	}
	
	@Test
	public void testLgrfUpdate(){
		McRunnable runnable = player.getMcRunnable(0);
		runnable.acceptMove(coords.at("a1"));
		runnable.acceptMove(coords.at("b1"));
		runnable.acceptMove(coords.at("c1"));
		updater.updateTree(BLACK, runnable);
		assertEquals(coords.at("c1"), lgrfTable.getFirstLevelReply(BLACK, coords.at("b1")));
		assertEquals(coords.at("a1"), lgrfTable.getFirstLevelReply(BLACK, NO_POINT));
		assertEquals(coords.at("a1"), lgrfTable.getSecondLevelReply(BLACK, NO_POINT, NO_POINT));
		assertEquals(NO_POINT, lgrfTable.getFirstLevelReply(BLACK, coords.at("a1")));
		assertEquals(coords.at("c1"), lgrfTable.getSecondLevelReply(BLACK, coords.at("a1"), coords.at("b1")));
	}
	
}
