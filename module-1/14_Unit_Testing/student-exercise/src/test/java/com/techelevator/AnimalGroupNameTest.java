package com.techelevator;

import org.junit.*;

public class AnimalGroupNameTest {
	
	private AnimalGroupName animalGroupName;
		
		@Before
		public void setup() {
			animalGroupName = new AnimalGroupName();
		}
		
		@Test
		public void rhino_returns_Crash() {
			String herdName = animalGroupName.getHerd("Rhino");
			Assert.assertEquals("Crash", herdName);
		}

}
