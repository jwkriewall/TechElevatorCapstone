package com.techelevator.shapes;

public class Rectangle {
	
	// define State
	// called properties
	private int length;
	private int width;
	
	
	// How to instantiate a Rectangle object (how to build a rectangle)
	
	// no-argument default constructor is implicitly used by Java
			// unless an explicit constructor is set
	
	// below are two constructors. Created object will default to the constructor that will take it.
	public Rectangle() {}
	
	public Rectangle(int length, int width){
		this.length = length;
		this.width = width;
	}
	// Behavior
	// Public methods provide the way others use the object
	
	
	public boolean isLargerThan(Rectangle other) {
		boolean isLarger = calculateIfLarger(other);
		return isLarger;
	}
	
	public boolean isLargerThan(int otherWidth, int otherLength) {
		return this.getArea() > (otherWidth * otherLength);
	}
	
	
	private boolean calculateIfLarger(Rectangle other) {
		return this.getArea() > other.getArea();
	}
	
	@Override
	public boolean equals(Object other) {
		Rectangle otherRectangle = (Rectangle) other;
		return otherRectangle.getLength() == this.length && 
				otherRectangle.getWidth() == this.width;
	}
	
	@Override
	public String toString() {
		return this.width + " x " + this.length + " = " + this.getArea();
	}
	
	
	//Public, exact variable type (int here), get + variable Capitalized
	
	//get provides a way for other programs to retrieve the values
	// uses this.variable notation
	public int getLength() {
		return this.length;
	}
	public int getWidth() {
		return this.width;
	}
	
	// Set provides access to change the value or amount of a member variable.
	// what does void do?
	// void does the work, but doesn't give a response - NO RETURN VALUE
	
	public void setLength(int length) {
		//this.length refers to the definition on line 6.
		// length after the = refers to the length being passed into the program.
		this.length = Math.abs(length);
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	// a derived property is one that's calculated as needed. 
	// NOT STORED as member variable
	// calculated from existing member variables
	public int getArea() {
		return this.width * this.length;
	}
}
