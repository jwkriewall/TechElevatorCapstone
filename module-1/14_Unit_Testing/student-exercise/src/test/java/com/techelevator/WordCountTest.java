package com.techelevator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
		Map<String, Integer> test = new LinkedHashMap<String, Integer>();
		test.put("ba", 2);
		test.put("black", 1);
		test.put("sheep", 1);
		Assert.assertEquals(test, result);
	}
	
	@Test
	public void key_with_only_single_words() {
		String[] key1 = {"ba", "black", "sheep"};
		Map<String, Integer> result = wordCount.getCount(key1);
		Map<String, Integer> test = new LinkedHashMap<String, Integer>();
		test.put("ba", 1);
		test.put("black", 1);
		test.put("sheep", 1);
		Assert.assertEquals(test, result);
	}
	
	@Test
	public void key_with_muliple_double_words() {
		String[] key1 = {"ba", "ba", "black", "black", "sheep", "sheep"};
		Map<String, Integer> result = wordCount.getCount(key1);
		Map<String, Integer> test = new LinkedHashMap<String, Integer>();
		test.put("ba", 2);
		test.put("black", 2);
		test.put("sheep", 2);
		Assert.assertEquals(test, result);
	}
	
	@Test
	public void key_with_blank_string() {
		String[] key1 = {"", "", "black", "sheep"};
		Map<String, Integer> result = wordCount.getCount(key1);
		Map<String, Integer> test = new LinkedHashMap<String, Integer>();
		test.put("", 2);
		test.put("black", 1);
		test.put("sheep", 1);
		Assert.assertEquals(test, result);
	}
	
	@Test
	public void key_with_null_String() {
		String[] key1 = {null, "black", "sheep"};
		Map<String, Integer> result = wordCount.getCount(key1);
		Map<String, Integer> test = new LinkedHashMap<String, Integer>();
		test.put(null, 1);
		test.put("black", 1);
		test.put("sheep", 1);
		Assert.assertEquals(test, result);
	}

}
