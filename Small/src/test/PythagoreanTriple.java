package test;

import acm.graphics.GMath;
import acm.program.ConsoleProgram;

public class PythagoreanTriple extends ConsoleProgram{
	private int LIMIT;
	public void run(){
		LIMIT=readInt("Input a limit:");
		tryAllTriple(LIMIT);
	}
	private void tryAllTriple(int m){
		for (int a=1;a<=m;a++){
			for(int b=a;b<=m;b++){
				int csq = a*a+b*b;
				int c=GMath.round(Math.sqrt(a*a+b*b));
				if(c*c==csq){println(a+","+b+","+c);}
			}
		}
	}
}
