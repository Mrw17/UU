package algo.multiply;

import java.util.*;

import algo.util.NumberCreator;
import algo.util.StopWatch;
/**
 * Invokes algorithms with small sized data structures to help you establish that 
 * your algorithm implementations are correct before testing them with large size 
 * data structures.
 */
public class AlgoMultiplySmallSize {
	/**
	 * Program entry point. Invokes algorithms with different size 
	 * and type of data structure.
	 * @param args not used.
	 */
	public static void main(String[] args) {
		AlgorithmAndDatastructure test = new AlgorithmAndDatastructure();
		StopWatch stopWatch = new StopWatch();
		List<Integer> dataStructure;
		int[] numberOfElements;
		long sum, time;
				
		/* 
		 * Testing the method multiplyAll with different number of elements
		 * using the data structure ArrayList. If the algorithm is implemented
		 * correctly it should produce the following results:
		 * 4 000 elements -> Sum: 161 943 000
		 * 8 000 elements -> Sum: 647 886 000
		 * 12 000 elements -> Sum: 1 457 829 000
		 * 16 000 elements -> Sum: 2 591 772 000
		 * 20 000 elements -> Sum: 4 049 715 000
		 */		
		numberOfElements = new int[] {4000, 8000, 12000, 16000, 20000};
		
		for(int i = 0; i < numberOfElements.length; i ++) {	
			dataStructure = new ArrayList<Integer>(NumberCreator.createNumberList(numberOfElements[i]));
			stopWatch.start();		
			sum = test.multiplyAll(dataStructure);	
			time = stopWatch.stop();		
			System.out.println("Multiply all with " + numberOfElements[i] + " elements in ArrayList. Sum: " + sum + " Time: " + time);
		}	
		
		/* 
		 * Testing the method multiplyAll with different number of elements
		 * using the data structure LinkedList. If the algorithm is implemented
		 * correctly it should produce the following results:
		 * 100 elements -> Sum: 99 825
		 * 150 elements -> Sum: 225 675
		 * 200 elements -> Sum: 402 150
		 * 250 elements -> Sum: 629 250
		 * 300 elements -> Sum: 906 975
		 */		
		numberOfElements = new int[] {100, 150, 200, 250, 300};
		
		for(int i = 0; i < numberOfElements.length; i ++) {	
			dataStructure = new LinkedList<Integer>(NumberCreator.createNumberList(numberOfElements[i]));
			stopWatch.start();		
			sum = test.multiplyAll(dataStructure);	
			time = stopWatch.stop();		
			System.out.println("Multiply all with " + numberOfElements[i] + " elements in LinkedList. Sum: " + sum + " Time: " + time);
		}	
		
		/* 
		 * Testing the method multiplySome with different number of elements
		 * using the data structure ArrayList. If the algorithm is implemented
		 * correctly it should produce the following results:
		 * 10 000 000 elements -> Sum: 60 000 000
		 * 15 000 000 elements -> Sum: 90 000 000
		 * 20 000 000 elements -> Sum: 120 000 000
		 * 25 000 000 elements -> Sum: 150 000 000
		 * 30 000 000 elements -> Sum: 180 000 000 
		 */	
		numberOfElements = new int[] {10000000, 15000000, 20000000, 25000000, 30000000};
		
		for(int i = 0; i < numberOfElements.length; i ++) {	
			dataStructure = new ArrayList<Integer>(NumberCreator.createNumberList(numberOfElements[i]));
			stopWatch.start();		
			sum = test.multiplySome(dataStructure);	
			time = stopWatch.stop();		
			System.out.println("Multiply some with " + numberOfElements[i] + " elements in ArrayList. Sum: " + sum + " Time: " + time);
		}	
	
		/* 
		 * Testing the method multiplySome with different number of elements
		 * using the data structure LinkedList. If the algorithm is implemented
		 * correctly it should produce the following results:
		 * 5 000 elements -> Sum: 30 000
		 * 10 000 elements -> Sum: 60 000
		 * 15 000 elements -> Sum: 90 000
		 * 20 000 elements -> Sum: 120 000
		 * 25 000 elements -> Sum: 150 000 
		 */			
		numberOfElements = new int[] {5000, 10000, 15000, 20000, 25000};
		
		for(int i = 0; i < numberOfElements.length; i ++) {	
			dataStructure = new LinkedList<Integer>(NumberCreator.createNumberList(numberOfElements[i]));
			stopWatch.start();		
			sum = test.multiplySome(dataStructure);	
			time = stopWatch.stop();		
			System.out.println("Multiply some with " + numberOfElements[i] + " elements in LinkedList. Sum: " + sum + " Time: " + time);
		}		
	}
}