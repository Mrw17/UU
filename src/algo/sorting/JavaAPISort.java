package algo.sorting;
import  java.util.Arrays;

/**
 * Sort an array containing integers in ascending order
 * (arranged from smallest to largest) using Java API.
 */
public class JavaAPISort implements Sort {

	@Override
	public int[] sort(int[] unsorted) {
		Arrays.sort(unsorted);
		return unsorted;
	}
}