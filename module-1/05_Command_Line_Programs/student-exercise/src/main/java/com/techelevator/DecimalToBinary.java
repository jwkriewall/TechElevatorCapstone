package com.techelevator;

import java.util.Scanner;

public class DecimalToBinary {
	
	//Write a command line program which prompts the user for a series of decimal integer values separated by spaces. 
	//Each decimal integer is displayed along with its equivalent binary value.

	//Please enter in a series of decimal values (separated by spaces): 460 8218 1 31313 987654321

	//460 in binary is  111001100
	//8218 in binary is 10000000011010
	//1 in binary is 1
	//31313 in binary is 111101001010001
	//987654321 in binary is 111010110111100110100010110001


	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		String result = "";
		
		System.out.print("Please enter in a series of decimal values (separated by spaces): ");
		String numbersArray = input.nextLine();
		String[] array = numbersArray.split(" ");
		
		int[] numbers = new int[array.length];	
		for(int i = 0;i < array.length;i++)
		{
			numbers[i] = Integer.parseInt(array[i]);
		}
		
		for (int i = 0; i < array.length; i++) {
			
		result = Integer.toBinaryString(numbers[i]);
		
		System.out.println(array[i] + " in binary is " + result);
		}

	}

}
