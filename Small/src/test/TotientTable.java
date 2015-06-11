package test;

import acm.program.*;

public class TotientTable extends ConsoleProgram {
	/* Creates the table */
	public void run() {
		for (int i = 2; i <= LIMIT; i++) {
			println("totient(" + i + ") = " + totient(i));
		}
	}

	/*
	 * Calculates the Euler totient function of n. The totient is the number of
	 * integers between 1 and n that share no factors with n (other than 1).
	 */
	private int totient(int n) {
		int count = 0;
		for (int i = 1; i < n; i++) {
			// if (!shareACommonFactor(i, n)) count++;
			// }
			if (gcd(i, n) == 1) {
				count++;
			}
			
		}
		return count;
	}

	/*
	 * Returns true if n1 and n2 share a common factor (other than 1). In
	 * mathematics, such numbers are said to be relatively prime.
	 */
	private boolean shareACommonFactor(int n1, int n2) {
		for (int i = 2; i <= Math.min(n1, n2); i++) {
			if (n1 % i == 0 && n2 % i == 0)
				return true;
		}
		return false;
	}
	
	public int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	/* Private constants */
	private static final int LIMIT = 12;
}