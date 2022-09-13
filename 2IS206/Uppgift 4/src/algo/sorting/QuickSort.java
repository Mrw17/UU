package algo.sorting;

/**
 * Sort an array containing integers in ascending order
 * (arranged from smallest to largest) using the quick sort algorithm.
 */
public class QuickSort implements Sort {

	@Override
	public int[] sort(int[] unsorted) {
		quickSort(unsorted, 0, unsorted.length - 1);
		return unsorted;
	}
	/**
	 * Sort a part of the provided array between the two indexes.
	 * @param array the array we want to sort.
	 * @param low the lower index of the part we want to sort.
	 * @param high the higher index of the part we want to sort.
	 */
	private void quickSort(int[] array, int low, int high) {

		int i = low, j = high;		
		int pivot = array[low + (high-low)/2];

		while (i <= j) {			
			while (array[i] < pivot) {
				i++;
			}			
			while (array[j] > pivot) {
				j--;
			}			
			if (i <= j) {
				swap(array, i, j);
				i++;
				j--;
			}
		}
		
		if (low < j) {
			quickSort(array, low, j);
		}		
		if (i < high) {
			quickSort(array, i, high);
		}
	}
	/**
	 * Swap place of two elements in an array.
	 * 
	 * @param array the array we want to swap elements within.
	 * @param i the first element we want to swap.
	 * @param j the second element we want to swap.
	 */
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}