package test;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;

public class Frogger extends GraphicsProgram {
	public static final int SQSIZE = 75;
	public static final int NCOLS = 7;
	public static final int NROWS = 5;
	public static final int APPLICATION_WDITH = NCOLS*SQSIZE;
	public static final int APPLICATION_HEIGHT = NROWS*SQSIZE;
	
	public void init() {
		setSize(APPLICATION_WDITH,APPLICATION_HEIGHT);
		addMouseListeners();
		addFrog();
	}
	
	public void run() {
		while (true) {
			waitForClick();
			moveFrog();
		}
	}
	
	private void addFrog() {
		frog = new GImage("lina.jpg");
		frog.setSize(75,75);
		double x = getWidth() / 2 - frog.getWidth() / 2;
		double y = (getHeight() - frog.getHeight()) - (SQSIZE - frog.getHeight()) / 2;
		add(frog, x, y);
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		println("" + mouseX + mouseY);
	}
	
	private void moveFrog() {
		double frogX = frog.getX();
		double frogY = frog.getY();
		if (Math.abs(mouseY - frogY) > Math.abs(mouseX - frogX)) {
			if (mouseY - frogY < 0) frog.setLocation(frog.getX(), frog.getY() - SQSIZE); // move up
			if (mouseY - frogY > 0) frog.setLocation(frog.getX(), frog.getY() + SQSIZE); // move down
		}
		if (Math.abs(mouseX - frogX) > Math.abs(mouseY - frogY)) {
			if (mouseX - frogX < 0) frog.setLocation(frog.getX() - SQSIZE, frog.getY()); // move left
			if (mouseX - frogX > 0) frog.setLocation(frog.getX() + SQSIZE, frog.getY()); // move right
		}
	}
	
	
	/* Private Instance Variables */
	GImage frog;
	int mouseX;
	int mouseY;
	
}