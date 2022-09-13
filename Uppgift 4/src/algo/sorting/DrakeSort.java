package algo.sorting;

/**
 * Sort an array containing integers in ascending order
 * (arranged from smallest to largest) using the Drake sort algorithm.
 */
public class DrakeSort implements Sort {

	/**
	 * Takes an unsorted int array and sorts it based on DrakeSort
	 * @param unsorted the unsorted array.
	 * @return an sorted array
	 */
	@Override
	public int[] sort(int[] unsorted) {
		int highestNum = unsorted[0];
		int lowNum = unsorted[0];
		int[] sortedArr = new int[unsorted.length];
		int pointer = 0;


		// Finds highest number in unsorted array
		for(int i = 1; i < unsorted.length; i++){
			if(unsorted[i] > highestNum)
				highestNum = unsorted[i];
			else if(unsorted[i] < lowNum)
				lowNum = unsorted[i];
		}


		int[] freqArr = new int[highestNum + 1];

		//Counts freq on numbers and saves it to freqArr
		for(int i = 0; i < unsorted.length; i++){
			int num = unsorted[i];
			freqArr[num]++;
		}

		//Saves numbers to sortedArr based on their freq in freqArr
		for(int i = 0; i < (highestNum+1); i++){
			if(freqArr[i] == 0)
				continue;

			for(int j = freqArr[i]; 0 < j; j--){
				sortedArr[pointer] = i;
				pointer++;
			}
		}
		return sortedArr;
	}
}