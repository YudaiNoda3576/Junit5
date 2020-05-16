package com.in28mintues.junit5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class TestPractice {
	
	private String test;
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@RepeatedTest(10)
	void contains_basic() {
		assertFalse("abcdefgh".contains("ijk"));
	}
	
	@Test
	@Disabled
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(5), () -> {
			for(int i = 0; i <=10000; i++) {
				int j = i;			
				System.out.println(j);
			}		
		}
	  );
	}
	
	@Test
	@RepeatedTest(10)
//	テストを実行しない　＝　不要なテストを飛ばして実行スピードをあげることができる
	@Disabled //Junit4では@Ignored
	void contains_basic_disable() {
		assertFalse("abcdefgh".contains("ijk"));
	}
	
//	非 static である必要があるので、必然的に @BeforeAll, @AfterAll はそのままでは指定できない
//	どうしても指定したい場合は、後述のテストインスンタンスのライフサイクルの指定で PER_CLASS を設定する必要がある
	@Nested
	@DisplayName("For an empty Test")
	class EmptyStringTest {
		@BeforeEach
		void setToEmpty() {
			test = "";
		}
		
		@Test
		void uppercaseIsEmpty() {
			assertEquals("",test.toUpperCase());
		}
		
		@Test
		void lengthIsZero() {
			assertEquals(0,test.length());
		}
	}
	
}
