package com.techelevator;

public class SquareWall extends RectangleWall{
	
	private String name;
	private String color;
	private int sideLength;
	
	// below variables needed to be static instead of private. Why?
	static int length;
	static int height;
	

	public SquareWall(String name, String color, int sideLength) {
		super(name, color, length, height);
		this.name = name;
		this.color = color;
		this.sideLength = sideLength;	
	}
	
//	@Override 
//	public int getHeight() {
//		return height;
//	}
	
	@Override 
	public int getArea(){
		return sideLength * sideLength;
	}
	
	@Override
	public String toString() {
		return name + " (" + sideLength + "x" + sideLength + ") square";
	}

}
