package test;

import acm.program.ConsoleProgram;

public class CountLoveNumber extends ConsoleProgram {
	public void run() {
		int count = 0;
		while (true) {
			String line = readLine();
			if (line.length() == 0)
				break;
			count += countLove(line);
		}
		println("Love occurs " + count + " times.");
	}

	private int countLove(String str) {
		String lowerCaseString = str.toLowerCase();
		int count = 0;
		int start = lowerCaseString.indexOf("love");
		while (start != -1) {
			if (isSeparator(lowerCaseString, start - 1)
					&& isSeparator(lowerCaseString, start + 4)) {
				count++;
			}
			start = lowerCaseString.indexOf("love", start + 4);
		}
		return count;
	}

	/* Checks to see if the ith char of str is a separator */
	private boolean isSeparator(String str, int i) {
		if (i < 0 || i >= str.length())
			return true;
		return !Character.isLetter(str.charAt(i));
	}
}