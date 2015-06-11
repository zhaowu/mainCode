package test;

import acm.program.ConsoleProgram;

public class PiSeries extends ConsoleProgram{
	private static final double TERM_THRESHOLD = .000001;
	private double rightSum =0;
	private double upper=1.0;
	private double lower=1.0;
	private double step =2.0;
	private int sign = 1;
	private double currentTerm = sign*upper/lower;
	public void run(){		
		while(true){
			if (Math.abs(currentTerm) < TERM_THRESHOLD) break;
			rightSum +=currentTerm;	
			sign = -1*sign;
			lower+=step;
			currentTerm = sign*upper/lower;
		}
		println("pie = "+rightSum*4);
	}
}
