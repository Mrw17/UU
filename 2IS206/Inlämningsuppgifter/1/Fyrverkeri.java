/**
 * @author Daniel Westerlund
 * @date 2020-01-23
 */

public class Fyrverkeri {

	/**
	 * Main program that will make a count down from 10
	 * and then simulate 10 bangs. 
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Nu börjar nedräkningen!");
		countDown(10);
	}

	/**
	 * Recursive method with base case num < 1  
	 * It will print out num before each recursive call,
	 * and making it look like a count down.  
	 * After base call has been found, 
	 * it will print out bangs that are numbered from 1 to num. 
	 * @param num number of count down + bangs
	 */
	private static void countDown(int num) {
		System.out.print(num + " ");
		if(num < 1)
			return;
		
		countDown(num-1);
		System.out.println("\nBANG " + num + "!");
	}

}
