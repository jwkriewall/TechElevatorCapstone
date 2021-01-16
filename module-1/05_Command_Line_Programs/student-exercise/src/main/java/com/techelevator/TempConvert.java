package com.techelevator;

import java.util.Scanner;

//Please enter the temperature: 58
//Is the temperature in (C)elsius, or (F)ahrenheit?
//58F is 14C.

//Write a command line program which prompts a user to enter a temperature, and whether it's in degrees (C)elsius or (F)ahrenheit. 
//Convert the temperature to the opposite degrees, and display the old and new temperatures to the console.


public class TempConvert {

	public static void main(String[] args) {	
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter the temperature: ");
		String  tempLevel = input.nextLine();
		double temperature = Double.parseDouble(tempLevel);
		
		System.out.print("Is the temperature in (C)elcius or (F)ahrenheit? ");
		String celciusOrFahrenheit = input.nextLine();
		
		double celciusConversion = temperature * 1.8 + 32;
		double fahrConversion = (temperature - 32) / 1.8;
		
		if (celciusOrFahrenheit.equals("F")) {
			System.out.println((int)temperature + "F is " + (int) fahrConversion + " C.");
		}
		if (celciusOrFahrenheit.equals("C")) {
			System.out.println((int)temperature + "C is " + (int) celciusConversion + " F.");
		}
	}

}
