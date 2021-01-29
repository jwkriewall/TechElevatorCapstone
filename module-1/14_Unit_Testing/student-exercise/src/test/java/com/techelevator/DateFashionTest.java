package com.techelevator;

import org.junit.*;

public class DateFashionTest {

	private DateFashion dateFashion;
	
	@Before
	public void setup() {
		dateFashion = new DateFashion();
	}
	
	@Test
	public void date_Lower_Than_2(){
		int result = dateFashion.getATable(5, 0);
			Assert.assertEquals(0, result);
	}
		
	@Test
	public void date_and_you_higher_Than_2_lower_than_8(){
		int result = dateFashion.getATable(5, 5);
			Assert.assertEquals(1, result);
	}
	
	@Test
	public void both_higher_than_7(){
		int result = dateFashion.getATable(9, 10);
			Assert.assertEquals(2, result);
	}
}
