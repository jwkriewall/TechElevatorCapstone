package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class WordSearch {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input = new Scanner(System.in);
		Scanner wordInput = new Scanner(System.in);
		
		System.out.print("What is the file that should be searched? ");
		String textFileLocation = input.nextLine();
		
		File file = new File(textFileLocation);
		
				
				try {
				Scanner scanner = new Scanner(file);
				
				if (file.isFile()){
				
				System.out.print("What is the search word you are looking for? ");
				String word = wordInput.nextLine();	
				
				int count = 1;
				
				while (scanner.hasNextLine()) {
					
					String line = scanner.nextLine();
					if(line.contains(word)) {
						System.out.println(count + ") " + line);
						
					}
					count++;
					
				}

			}
		
		}
		finally {
			
		}
	}
}
