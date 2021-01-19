package com.techelevator;

import java.util.Scanner;

//public class DecimalToBinary {
	
	//Write a command line program which prompts the user for a series of decimal integer values separated by spaces. 
	//Each decimal integer is displayed along with its equivalent binary value.

	//Please enter in a series of decimal values (separated by spaces): 460 8218 1 31313 987654321

	//460 in binary is  111001100
	//8218 in binary is 10000000011010
	//1 in binary is 1
	//31313 in binary is 111101001010001
	//987654321 in binary is 111010110111100110100010110001


	//public static void main(String[] args) {
		
		//Scanner input = new Scanner(System.in);
		
		//String result = "";
		
		//System.out.print("Please enter in a series of decimal values (separated by spaces): ");
		//String numbersArray = input.nextLine();
		//String[] array = numbersArray.split(" ");
		
		//int[] numbers = new int[array.length];	
		//for(int i = 0;i < array.length;i++)
		//{
		//	numbers[i] = Integer.parseInt(array[i]);
		//}
		
		//for (int i = 0; i < array.length; i++) {
			
		//result = Integer.toBinaryString(numbers[i]);
		
		//System.out.println(array[i] + " in binary is " + result);
		//}
	//}
//}
	
	
	//Store the remainder when the number is divided by 2 in an array.
	//Divide number by 2
	//Repeat the above two steps until the remaining number is greater than zero.
	//Print the array in reverse order now.
	
public class DecimalToBinary {
	
	public static void main(String[] args) {
	
	Scanner input = new Scanner(System.in);
	
		
		
		int[] binary = new int[20];
			
		System.out.print("Please enter in a series of decimal values (separated by spaces): ");
		String numbersArray = input.nextLine();
		String[] array = numbersArray.split(" ");
		
		int[] numbers = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			numbers[i] = Integer.parseInt(array[i]);
		}
		
		for (int i = 0; i < numbers.length; i++) {
			while (numbers[i] >= 0) {
				binary[i++] = numbers[i] % 2;
				numbers[i] = numbers[i] / 2;
			}
		}
		for (int i = 0; i < numbers.length; i++) {
			
			for (int j = i - 1; j >= 0; j--) {
				System.out.println(binary[j]);
			}
		}
	}
}


