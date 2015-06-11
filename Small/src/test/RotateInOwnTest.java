package test;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class RotateInOwnTest extends ConsoleProgram {
	private static final int size = 9;
	private int[][] TEST_GRID = new int[size][size];
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void run() {
		generateSolution();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				print(TEST_GRID[i][j]);
				print(" ");
			}
			println();
		}

		// rotate2(TEST_GRID);

		println();
		rotate5(TEST_GRID);
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				print(TEST_GRID[x][y]);
				print(" ");
			}
			println();
		}

	}

	private void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;

		int m = matrix.length;

		int[][] result = new int[m][m];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				result[j][m - 1 - i] = matrix[i][j];
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = result[i][j];
			}
		}

	}

	private void rotate2(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = temp;
			}
		}
	}
	
	private int[][] rotate3(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return matrix;

		int m = matrix.length;

		int[][] result = new int[m][m];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				result[j][m - 1 - i] = matrix[i][j];
			}
		}

		matrix = result;

		return matrix;
	}

	//change the method for counter-clockwise
	private void rotate4(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i]=matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j]=matrix[n - 1 - j][i];
				matrix[n - 1 - j][i]=temp;
			}
		}
	}
	private void rotate5(int[][] matrix) {
	  int n = matrix.length;
      if(n == 0)return;
      for(int i = 0, len = n; i < n/2; i++, len -= 2)
      {//n/2 为旋转的圈数，len为第i圈中正方形的边长
          int m = len - 1;
          for(int j = 0; j < m; j++)
          {
              int tmp = matrix[i][i+j];
              matrix[i][i+j] = matrix[i+m-j][i];
              matrix[i+m-j][i] = matrix[i+m][i+m-j];
              matrix[i+m][i+m-j] = matrix[i+j][i+m];
              matrix[i+j][i+m] = tmp;
          }
      }
	}
	
	private void generateSolution() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				TEST_GRID[i][j] = rgen.nextInt(1, 9);
			}
		}
	}
}