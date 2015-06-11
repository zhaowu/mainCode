package test;

import acm.program.*;
import acm.util.*;
import java.awt.event.*;

public class RandomlyMovingRedCross extends GraphicsProgram {
	public static void main(String[] args) {
		new RandomlyMovingRedCross().start(args);
	}

	/* Sets up the program at the beginning */
	public void init() {
		setSize(900,800);
		cross = new RedCross();
		//have to add mouselistener to cross so that e.getSource() can return cross when we click on cross
		cross.addMouseListener(this);
		add(cross, getWidth() / 2, getHeight() / 2);
		chooseRandomDirection();
		addMouseListeners();
	}

	/* Runs the simulation */
	public void run() {
		while (true) {
			cross.movePolar(VELOCITY, direction);
			pause(PAUSE_TIME);
		}
	}

	/* Called when the mouse is clicked */
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getSource());
		//if (cross.contains(e.getX(), e.getY())) {
		if (e.getSource()==cross){
			chooseRandomDirection();
		}
	}

	/* Resets the direction to a random value */
	private void chooseRandomDirection() {
		direction = rgen.nextDouble(0, 360);
	}

	/* Private constants */
	private static final double PAUSE_TIME = 20;
	private static final double VELOCITY = 2;
	/* Private instance variables */
	private RedCross cross;
	private double direction;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}