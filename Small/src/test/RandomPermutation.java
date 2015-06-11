package test;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class RandomPermutation extends ConsoleProgram{
	private RandomGenerator rgen = RandomGenerator.getInstance();
	public void run(){
		println(generateRandomKey());
	}
	 private String generateRandomKey() {
		 return generateRandomPermutation("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		 }
	 private String generateRandomPermutation(String str) {
		 String result = "";
		 while (str.length() > 0) {
		 int index = rgen.nextInt(str.length());
		 //randomly pick a character from str and add it to the end the result 
		 result += str.charAt(index);
		 String head = str.substring(0, index);
		 String tail = str.substring(index + 1);
		 //str = str - that character
		 str = head + tail;
		 }
		 return result;
		 }
}
