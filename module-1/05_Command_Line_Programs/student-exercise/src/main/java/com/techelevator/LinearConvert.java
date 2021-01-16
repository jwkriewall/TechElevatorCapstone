package com.techelevator;

import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {

		//Write a program that converts meters to feet and vice-versa.
		//The foot to meter conversion formula is: f * 0.3048
		//The meter to foot conversion formula is: m * 3.2808399
		
		//Write a command line program which prompts a user to enter a length, and whether the measurement is in (m)eters or (f)eet. 
		//Convert the length to the opposite measurement, and display the old and new measurements to the console.
		
		//Please enter the length: 58
		//Is the measurement in (m)eter, or (f)eet? f
		//58f is 17m.
		
		Scanner input = new Scanner(System.in);
	
		System.out.print("Please enter the length: ");
		String lengthInput = input.nextLine();
		double length = Double.parseDouble(lengthInput);
			
		System.out.print("Is the measurement in (m)eter or (f)eet? ");
		String  mOrF = input.nextLine();
			
		double conversionToFeet = length * 3.2808399;
		double conversionToMeters = length * 0.3048;
			
		if (mOrF.equals("m")) {
			System.out.println((int) length + "m is " + (int) conversionToFeet + "f.");
		}
			
		if (mOrF.equals("f")) {
			System.out.println((int) length + "f is " + (int) conversionToMeters + "m.");
		}
	}
}
