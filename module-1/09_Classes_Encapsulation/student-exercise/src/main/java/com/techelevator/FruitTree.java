package com.techelevator;

public class FruitTree {
	
	public FruitTree(String typeOfFruit, int startingPiecesOfFruit) {}
		
	private String typeOfFruit;
	private int piecesOfFruitLeft;
	
	public String getTypeOfFruit() {
		return typeOfFruit;
	}
	
	
	public int getPiecesOfFruitLeft() {
		return this.piecesOfFruitLeft;
	}

	// startingPiecesOfFruit goes where?
	
	public boolean pickFruit(int numberOfPiecesToRemove) {
		if (this.piecesOfFruitLeft < numberOfPiecesToRemove) {
			this.piecesOfFruitLeft = (piecesOfFruitLeft - numberOfPiecesToRemove);
			return true;
		}
		return false;
	}
	
}
