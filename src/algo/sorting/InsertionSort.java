package algo.sorting;

/**
 * Sort an array containing integers in ascending order
 * (arranged from smallest to largest) using the insertion sort algorithm.
 */
public class InsertionSort implements Sort {

	@Override
	public int[] sort(int[] unsorted) {
		int findSpotInArray = 0;
		int unsortedElement = 0;

		//Goes through whole array
		for(int i = 1; i <= (unsorted.length-1); i++){
			unsortedElement = unsorted[i];
			findSpotInArray = i;

			//Swapping elements if an element from the unsorted area
			//is smaller then last element in the sorted area
			while(unsorted[findSpotInArray-1] > unsortedElement && findSpotInArray >=1){
				unsorted[findSpotInArray] = unsorted[findSpotInArray-1];
				findSpotInArray--;
			}
				unsorted[findSpotInArray] = unsortedElement;
		}
		return unsorted;
	}

}