package test;

import acm.program.*;

public class FindSecondLargest extends ConsoleProgram {

private static final int sentinal = 0;
	
	public void run() {
		println("This program finds the two largest integers in a list.");
		println("Enter value, one per line, using a " + sentinal);
		println("to signal the end of the list");
		//assigns -1, the first integer that is less than 0 as the 
		//largest and second largest number
		int largestNumber = -1;
		int secondLargest = -1;
		while (true) {
			int number = readInt("?");
			if (number == sentinal) {
				//if the sentinal was entered as the first or second number
				//says that the entry was invalid
				if (largestNumber == -1 || secondLargest == -1) {
					println ("You cannot enter the sentinal as the first or second number");
					break;
				}
				else {
					println("The largest value is " + largestNumber);
					println("The second largest is " + secondLargest);
					break;
				}
			}
			//compares the entered number to the largest and second largest numbers, 
			//and assigns them appropriately
			if (number > largestNumber) {
				secondLargest = largestNumber;
				largestNumber = number;
			}
			else if (number > secondLargest) {
				secondLargest = number;
			}
		}
	}	
}