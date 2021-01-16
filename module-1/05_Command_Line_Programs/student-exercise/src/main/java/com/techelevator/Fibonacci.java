package com.techelevator;

import java.util.Scanner;

public class Fibonacci {
	
	//By definition, the first two numbers in the Fibonacci sequence are 0 and 1, 
	//and each subsequent number is the sum of the previous two.

	//Write a command line program which prompts the user for an integer value 
	//and display the Fibonacci sequence leading up to that number.
	
	//Please enter the Fibonacci number: 25

	//0, 1, 1, 2, 3, 5, 8, 13, 21

	public static void main(String[] args) {
		
		int firstPosition = 0;
		int secondPosition = 1;
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter the Fibonacci number: ");
		String numberInput = input.nextLine();
		int number = Integer.parseInt(numberInput);
		
		for (int i = 0; i < number; i++) {
			
			while (firstPosition <= number) {	
			
			int newSum = firstPosition + secondPosition;
			System.out.print(firstPosition + " ");
			firstPosition = secondPosition;	
			secondPosition = newSum;
			}
		}
	}
}
