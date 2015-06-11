package test;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class RotateInOwn extends ConsoleProgram {
	private static final int size = 6;
	private int[][] TEST_GRID=new int [size][size];
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	public void run(){
		generateSolution();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				print(TEST_GRID[i][j]);
				print(" ");
			}
			println();
		}
		println();
		rotate2(TEST_GRID);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				print(TEST_GRID[i][j]);
				print(" ");
			}
			println();
		}
		
	}
	
//    private void rotate(int[][] matrix) {
//        int n = matrix.length;
//        int limit = (n-1)/2;
//        for(int i=0;i<= limit; i++){
//            for(int j=i;j<n-1-i;j++){
////        for (int i = 0; i < n / 2; i++) {
////    		for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
//                int temp = matrix[i][j];
//                matrix[i][j] = matrix[n-1-j][i];
//                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
//                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
//                matrix[j][n-1-i] = temp;
//            }
//        }
//    }
    
    private void rotate2(int[][] matrix) {
        int N = matrix.length;
        for(int i = 0; i < N/2; ++i) {
            for(int j = 0; j < (N+1)/2; ++j) {
                int temp = matrix[i][j];  // save the top element;
                matrix[i][j] = matrix[N-1-j][i];  // right -> top;
                matrix[N-1-j][i] = matrix[N-1-i][N-1-j]; // bottom -> right;
                matrix[N-1-i][N-1-j] = matrix[j][N-1-i];// left -> bottom;
                matrix[j][N-1-i] = temp;                // top -> left;
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