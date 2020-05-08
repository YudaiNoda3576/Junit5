package com.in28mintues.junit5;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class TestPractice {

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@RepeatedTest(10)
	void contains_basic() {
		assertFalse("abcdefgh".contains("ijk"));
	}
	
	@Test
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(5), () -> {
			for(int i = 0; i <=10000; i++) {
				int j = i;			
				System.out.println(j);
			}		
		}
	  );
	}
}
