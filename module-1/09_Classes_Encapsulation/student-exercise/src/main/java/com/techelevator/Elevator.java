package com.techelevator;

public class Elevator {
	
	public Elevator(int numberOfLevels) {
		this.numberOfLevels = numberOfLevels;
		// how many floors are available
	}
	private final static int DEFAULT_FLOOR = 1;
	
	private int numberOfLevels;
	private int currentFloor = 1;
	private int numberOfFloors = numberOfLevels;
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
		
		if (doorOpen) {
			if (numberOfLevels >= desiredFloor) {
				this.currentFloor = desiredFloor;
			}
		}
	}
	public void goDown(int desiredFloor) {
		if (isDoorOpen()) {
			if (desiredFloor < 1){
				this.currentFloor = DEFAULT_FLOOR;
			}
			else {
				if (numberOfLevels <= desiredFloor) {
				this.currentFloor = desiredFloor;
			}
		}
		
	}
	
}
}
