package com.techelevator;

import org.junit.*;

public class NonStartTest {
	
	private NonStart nonStart;
	
	@Before
	public void setup() {
		nonStart = new NonStart();
	}
	
	@Test
	public void two_strings_length_2() {
		String string1 = "in";
		String string2 = "ra";
		
		String result = nonStart.getPartialString(string1, string2);
		Assert.assertEquals("na", result);
	}

	
	@Test
	public void two_strings_length_1() {
		String string1 = "o";
		String string2 = "k";
		
		String result = nonStart.getPartialString(string1, string2);
		Assert.assertEquals("", result);
	}
	
//	@Test
//	public void two_strings_length_0() {
//		String string1 = "";
//		String string2 = "";
//		
//		String result = nonStart.getPartialString(string1, string2);
//		Assert.assertEquals("", result);
//	}
//  How can I test for someone who entered a blank string? Would that best be prevented in the main application?
	
	@Test
	public void two_Strings_with_numbers() {
		String string1 = "fourteen14";
		String string2 = "one1";
		
		String result = nonStart.getPartialString(string1, string2);
		Assert.assertEquals("ourteen14ne1", result);
	}



}
