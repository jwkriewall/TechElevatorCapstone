package com.techelevator;

public class TriangleWall extends Wall{
	
	static String name;
	static String color;
	private int base;
	private int height;

	public TriangleWall(String name, String color, int base, int height) {
		super(name, color);
		this.name = name;
		this.color = color;
		this.base = base;
		this.height = height;
	
	}

	public int getBase() {
		return base;
	}
	public int getHeight() {
		return height;
	}
	
	public int getArea() {
		return (base * height) / 2;
	}
	
	public String toString() {
		return name + " (" + base + "x" + height + ") triangle";
	}
}
