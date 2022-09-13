package algo.sorting;

import algo.util.NumberCreator;
import algo.util.StopWatch;
/**
 * AlgoSorting invokes sorting algorithms with different sized data structures.
 */
public class AlgoSorting {
	/**
	 * Program entry point. Invokes sorting algorithms with different sizes.
	 * @param args not used.
	 */
	public static void main(String[] args) {
		Sort sortingAlgorithm = null;
		StopWatch stopWatch = new StopWatch();
		int[] dataStructure;
		int[] numberOfElements;
		long time;
					
		/* 
		 * Testing Insertion sort on different number of elements. 
		 */		
		numberOfElements = new int[] {40000, 80000, 120000, 160000, 200000};
		sortingAlgorithm = new InsertionSort();
		
		for(int i = 0; i < numberOfElements.length; i ++) {	
			dataStructure = NumberCreator.createNumberArray(numberOfElements[i]);
			stopWatch.start();		
			sortingAlgorithm.sort(dataStructure);	
			time = stopWatch.stop();		
			System.out.println("Sorting " + numberOfElements[i] + " elements using Insertion sort. Time: " + time);
		}	
		
		/* 
		 * Testing Drake sort on different number of elements. 
		 */		
		numberOfElements = new int[] {40000000, 80000000, 120000000, 160000000, 200000000};
		sortingAlgorithm = new DrakeSort();
		
		for(int i = 0; i < numberOfElements.length; i ++) {	
			dataStructure = NumberCreator.createNumberArray(numberOfElements[i]);
			stopWatch.start();		
			sortingAlgorithm.sort(dataStructure);	
			time = stopWatch.stop();		
			System.out.println("Sorting " + numberOfElements[i] + " elements using Drake sort. Time: " + time);
		}	
		
		/* 
		 * Testing Quick sort on different number of elements. 
		 */		
		numberOfElements = new int[] {4000000, 8000000, 12000000, 16000000, 20000000};
		sortingAlgorithm = new QuickSort();
		
		for(int i = 0; i < numberOfElements.length; i ++) {	
			dataStructure = NumberCreator.createNumberArray(numberOfElements[i]);
			stopWatch.start();		
			sortingAlgorithm.sort(dataStructure);	
			time = stopWatch.stop();		
			System.out.println("Sorting " + numberOfElements[i] + " elements using Quick sort. Time: " + time);
		}	
		
		/* 
		 * Testing Java API sort on different number of elements. 
		 */		
		numberOfElements = new int[] {4000000, 8000000, 12000000, 16000000, 20000000};
		sortingAlgorithm = new JavaAPISort();
		
		for(int i = 0; i < numberOfElements.length; i ++) {	
			dataStructure = NumberCreator.createNumberArray(numberOfElements[i]);
			stopWatch.start();		
			sortingAlgorithm.sort(dataStructure);	
			time = stopWatch.stop();		
			System.out.println("Sorting " + numberOfElements[i] + " elements using Java API sort. Time: " + time);
		}			
	}
}