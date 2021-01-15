package com.techelevator;

import java.util.Scanner;


/*
 * 1. Take user input.
 * 2. Use that input to do something
 * 3. Display a response
 */
public class CatPackingCalculator {

	public static void main(String[] args) {
		
		// what are box dimensions? l, w, h
		// calculate the volume of box l*w*h
		// determine how many cats can fit
		// give answer
		
		double averageVolumeOfHouseCat = 0.083;
		double estimatedPackingDensityOfCat = 0.5;
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("What is the length of the container in feet? ");
		String lengthOfContainer = input.nextLine();
		double containerLengthInFeet = Double.parseDouble(lengthOfContainer);
		
		System.out.print("What is the height of the container in feet? ");
		String heightOfContainer = input.nextLine();
		double containerHeightInFeet = Double.parseDouble(heightOfContainer);
		
		System.out.print("What is the width of the container in feet? ");
		String widthOfContainer = input.nextLine();
		double containerWidthInFeet = Double.parseDouble(widthOfContainer);
		
		double containerVolume = containerLengthInFeet
								* containerHeightInFeet
								* containerWidthInFeet;
		double catsByVolume = containerVolume / averageVolumeOfHouseCat;
		double packedCats = catsByVolume * estimatedPackingDensityOfCat;
		System.out.println();
		
		System.out.printf("%-15s %-15s %-15s %-25s%n", "Height", "Width", "Length", "Cats in Box");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.printf("%-15s %-15s %-15s %6.2f %n", containerLengthInFeet, containerWidthInFeet, containerHeightInFeet, packedCats);
		
	}
	

}
