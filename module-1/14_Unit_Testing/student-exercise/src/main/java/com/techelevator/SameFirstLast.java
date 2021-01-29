package com.techelevator;

public class SameFirstLast {

    /*
     Given an array of ints, return true if the array is length 1 or more, and the first element and
     the last element are equal.
     IsItTheSame([1, 2, 3]) → false
     IsItTheSame([1, 2, 3, 1]) → true
     IsItTheSame([1, 2, 1]) → true
     */
    public boolean isItTheSame(int[] array1) {
    	if (array1 == null) return false;
    	if (array1.length <= 1) return false;
    	else return array1.length > 0 && array1[0] == array1[array1.length - 1];
    }

}
