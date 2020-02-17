package algo.multiply;

import java.util.List;

/**
 * Class containing method that tests execution time of algorithmic 
 * operations involving multiplication on Integers using different data structures. 
 */
public class AlgorithmAndDatastructure {	
	
	/**
	 * Construct a MultiplyAndDatastructure.
	 */
	public AlgorithmAndDatastructure() {	}
	
	/**
	 * Multiplies the first element from the beginning with the first element from 
	 * the end, the second element from the beginning with the second element from 
	 * the end etc and returns the sum of all multiplications. 
	 * 
	 * @param numberList a List containing Integers
	 * @return the sum
	 */
	public long multiplySome(List<Integer> numberList) {

		int tail = numberList.size();
		long sum = 0;

		/**
		 * Iterate through the whole list and multiply
		 * [element0], with [last element].
		 * [element1], with [last element-1]
		 * and so on.
		 */
		for(int i=0; i < tail; i++) {

			//Goes from left
			int left = numberList.get(i);

			//goes from right
			int right = numberList.get(tail-1);

			sum += (left * right);

			//moves one step close to middle from right
			tail--;
		}
		return sum;
	}	
	/**
	 * Multiplies all elements in the List with all the other elements 
	 * and returns the sum of all multiplications.  
	 * 
	 * @param numberList a List containing Integers
	 * @return the sum
	 */
	public long multiplyAll(List<Integer> numberList) {
		long sum = 0;

		 //Slow way to solve it
		/*
		for(int i = 0; i < numberList.size(); i++){
			int innerLoopSum = 0;

			for(int j = i+1; j < numberList.size(); j++) {
				innerLoopSum += numberList.get(i) * numberList.get(j);
			}
			sum += innerLoopSum;
		}

		 */

		// Faster way to solve it
		long sumOfAllNumbers = 0;
		long temp = 0;

		//Calculate total sum of list
		for(int i = 0;  i < numberList.size(); i++)
			sumOfAllNumbers += numberList.get(i);

		// Decreases sumOfAllNumbers with numberList(i)
		// Calculates numberList(i) * sumOfAllNumbers
		// Saves it to sum
		for(int i = 0; i < numberList.size(); i++){

			//Continues to next number  if numberList.get(i) is 0, no need to continue
			if(numberList.get(i) == 0)
				continue;
			sumOfAllNumbers -= numberList.get(i);
			sum += (sumOfAllNumbers * numberList.get(i)) ;
		}

		return sum;
	}
}