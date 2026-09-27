package edu.lclark.orego.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestingToolsTest {

	@SuppressWarnings("static-method")
	@Test
	public void testAsOneString() {
		assertEquals("foo\nbar\n", TestingTools.asOneString(new String[] {"foo", "bar"}));
	}

}
