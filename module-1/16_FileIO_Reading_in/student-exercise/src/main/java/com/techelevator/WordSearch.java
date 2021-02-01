package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class WordSearch {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Scanner wordInput = new Scanner(System.in);
		
		System.out.print("What is the file that should be searched? ");
		String textFileLocation = input.nextLine();
		
		File file = new File(textFileLocation);
		

		if (file.isFile()) {
			
			System.out.print("What is the search word you are looking for? ");
			String word = wordInput.nextLine();	
			
	
			Scanner fileScanner = null;
			try {
				fileScanner = new Scanner(new File(textFileLocation));
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
				e.printStackTrace();
			
		
		
	
				int line = 0;
				while (fileScanner.hasNext()) {
					fileScanner.findAll(word);
					line++;
				
				}
				
			}
		
		}
	}
}
