package com.techelevator;

public class Airplane {
	
	public Airplane(String planeNumber, int totalFirstClassSeats, int totalCoachSeats) {
		
		this.planeNumber = planeNumber;
		this.totalFirstClassSeats = totalFirstClassSeats;
		this.totalCoachSeats = totalCoachSeats;
		
	}
	
	private String planeNumber;
	private int totalFirstClassSeats;
	private int totalCoachSeats;
	private int bookedFirstClassSeats;
	private int bookedCoachSeats;
	private int availableCoachSeats;

	
	public String getPlaneNumber() {
		return planeNumber;
	}
	public int getTotalFirstClassSeats() {
		return totalFirstClassSeats;
	}
	public int getTotalCoachSeats() {
		return this.totalCoachSeats;
	}
	public int getAvailableFirstClassSeats() {
		return this.totalFirstClassSeats - this.bookedFirstClassSeats;
	}
	public int getBookedFirstClassSeats() {
		return bookedFirstClassSeats;
	}
	public int getBookedCoachSeats() {
		return this.bookedCoachSeats;
	}
	public int getAvailableCoachSeats() {
		return this.totalCoachSeats - this.bookedCoachSeats;
	}
	
	
	
	
	public boolean reserveSeats(boolean forFirstClass, int totalNumberOfSeats) {
		
		// if forFirstClass = true, add totalNOS to bookedFirstClassS
		// if false, add totalnumberOS to BookedCoachSeats
		
		int totalSeatsOnPlane = this.totalFirstClassSeats + this.totalCoachSeats;
		int firstClassSeatsRemaining = this.totalFirstClassSeats - this.bookedFirstClassSeats;
		int coachSeatsRemaining = this.totalCoachSeats - this.bookedCoachSeats;
		
		if (totalNumberOfSeats < totalSeatsOnPlane)
				//(firstClassSeatsRemaining) >= (totalNumberOfSeats)) 
		{
			
			if (forFirstClass) {
				// below code should ask whether there are enough of the specific type of seat to book
				// there may be enough space on the plane, but not enough in the specific class;
				if (firstClassSeatsRemaining > totalNumberOfSeats) {
					this.bookedFirstClassSeats += totalNumberOfSeats;
					return true;
				}
			}
			if (!forFirstClass) {
				if(coachSeatsRemaining > totalNumberOfSeats) {
				this.bookedCoachSeats += totalNumberOfSeats;
				return true;
				}
			
				
			}
			
		}
		return false;
	}	

}
