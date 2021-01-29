package com.techelevator;

import org.junit.*;

public class MaxEnd3Test {
	
	private MaxEnd3 maxEnd3;
	
	@Before
	public void setup() {
		maxEnd3 = new MaxEnd3();
	}
	
	@Test
	public void first_number_bigger() {
		int[] array1 = {11, 2, 3};
		int[] array2 = {11, 11, 11};
		int[] result = maxEnd3.makeArray(array1);
		Assert.assertArrayEquals(array2, result);
	}

	@Test
	public void last_number_bigger() {
		int[] array1 = {11, 2, 3, 44};
		int[] array2 = {44, 44, 44, 44};
		int[] result = maxEnd3.makeArray(array1);
		Assert.assertArrayEquals(array2, result);
	}
	
	@Test
	public void all_numbers_same() {
		int[] array1 = {1, 1, 1};
		int[] array2 = {1, 1, 1};
		int[] result = maxEnd3.makeArray(array1);
		Assert.assertArrayEquals(array2, result);
	}


}
