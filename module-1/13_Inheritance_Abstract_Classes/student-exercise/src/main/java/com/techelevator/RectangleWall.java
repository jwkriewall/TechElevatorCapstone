package com.techelevator;

public class RectangleWall extends Wall{
	
	private int length;
	private int height;
	
	private String name;
	private String color;
	
	// below line is a declaration of the Wall constructor
	// since we are extending wall, we also need to create a Wall constructor
	
	
	public RectangleWall (String name, String color, int length, int height) {
		// super references Wall constructor 
		// Wall constructor passes in two parameters to fulfill it's requirements
		// RectangleWall constructor needs a reference to Wall in order to pass.
		// Why couldn't I call it "Wall" instead of super?
		super(name, color);
		this.name = name;
		this.color = color;
		this.length = length;
		this.height = height;
	}

	public int getLength() {
		return length;
	}
	public int getHeight() {
		return height;
	}
	
	@Override
	public int getArea() {
		return (length * height);
	}
	
	// no idea what this means
	@Override
	public String toString() {
		return name + " (" + length + "x" + height + ") rectangle";
	}
}

