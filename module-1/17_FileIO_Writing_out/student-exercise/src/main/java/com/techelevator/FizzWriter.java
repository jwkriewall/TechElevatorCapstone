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
	private final static String FILE_NAME = "FizzBuzz.txt";

	public static void main(String[] args) throws IOException {
		
//		int position = 1;
//		
//		String filePath = ("FizzBuzz2.txt");
//		
//		File file = new File(filePath);
//		file.createNewFile();
//	
//		
//		try (PrintWriter FizzBuzz = new PrintWriter(file);	
//			BufferedWriter bufferedWriter = new BufferedWriter(FizzBuzz)) {
//		
//			while(position <= UPPER_LIMIT) {
//			
//				if (position % 5 == 0 && position % 3 == 0) {
//					bufferedWriter.write(FIZZ + BUZZ + " ");
//					position++;
//				}
//				else if (position % 5 == 0) {
//					bufferedWriter.write(BUZZ + " ");
//					position++;
//				}
//				else if (position % 3 == 0) {
//					bufferedWriter.write(FIZZ + " ");
//					position++;
//				}
//				else {
//					bufferedWriter.write(position + " ");
//					position++;
//				}
//				bufferedWriter.close();
//				
//			
//			}
//			
//		} 
//			System.out.println("FizzBuzz.txt created. ");	 
//		
//	}
//}
		
	File file = new File(FILE_NAME);
	file.createNewFile();
	
	try (PrintWriter printWriter = new PrintWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
		
		for (int i = 1 ; i <= UPPER_LIMIT ; i++) {
			bufferedWriter.write(getOutput(i) + ("\n")); 
		}
	}

	System.out.println(FILE_NAME + " created. ");
}


	private static String getOutput(int i) {
	
		String textToPrint = String.valueOf(i);
		
		if (i % 5 == 0 && i % 3 == 0) {
			textToPrint = FIZZ + BUZZ;
		} else if (i % 5 == 0) {
			textToPrint = BUZZ;
		} else if (i % 3 == 0) {
			textToPrint = FIZZ;
		}
		return textToPrint;
	}
}

