package test;

import acm.program.ConsoleProgram;

public class DrawDimondOutline extends ConsoleProgram {
	public void run() {
//		System.out.print("\n");
//		System.out.print("huan hang");
		drawDiamondOutline(8);
	}

	private void drawDiamondOutline(int n) {
		for (int i = 0; i < n ; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				if (i != Math.abs(j - 7)) {
					System.out.print(" ");
				} else {
					System.out.print("*");
				}
				if (j == 2 * n - 2) {
					System.out.println();
					break;
				}
			}

		}
		for (int i = n; i < 2*n-1; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				if (Math.abs(i-14)!= Math.abs(j - 7)) {
					System.out.print(" ");
				} else {
					System.out.print("*");
				}
				if (j == 2 * n - 2) {
					System.out.println();
					break;
				}
			}
			
		}

	}
}
