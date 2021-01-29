package com.techelevator;

import org.junit.*;

public class Lucky13Test {
	
	private Lucky13 lucky13;
	
	@Before
	public void setup() {
		lucky13 = new Lucky13();
	}

	@Test
	public void no_1_or_3() {
		int[] firstArray = {2,4,6};
		boolean result = lucky13.getLucky(firstArray);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void one_and_3() {
		int[] secondArray = {1,2,3};
		boolean result = lucky13.getLucky(secondArray);
		Assert.assertEquals(false,result);
	}
	
	@Test
	public void empty() {
		int[] firstArray = {};
		boolean result = lucky13.getLucky(firstArray);
		Assert.assertEquals(true, result);
	}
}
