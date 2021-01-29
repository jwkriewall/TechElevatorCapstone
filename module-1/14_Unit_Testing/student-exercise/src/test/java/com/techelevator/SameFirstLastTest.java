package com.techelevator;

import org.junit.*;

public class SameFirstLastTest {
	
	private SameFirstLast sameFirstLast;
	
	@Before
	public void setup() {
		sameFirstLast = new SameFirstLast();
	}
	
	@Test
	public void array_length_3_first_last_same() {
		int[] array1 = {1, 2, 1};
		boolean result = sameFirstLast.isItTheSame(array1);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void array_length_1() {
		int[] array1 = {100};
		boolean result = sameFirstLast.isItTheSame(array1);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void array_length_0() {
		int[] array1 = {};
		boolean result = sameFirstLast.isItTheSame(array1);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void array_beginning_and_ending_different() {
		int[] array1 = {1, 2, 3, 4};
		boolean result = sameFirstLast.isItTheSame(array1);
		Assert.assertEquals(false, result);
	}

}
