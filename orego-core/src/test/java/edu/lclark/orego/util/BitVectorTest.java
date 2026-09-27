package edu.lclark.orego.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BitVectorTest {

	/** Capacity of the bit vector used in these tests. */
	public static final int CAPACITY = 500;

	private BitVector vector;

	@BeforeEach
	public void setUp() throws Exception {
		vector = new BitVector(CAPACITY);
	}

	@Test
	public void testClear() {
		// All bits should be false
		for (int i = 0; i < CAPACITY; i++) {
			assertFalse(vector.get(i));
		}
		// Set all bits to true
		for (int i = 0; i < CAPACITY; i++) {
			vector.set(i, true);
		}
		// Verify that they are true
		for (int i = 0; i < CAPACITY; i++) {
			assertTrue(vector.get(i));
		}
		// Clear the vector
		vector.clear();
		// All bits should now be false
		for (int i = 0; i < CAPACITY; i++) {
			assertFalse(vector.get(i));
		}
	}

	@Test
	public void testSet() {
		// Toggle each bit, verifying that it changes both ways
		for (int i = 0; i < CAPACITY; i++) {
			assertFalse(vector.get(i));
			vector.set(i, true);
			assertTrue(vector.get(i));
			vector.set(i, false);
			assertFalse(vector.get(i));
		}
	}

}
