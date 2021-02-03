package com.techelevator;

import org.junit.Before;
import org.junit.Test;

public class HotelReservationTest {
	
	private HotelReservation HotelReservation;
	
	String name;
	int numberOfNights;
	
	@Before
	public void setup() {
		HotelReservation = new HotelReservation(name, numberOfNights);
	}
	
	@Test
	public void guestOneNight() {
		// Arrange
		// Act
		boolean result = HotelReservation.actualTotal("John",1);
		// Assert
		Assert.assertEquals(59.99);
	}


}
