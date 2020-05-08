package com.in28mintues.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class SpringTest {

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(actualLength, expectedLength);
	}

}
