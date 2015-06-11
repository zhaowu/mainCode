package test;

import acm.program.ConsoleProgram;

public class Decryp extends ConsoleProgram{
	public void run(){
		while(true){
		String encryption = readLine("Input Upper-case letters:");
		if(encryption.equals("")) break;
		println("Plain text is: "+invertKey(encryption));		
		}
		println("end");
	}
	private String invertKey(String key) {
		String result="";
		String plain  ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String encryp ="QWERTYUIOPASDFGHJKLZXCVBNM";
		for (int i=0;i<key.length();i++){
			char c = Character.toUpperCase(key.charAt(i));
			int index = encryp.indexOf(c);
			char pc = plain.charAt(index);
			result = result + pc;
		}
		return result;
	}
}
