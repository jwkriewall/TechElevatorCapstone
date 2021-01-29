package com.techelevator;

import org.junit.*;

public class Less20Test {
	
	private Less20 less20;
	
	@Before
	public void setup() {
		less20 = new Less20();
	}
	
	@Test
	public void two_less_than_multiple() {
		boolean result = less20.isLessThanMultipleOf20(38);
		Assert.assertTrue(result);
	}
	
	@Test
	public void one_more_than_multiple() {
		boolean result = less20.isLessThanMultipleOf20(21);
		Assert.assertFalse(result);
	}
	@Test
	public void zero() {
		boolean result = less20.isLessThanMultipleOf20(0);
		Assert.assertFalse(result);
	}
	@Test
	public void three_more_than_multiple() {
		boolean result = less20.isLessThanMultipleOf20(83);
		Assert.assertFalse(result);
	}
	@Test
	public void negative_number() {
		boolean result = less20.isLessThanMultipleOf20(-4);
		Assert.assertFalse("Must use non-negative number",result);
	}
	
	
}
