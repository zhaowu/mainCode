package test;

import acm.program.ConsoleProgram;

public class DigitalRoot extends ConsoleProgram{
	public void run(){
		int positiveInteger = readInt("Input a positive integer:");
		println("digital root of "+positiveInteger+" is "+mystery(positiveInteger));
	}

	private int mystery(int n) {
		 while (n >= 10) {
		 int k = 0;
		 while (n > 0) {
		 k += n % 10;
		 n /= 10;
		 }
		 n = k;
		 }
		 return n;
		}
}
