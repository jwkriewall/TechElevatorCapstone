package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HotelReservationTest {
	
	private HotelReservation hotelReservation;
	
	String name;
	int numberOfNights;
	
	@Before
	public void setup() {
		hotelReservation = new HotelReservation(name, numberOfNights);
	}
	
	@Test
	public void guestOneNight() {
		// Arrange
		// Act
		double result = hotelReservation.actualTotal("John",1);
		// Assert
		Assert.assertEquals(59.99, result);
	}


}
