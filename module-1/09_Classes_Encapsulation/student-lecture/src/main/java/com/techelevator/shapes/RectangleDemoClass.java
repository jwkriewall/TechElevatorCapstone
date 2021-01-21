package com.techelevator.shapes;

public class RectangleDemoClass {

	public static void main(String[] args) {
		
		// below line creates new rectangle (AN OBJECT)
		Rectangle rect = new Rectangle();
		Rectangle rect2 = new Rectangle();
		
		
		
		
		// this new object can pass in parameters to the variable defined in the main class
		rect.setLength(10);
		rect.setWidth(15);
		
		rect2.setLength(1000);
		rect2.setWidth(20);

		
		System.out.println( rect.getLength());
		// Will print 10
		
		System.out.println(rect.getArea());
		
		System.out.println( rect.isLargerThan(rect2));
		
		// the following method is located in only the Rectangle Demo Class!
		boolean isLarger = rect2.isLargerThan(rect);
		isLarger = rect2.isLargerThan(50, 60);
		
		System.out.println(isLarger);
		
		Rectangle sameSizeAsRect1 = new Rectangle(rect.getLength(), rect.getWidth());
			if (rect.equals(sameSizeAsRect1)) {
				System.out.println("The Rectangles are equal");
			} else {
				System.out.println("The Rectangles are not equal");
			}
		System.out.println(rect.toString());
		
	}

}
