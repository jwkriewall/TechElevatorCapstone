package com.techelevator.trees;

import org.junit.*;

public class FruitTreeTest {
	
	private FruitTree fruitTree;
	
	@Before
	public void setup() {
		fruitTree = new FruitTree("Apples", 10);
	}
	
	/*
	 * Verify constructor with different values (type of fruit and number)
	 * Pick more fruit than tree has
	 * Pick exact amount of fruit
	 * Pick a negative amount
	 * Pick an allowed amount
	 * Pick 0 fruit
	 * 
	 * pick allowed amount twice
	 * pick allowed amount, then more than allowed
	 */
	
	@Test 
	public void tree_has_correct_fruit() {
		Assert.assertEquals("Apples", fruitTree.getTypeOfFruit());
	}

	@Test 
	public void tree_has_correct_amount_of_fruit() {
		Assert.assertEquals(10, fruitTree.getPiecesOfFruitLeft());
	}
	
	@Test 
	public void pick_too_much_fruit() {
		// Arrange - nothing to arrange
		// Act
		Assert.assertFalse(fruitTree.pickFruit(11));
		// Assert
		Assert.assertEquals(10, fruitTree.getPiecesOfFruitLeft());
	}
	
	@Test 
	public void pick_exact_amount_of_fruit() {
		// Arrange - nothing to arrange
		// Act
		Assert.assertTrue(fruitTree.pickFruit(10));
		// Assert
		Assert.assertEquals(0, fruitTree.getPiecesOfFruitLeft());
	}
	
	@Test 
	public void pick_allowed_amount_of_fruit() {
		// Arrange - nothing to arrange
		// Act
		Assert.assertTrue(fruitTree.pickFruit(6));
		// Assert
		Assert.assertEquals(4, fruitTree.getPiecesOfFruitLeft());
	}
	
	@Test 
	public void pick_0_fruit() {
		// Arrange - nothing to arrange
		// Act
		Assert.assertTrue(fruitTree.pickFruit(0));
		// Assert
		Assert.assertEquals(10, fruitTree.getPiecesOfFruitLeft());
	}
	
	@Test 
	public void pick_negative_amount_of_fruit() {
		// Arrange - nothing to arrange
		// Act
		Assert.assertFalse("pickFruit returned True", fruitTree.pickFruit(-1));
		// Assert
		Assert.assertEquals("Tree has wrong amount of fruit: " + fruitTree.getPiecesOfFruitLeft(), 10, fruitTree.getPiecesOfFruitLeft());
	}
	
	@Test 
	public void pick_allowed_amount_twice() {
		// Arrange
		fruitTree.pickFruit(3);
		// Act
		boolean result = (fruitTree.pickFruit(4));
		// Assert
		Assert.assertTrue("pickFruit returned false", result);
		Assert.assertEquals(3, fruitTree.getPiecesOfFruitLeft());
	}
	
	@Test 
	public void pick_allowed_amount_twice_then_more_than_allowed() {
		
		int startingFruit = fruitTree.getPiecesOfFruitLeft();
		int firstPick = 3;
		int secondPick = 4;
		int overPick = startingFruit - firstPick - secondPick + 1;
		int expectedRemaining = startingFruit - firstPick - secondPick;
		// Arrange
		fruitTree.pickFruit(3);
		fruitTree.pickFruit(4);
		// Act
		boolean result = (fruitTree.pickFruit(overPick));
		
		// Assert
		Assert.assertFalse(result);
		Assert.assertEquals(expectedRemaining, fruitTree.getPiecesOfFruitLeft());
	}
	
	@Test
	public void can_hold_different_fruit() {
		// Arrange is going to be to create a new fruit tree
		FruitTree pearTree = new FruitTree("Pears", 5);
		// Act
		String fruitType = pearTree.getTypeOfFruit();
		Assert.assertEquals("Pears", fruitType);
	}
}
