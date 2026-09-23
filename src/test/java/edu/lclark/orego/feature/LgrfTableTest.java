package edu.lclark.orego.feature;

import static edu.lclark.orego.core.CoordinateSystem.*;
import static edu.lclark.orego.core.StoneColor.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.lclark.orego.core.CoordinateSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LgrfTableTest {
	
	private CoordinateSystem coords;
	
	private LgrfTable table;
	
	@BeforeEach
	public void setUp() throws Exception {
		coords = CoordinateSystem.forWidth(19);
		table = new LgrfTable(coords);
	}

	@Test
	public void testUpdate() {
		table.update(BLACK, true, coords.at("a1"), coords.at("b1"), coords.at("c1"));
		assertEquals(coords.at("c1"), table.getFirstLevelReply(BLACK, coords.at("b1")));
		assertEquals(NO_POINT, table.getFirstLevelReply(BLACK, coords.at("a1")));
		assertEquals(coords.at("c1"), table.getSecondLevelReply(BLACK, coords.at("a1"), coords.at("b1")));
	}
	
	@Test
	public void testClear(){
		table.update(BLACK, true, coords.at("a1"), coords.at("b1"), coords.at("c1"));
		table.clear();
		assertEquals(NO_POINT, table.getFirstLevelReply(BLACK, coords.at("b1")));
	}

}
