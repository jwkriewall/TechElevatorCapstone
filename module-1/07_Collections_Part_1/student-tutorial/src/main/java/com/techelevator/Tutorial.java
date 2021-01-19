package com.techelevator;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Tutorial {
	
    public static void main(String[] args) {

    	// Step One: Declare a List
    	List<String> name = new ArrayList<String>();

    	
    	// Step Two: Add values to a List
    	
    	name.add("Ada");
    	name.add("Grace");
    	name.add("Margaret");
    	name.add("Adele");
    	
    	// Step Three: Looping through a List in a for loop
    	
    	for(int i = 0; i < name.size(); i++) {
    		System.out.println("The name at index " + i + " is equal to " + name.get(i));
    		
    	}

    	
    	// Step Four: Remove an item
    	
    	name.remove(0);

    	
    	// Step Five: Looping through List in a for-each loop
    	
    	for (String nameList : name) {
    		System.out.println("Name: " + nameList);
    	}

    }

}
