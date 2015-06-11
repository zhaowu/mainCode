package test;

import acm.program.*;

public class midterm extends ConsoleProgram {
	public void run() {
		String s1 = "To err";
		String s2 = "is human!";
		s1 = forgive(s1, s2);
		println(s1 + " " + s2);
	}

	private String forgive(String me, String you) {
		String heart = me.substring(0, you.length() - me.length());
		println("you.length()="+you.length());
		println("me.length()="+me.length());
		println("heart="+heart);
		you = "" + you.charAt(me.length());
		println("You="+you);
		int amount = heart.length();
		println("amount="+amount);
		me = me.substring(amount + 2) + me.charAt(amount);
		println("me="+me);
		println("a+2="+understanding(you, 2));
		heart += understanding(you, 2) + you + me;
		return heart;
	}

	private char understanding(String you, int num) {
		return (char) (you.charAt(0) + num);
	}
}