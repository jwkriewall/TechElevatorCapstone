package com.techelevator;

import org.junit.*;

public class StringBitsTest {
	
	private StringBits stringBits;
	
	@Before
	public void setup() {
		stringBits = new StringBits();
	}
	
	@Test
	public void string_of_five() {
		String string1 = "Hello";
		String result = stringBits.getBits(string1);
		Assert.assertEquals("Hlo", result);
	}
	
	@Test
	public void string_of_one() {
		String string1 = "J";
		String result = stringBits.getBits(string1);
		Assert.assertEquals("J", result);
	}
	
	@Test
	public void string_of_none() {
		String string1 = "";
		String result = stringBits.getBits(string1);
		Assert.assertEquals("", result);
	}
	
	@Test
	public void string_of_numbers() {
		String string1 = "12345";
		String result = stringBits.getBits(string1);
		Assert.assertEquals("135", result);
	}

}
