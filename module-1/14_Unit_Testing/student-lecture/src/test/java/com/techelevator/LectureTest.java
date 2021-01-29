package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;

public class LectureTest {
	
	/*
	 * A Private member variable to instantiate the class we are testing
	 */
	
	private Lecture lecture;
	
	/*
	 * Before runs before EACH TEST!
	 * Instantiate a new object of the class being tested so that every test
	 * starts from the same point / state.
	 */
	
	@Before
	public void setup() {
		lecture = new Lecture();
		
	}
	
	/*
	 * After runs after Each test
	 * After is used to cleanup after a test runs and often is not needed
	 */
	
	
	@After
	public void cleanup() {
		
	}
	/*
	 * Tests for or35
	 * multiple of 3 but not 5
	 * multiple of 5 but not 3
	 * multiple of 3 and 5
	 * not a multiple of 3 or 5
	 * Negative multiple of 3 or 5
	 */
	
	@Test
	public void or35_multiple_of_3_but_not_5() {
		// Arrange (if needed)
		// Act (Test)
		boolean result = lecture.or35(9);
		// Assert (Verify)
		Assert.assertTrue(result);
	}
	
	@Test
	public void or35_multiple_of_5_but_not_3() {
		// Arrange
		// Act
		// Assert
		boolean result = lecture.or35(15);
		Assert.assertTrue(result);
	}
	
	@Test
	public void or35_multiple_of_both_3_and_5() {
		// Arrange
		// Act
		boolean result = lecture.or35(15);
		// Assert
		Assert.assertTrue(result);
	}
	
	@Test
	public void or35_not_multiple_of_3_or_5() {
		//Arrange
		// Act
		boolean result = lecture.or35(14);
		// Assert
		Assert.assertFalse(result);
	}
	
	
	@Test
	public void or35_zero() {
		// Arrange
		// Act
		boolean result = lecture.or35(0);
		// Assert
		Assert.assertFalse(result);
	}
	
	@Test
	public void or35_negative_multiple_of_3_or_5() {
		boolean result = lecture.or35(-6);
		Assert.assertFalse(result);
	}
	
	
	
	@Test
	public void caughtspeeding_driving_68_when_not_birthday() {
		// Arrange
		// Act
		int result = lecture.caughtSpeeding(60,  false);
		// Assert
		Assert.assertEquals(0, result); // first, the value we expect, 2nd the value we returned
	}
	
	/*Tests for firstTwo
	 * Testing with a String returned (Assert equals with a string)
	 * exactly two characters
	 * null
	 * case sensitive
	 * empty string
	 * less than 2 characters
	 */
	@Test
	public void firstTwo_first_two_Characters_returned_for_String_Longer_Than_2() {
		String result = lecture.firstTwo("Hello");
		Assert.assertEquals("He", result);
	}
	
	/*
	 * No 4 Letter Words
	 * Testing with Lists or Arrays
	 * 
	 */
	@Test
	public void list_does_not_include_4Letter_words() {
		
		// Arrange
		String[] originalWords = {"Train", "Boat", "Car"};
		String[] expectedWords = {"Train", "Car"};
		
		// Act
		List <String> actualResult = lecture.no4LetterWords(originalWords);
		
		// Assert
		// Assert that the returned List of words is the same list of words in our expectedWords
		
		Assert.assertNotNull(actualResult);
		String[] actualResultsAsArray = actualResult.toArray(new String[actualResult.size()]);
		Assert.assertArrayEquals(expectedWords, actualResultsAsArray);
	}
	
	
	/*
	 * RobPeterToPayPaul
	 * Testing with Map (or other complex data structures)
	 */
	
	@Test
	public void peter_gives_half_ofmoney_to_paul() {
		// Arrange - create and populate the map to use as input
		Map <String, Integer> peterPaul = new HashMap<String, Integer>();
		peterPaul.put("Peter", 2000);
		peterPaul.put("Paul", 500);
		
		// Act
		Map<String, Integer> result = lecture.robPeterToPayPaul(peterPaul);
		
		// Assert
		// When using a collection with a primitive, assertEquals will not
		// autobox a value, so it must be instantiated explicitly
		Assert.assertEquals(new Integer(1000), result.get("Peter"));
		Assert.assertEquals(new Integer(1500), result.get("Paul"));
	}
	
	@Test
	public void third_of_9() {
		double result = lecture.thirdDouble(9);
		// assertEquals(expected, actual, delta)
		// delta is difference allowed between numbers
		
		Assert.assertEquals(3, result, 0);
	}
	@Test
	public void third_of_5() {
		double result = lecture.thirdDouble(5);
		Assert.assertEquals(1.66, result, .009);
	}
	
	/*
	 * Testing with our own objects
	 * 
	 */
	@Test
	public void dog_returns_the_same_dog() {
		
		// Arrange - contains the variables to be passed
		Dog ourDog = new Dog("Fiddo", "poodle", 7);
		// Act
		Dog returnedDog = lecture.returnOurDog(ourDog);
		// Assert
		// Assert Equals doesn't work unless the class has a .equals override because
		// it is 2 differnt dog objects with the SAME values
		assertDogEquals(ourDog, returnedDog);
		
	}
	
	private void assertDogEquals(Dog expected, Dog actual) {
		Assert.assertEquals(expected.getBreed(), actual.getBreed());
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getAge(), actual.getAge());
	}
	
}
