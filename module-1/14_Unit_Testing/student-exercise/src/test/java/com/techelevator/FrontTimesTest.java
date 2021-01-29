package com.techelevator;

import org.junit.*;

public class FrontTimesTest {

	private FrontTimes frontTimes;
	
	@Before
	public void setup() {
		frontTimes = new FrontTimes();
	}
	
	@Test
	public void two_copies_of_front_three() {
		String result = frontTimes.generateString("Chocolate", 2);
		Assert.assertEquals("ChoCho", result);
	}
	@Test
	public void one_copies_of_front_three() {
		String result = frontTimes.generateString("Chocolate", 1);
		Assert.assertEquals("Cho", result);
	}
	@Test
	public void no_copies_of_front_three() {
		String result = frontTimes.generateString("Chocolate", 0);
		Assert.assertEquals("", result);
	}
	
}
