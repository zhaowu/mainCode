package test;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class SameBirthdaySimulation extends ConsoleProgram {
	/* Private constants */
	private static final int N_TRIALS = 100000;
	private static final int LOWER_LIMIT = 10;
	private static final int UPPER_LIMIT = 25;
	/* Instance variables */
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void run() {
		while (true) {
			int n = readInt("Number of people: ");
			if (n == 0)
				break;
			println("probability of " + n + " people is: "
					+ calculateProbability(n));
		}
	}

	private double calculateProbability(int n) {
		int[] birthday = new int[n];
		int count = 0;
		for (int i = 0; i < N_TRIALS; i++) {
			for (int j = 0; j < n; j++) {
				birthday[j] = rgen.nextInt(1, 365);
			}
//			boolean found =false;
//			for (int x = 0; x < birthday.length && !found; x++) {
//				for (int y = x + 1; y < birthday.length; y++) {
//					if (birthday[x] == birthday[y]) {
//						count++;
//						found=true;
//						break;
//					}
//				}
//			}
			
		loop1:  for (int x = 0; x < birthday.length ; x++) {
				for (int y = x + 1; y < birthday.length; y++) {
					if (birthday[x] == birthday[y]) {
						count++;
						break loop1;
					}
				}
			}

		}
		return (double)count / N_TRIALS;
	}

}
