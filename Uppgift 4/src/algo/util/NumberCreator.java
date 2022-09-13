package algo.util;

import java.util.ArrayList;
import java.util.List;
/**
 * Simple class to help construct a List with Integer values repeating the number pattern 0 to 9.
 */
public class NumberCreator {
	/**
	 * Create a List of Integer values repeating the number pattern 0 to 9.
	 * 
	 * @param length the number of numbers we want to generate.
	 * @return a List with Integers repeating the number pattern 0 to 9.
	 */
	public static final List<Integer> createNumberList(int length) {
		List<Integer> numberList = new ArrayList<Integer>(length);
				
		for(int i = 0; i < length; i ++) {
			numberList.add(i % 10);
		}		
		return numberList;
	}
	/**
	 * Create an Array of integer values repeating the number pattern 0 to 9.
	 * 
	 * @param length the number of numbers we want to generate.
	 * @return an array with integers repeating the number pattern 0 to 9.
	 */
	public static final int[] createNumberArray(int length) {
		int[] numberArray = new int[length];
				
		for(int i = 0; i < length; i ++) {
			numberArray[i] = i % 10;
		}		
		return numberArray;
	}
}