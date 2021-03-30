package com.techelevator;

public class Elevator {
	
	int numberOfFloors;
	int currentFloor;
	boolean doorOpen;
	
	private final int DEFAULT_FLOOR = 1;
	
	public Elevator(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
		// numberOfLevels = how many floors are available
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public boolean isDoorOpen() {
		return doorOpen;
	}
	
	public void openDoor() {
		this.doorOpen = true;
	}
	
	public void closeDoor() {
		this.doorOpen = false;
	}
	public void goUp(int desiredFloor) {
		if (desiredFloor <= numberOfFloors && doorOpen == false) {
			currentFloor = desiredFloor;
		}
	}
	
	public void goDown(int desiredFloor) {
		if (desiredFloor >= DEFAULT_FLOOR && desiredFloor < currentFloor && doorOpen == false) {
			currentFloor = desiredFloor;
		}
	}

}
