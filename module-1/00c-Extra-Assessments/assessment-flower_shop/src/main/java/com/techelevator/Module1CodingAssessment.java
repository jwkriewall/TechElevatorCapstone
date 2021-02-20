package com.techelevator;

import java.util.Scanner;

public class Module1CodingAssessment {

	public static void main(String[] args) {
		
		Scanner orderSpecs = new Scanner(System.in);
		
		System.out.println("How many roses would you like to order? ");
		String rosesOrdered = orderSpecs.nextLine();
		int numberOfRoses = Integer.parseInt(rosesOrdered);
		
		System.out.println("What style of bouquet would you like? ");
		String typeOfBouquet = orderSpecs.nextLine();
		
		
		FlowerShopOrder newOrder = new FlowerShopOrder();
		newOrder.order(typeOfBouquet, numberOfRoses);
		System.out.println(newOrder.toString() + newOrder.subtotal(typeOfBouquet, numberOfRoses));

	}

}
