package test;

import acm.program.ConsoleProgram;

public class RemoveDuplicate extends ConsoleProgram {
	public void run() {
		println(3 % 1);
		String input = readLine("Input a word: ");
		println(removeDoubleLetters(input));
	}

	private String removeDoubleLetters(String input) {
		//String result = "";
		// method 1
//		for (int i = 0; i < input.length(); i++) {
//			char ch = input.charAt(i);
//			if (i == 0 || ch != input.charAt(i - 1)) {
//				result += ch;
//			}
//		}
		//return result;
		// method 2
		 String result=input;
		 for (int i=0;i<input.length()-1;i++){
		 char c1 = input.charAt(i);
		 char c2 = input.charAt(i+1);
		 if (c1==c2){
		 result = result.substring(0, i)+result.substring(i+1);
		 }
		
		 }
		 return result;
	}
}
