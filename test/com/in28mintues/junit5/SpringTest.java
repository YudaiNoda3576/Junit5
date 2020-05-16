package com.in28mintues.junit5;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SpringTest {
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
//	現在のクラス内にある「全」テストが実行される前・後に実行されるメソッド
//	staticメソッド
	@BeforeAll
	static void beforeAll() {
		System.out.println("Initialize connection to database");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("Initialize connection to database");
	}
	
//	現在のクラス内にある「各」テストが実行される前・後に実行されるメソッド
//	Junit4では　＠Before
	@BeforeEach
	void beforeEach() {
		System.out.println("Initialize Test Data for each test");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("Clean Up Test Data");
	}

	@Test
	void length_basic() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(actualLength, expectedLength);
	}
	
//	例外
//	Junit5以降はpublic不要
	@Test()
//	public 
//	Testの表示名を明示できる
	@DisplayName("配列の値がnullの場合、例外発生")
	void length_exception() {
		String str = null;
//		int actualLength = str.length();　ラムダ式で記述できる
		assertThrows(NullPointerException.class,
				() -> {
					str.length();
				});
	}
	
	@Test
	void upperCase() {
		String str = "abcd";
		String result = str.toUpperCase();
		assertEquals("ABCD", result);
		assertNotNull(result);
//		assertNull(result);
	}
	
	@Test
	void contains_basic() {
//		String str = "abcdefg";
//		boolean result = str.contains("ijk");
//		assertEquals(false, result);
//		1行にまとめられる
		assertFalse("abcdefg".contains("ijk"));
	}
	
	@Test
	void split_basic() {
		String str = "abc def efg";
		String actualResult[] = str.split(" ");
//		エラーが起こるテスト
//		String[]expectedResult = new String[] {"ab1", "def", "efg"};
		String[]expectedResult = new String[] {"abc", "def", "efg"};
//		順番注意　
		assertArrayEquals(expectedResult, actualResult);
	}
	
	@Test
	void length_greater_than_zero() {
		assertTrue("ABCD".length()>0);
		assertTrue("ABC".length()>0);
		assertTrue("A".length()>0);
		assertTrue("DEF".length()>0);
	}
//	上の処理を1行で行える @ParameterizedTest
	@DisplayName("パラメータが0以上か確認するテスト")
	@ParameterizedTest
	//@ValueSource以降にパラメータ
	@ValueSource(strings = {"ABCD", "ABC", "A", "DEF"})
	void length_greater_than_zero_using_parameterrized_test(String str) {	
		assertTrue(str.length()>0);
	}
	@Test
	void uppercase() {
		assertEquals("ABCD", "abcd".toUpperCase());
		assertEquals("ABC", "abc".toUpperCase());
		assertEquals("", "".toUpperCase());
		assertEquals("ABCDEFG", "abcdefg".toUpperCase());
	}
//	上の処理を1行で行う
	@ParameterizedTest(name = "{0} toUpperCase is {1}")
//	''でnullを表す
	@CsvSource(value = {"abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG"})
	void uppercase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());	
	}
//	@ParameterizedTestに名前をつける
	@ParameterizedTest(name = "{0} length is {1}" )
	@CsvSource(value = {"abcd, 4", "abc, 3", "'', 0", "abcdefg, 7"})
	void length(String word, int expectedLength) {
		assertEquals(expectedLength, word.length());	
	}
}
