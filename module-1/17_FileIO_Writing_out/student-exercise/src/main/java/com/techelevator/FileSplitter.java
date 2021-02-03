package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileSplitter {

	public static void main(String[] args) {
		
		Scanner fileInput = new Scanner(System.in);
		Scanner numberOfFiles = new Scanner(System.in);
		
		System.out.print("Where is the input file? (please include path to file)? ");
		String file = fileInput.nextLine();
		
		System.out.print("How many lines of text (max) should there be in the split files? ");
		String number = numberOfFiles.nextLine();
		int numberAsInt = Integer.parseInt(number);
		
		// accept input
		// count the number of lines in the file (Scanner)
		// print Generating input-1.txt
		// for each line in the file
		
		File fileName = new File (file);
		
		if (!fileName.exists()) {
			System.out.println("File does not exist. Try again.");
		}
		
		int lineCount = 1;

		try(Scanner fileScanner = new Scanner(fileName)){
		
			
			while (fileScanner.hasNextLine()) {
				lineCount++;
				// will this loop?
				// it should, and also update lineCount for later use.
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try (PrintWriter newFiles = new PrintWriter(fileName); 
				BufferedWriter bufferedWriter = new BufferedWriter(newFiles)){
			
			int linesToPrint = lineCount / numberAsInt;
			
			for (int i = 1; i <= linesToPrint; i++) {
				bufferedWriter.write("Generating " + (fileName.length() - 4) + "-" + i + ".txt");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
