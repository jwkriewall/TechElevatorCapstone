package com.techelevator;

public class FruitTree {
	
	// Parameters passed through a method must use the values
	
	public FruitTree(String typeOfFruit, int startingPiecesOfFruit) {
		this.typeOfFruit = typeOfFruit;
		this.piecesOfFruitLeft = startingPiecesOfFruit;
	}
		
	private String typeOfFruit;
	private int piecesOfFruitLeft;
	
	
	
	
	public String getTypeOfFruit() {
		return this.typeOfFruit;
	}
	
	public int getPiecesOfFruitLeft() {
		return this.piecesOfFruitLeft;
	}
	
	

	
	public boolean pickFruit(int numberOfPiecesToRemove) {
		if (this.piecesOfFruitLeft >= numberOfPiecesToRemove) {
			this.piecesOfFruitLeft = (piecesOfFruitLeft - numberOfPiecesToRemove);
			return true;
		}
		return false;
	}
	
}
