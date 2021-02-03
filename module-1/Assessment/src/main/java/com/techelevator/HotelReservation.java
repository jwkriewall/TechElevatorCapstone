package com.techelevator;

public class HotelReservation {
	
	String name;
	int numberOfNights;
	double estimatedTotal;

	public HotelReservation(String name, int numberOfNights) {
		this.name = name;
		this.numberOfNights = numberOfNights;
	}
	
	double rate = 59.99;
	double actualTotal = 0;

	
	public double calculateEstimatedTotal(int numberOfNights) {
		this.estimatedTotal = numberOfNights * rate; 
		return estimatedTotal;
	}
	
	public double actualTotal(String name, int numberOfNights) {
		boolean needsCleaning = true;
		boolean usedMinibar = true;
		
		double cleaningFee = 34.99;
		double minibarFee = 12.99;
		
		if (needsCleaning && usedMinibar) {
			estimatedTotal += (cleaningFee * 2);
		}
		
		if (needsCleaning) {
			estimatedTotal += cleaningFee;
		}
		if (usedMinibar) {
			estimatedTotal += minibarFee;
		}
		return estimatedTotal;
	}
	
	@Override
	public String toString() {
		return "RESERVATION - " + name + " - " + estimatedTotal;
	}

}
