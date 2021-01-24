package com.techelevator;

public class Elevator {
	
	public Elevator(int numberOfLevels) {
		this.numberOfLevels = numberOfLevels;
		// numberOfLevels = how many floors are available
	}
	private final static int DEFAULT_FLOOR = 1;

	private int numberOfLevels;
	private int currentFloor = DEFAULT_FLOOR;
	private int numberOfFloors;
	private boolean doorOpen;
	
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	public int getNumberOfFloors() {
		return this.numberOfFloors;
	}
	public boolean getDoorOpen() {
		return this.doorOpen;
	}
	
	
	
	public void openDoor() {
		this.doorOpen = true;
	}
	public void closeDoor() {
		this.doorOpen = false;
	}
	public boolean isDoorOpen() {
		if (this.doorOpen = true) {
			return true;
		}
		return false;
		
		
	}
	public void goUp(int desiredFloor) {
		
		
		// if elevator is closed
		// calculate if there are that many floors in building
		// if so, add number of floors to current floor to get new floor
		
		if (isDoorOpen()) {
			if (numberOfLevels >= desiredFloor) {
				this.currentFloor = desiredFloor;
				this.doorOpen = false;
				
			}
		}
	}
	public void goDown(int desiredFloor) {
		if (isDoorOpen()) {
			if (desiredFloor < 1){
				this.doorOpen = false;
				this.currentFloor = 1;
				
			}
			if (isDoorOpen()){
				if (numberOfLevels <= desiredFloor) {
				this.doorOpen = false;
				this.currentFloor = desiredFloor;
				
			}
		}
		
	}
	
}

}
