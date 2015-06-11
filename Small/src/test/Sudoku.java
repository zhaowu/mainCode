package test;

import acm.program.*;
import acm.util.RandomGenerator;

public class Sudoku extends ConsoleProgram {
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int[][] TEST_GRID = new int[9][9];

	public void run() {
		generateSolution();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				print(TEST_GRID[i][j]);
				print(" ");
			}
			println();
		}
		println(checkSudokuSolution(TEST_GRID));
	}

	private void generateSolution() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				TEST_GRID[i][j] = rgen.nextInt(1, 9);
			}
		}
	}

	/* Tests whether a Sudoku solution is valid */
	private boolean checkSudokuSolution(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			if (checkGroupOfNine(getRow(grid, i)))
				return false;
			if (checkGroupOfNine(getColumn(grid, i)))
				return false;
			if (checkGroupOfNine(get3x3Square(grid, i)))
				return false;
		}
		return true;
	}

	/*
	 * Tests whether an array of nine values contains any duplicates or values
	 * outside of the range 1 to 9. If not, it must be a be a legal assignment
	 * of Sudoku values.
	 */
	private boolean checkGroupOfNine(int[] array) {
		boolean[] alreadySeen = new boolean[10];
		for (int i = 0; i < 9; i++) {
			int value = array[i];
			//eg value=array[0]=3->alreadySeen[3]=true    then if value=array[1]=3,since alreadySeen[3]==true, return true 
			if (value < 1 || value > 9 || alreadySeen[value])
				return true;
			alreadySeen[value] = true;
		}
		return false;
	}

	/* Extracts row k from an 9x9 grid */
	private int[] getRow(int[][] grid, int k) {
		return grid[k];
	}

	/* Extracts column k from an 9x9 grid */
	private int[] getColumn(int[][] grid, int k) {
		int[] column = new int[9];
		for (int i = 0; i < 9; i++) {
			column[i] = grid[i][k];
		}
		return column;
	}

	/*
	 * Extracts the elements of the kth 3x3 subsquare as an array of 9 elements.
	 * The subsquares are numbered from left to right then top to bottom
	 * starting with subsquare 0 in the upper left.
	 */
	//k=0,...,9
	//amazing...
	private int[] get3x3Square(int[][] grid, int k) {
		int baseRow = k / 3 * 3;
		int baseCol = k % 3 * 3;
		int[] array = new int[9];
		for (int i = 0; i < 9; i++) {
			array[i] = grid[baseRow + i / 3][baseCol + i % 3];
		}
		return array;
	}
}