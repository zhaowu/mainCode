package test;

import acm.program.*;
import acm.util.*;

public class ConsecutiveHeads extends ConsoleProgram {
	private RandomGenerator rgen = RandomGenerator.getInstance();	
	private int countConsecutive=0;
	private int countTotal=0;
	public void run() {
		
		while (true) {
			double d=rgen.nextDouble();
			println(d);
			if ( d< 0.5) {
				Tail();
			} else {
				Head();
				countTotal++;
			}
			if (countConsecutive == 3){	
				break;}

		}
		println("Total number of Head is "+countTotal);
	}

	private void Head() {
		println("Head");
		countConsecutive++;
	}

	private void Tail() {
		println("Tail");
		countConsecutive=0;
	}

}