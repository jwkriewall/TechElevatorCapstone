package com.techelevator;

import java.util.Map;

import org.junit.*;

public class WordCountTest {
	
	private WordCount wordCount;
	
	@Before
	public void setup() {
		wordCount = new WordCount();
	}
	
	@Test
	public void key_with_multiple_words() {
		String[] key1 = {"ba", "ba", "black", "sheep"};
		Map<String, Integer> result = wordCount.getCount(key1);
		Assert.assertEquals({"ba" : 2, "black" : 1, "sheep" : 1}, result);
	}

}
