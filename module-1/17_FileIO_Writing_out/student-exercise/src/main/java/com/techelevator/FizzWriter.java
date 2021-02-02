package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.Writer;

public class FizzWriter {
	
	private final static int UPPER_LIMIT = 300;
	
	private final static String FIZZ = "Fizz";
	private final static String BUZZ = "Buzz";

	public static void main(String[] args) throws IOException {
		
		int position = 1;
		
		String filePath = ("FizzBuzz.txt");
		
		File file = new File(filePath);
	
		
		try (PrintWriter FizzBuzz = new PrintWriter(file.getPath())){
			
			//FileWriter FizzBuzz = new FileWriter(file);
			
			//PrintWriter pw = new PrintWriter(FizzBuzz);
			
			BufferedWriter bufferedWriter = new BufferedWriter(FizzBuzz);
		
			while(position <= UPPER_LIMIT) {
			
				if (position % 5 == 0 && position % 3 == 0) {
					bufferedWriter.write(FIZZ + BUZZ + " ");
					position++;
				}
				else if (position % 5 == 0) {
					bufferedWriter.write(BUZZ + " ");
					position++;
				}
				else if (position % 3 == 0) {
					bufferedWriter.write(FIZZ + " ");
					position++;
				}
				else {
					bufferedWriter.write(position + " ");
					position++;
				}
				bufferedWriter.close();
				
			
			}
			
		}
		finally {
			System.out.println("FizzBuzz.txt created. ");
		}
		
	}
}

