package test;

import acm.program.ConsoleProgram;

public class SameBirthday extends ConsoleProgram{
	public void run(){
		int n = readInt("Number of people:");
		println("Probability that at least two of them share the same birthday = "+calculateP(n));
	}
	private double calculateP(int n){
		double p=1;
		double term;
		for(int i=0;i<n;i++){
			term = 1-(double)i/365;
			p = p*term;
		}
		
		return 1-p;
	}
}
