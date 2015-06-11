package test;

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class SnakeUsingDeltas extends GraphicsProgram {
	/* Initializes the graphics state */
	public void init() {
		setSize(1000,800);
		x = getWidth() / 2;
		y = getHeight() / 2;
		dx = SQUARE_SIZE;
		dy = 0;
		addMouseListeners();
	}

	/* Runs the simulation */
	public void run() {
		while (getElementAt(x, y) == null) {
			drawSquare();
			updateSnakeHeadLocation();
			pause(PAUSE_TIME);
		}
	}

	/* Adds a square to the window centered at (x, y) */
	private void drawSquare() {
		GRect rect = new GRect(SQUARE_SIZE, SQUARE_SIZE);
		rect.setFilled(true);
		add(rect, x - SQUARE_SIZE / 2, y - SQUARE_SIZE / 2);
	}

	/* Updates the location of the snake head */
	private void updateSnakeHeadLocation() {
		x += dx;
		y += dy;
	}

	/* Reacts to a mouse-pressed event */
	public void mousePressed(MouseEvent e) {
		if (dy == 0) {
			dx = 0;
			dy = (e.getY() > y) ? SQUARE_SIZE : -SQUARE_SIZE;
		} else {
			dx = (e.getX() > x) ? SQUARE_SIZE : -SQUARE_SIZE;
			dy = 0;
		}
	}

	/* Instance variables */
	private double x; /* The x coordinate of the snake head */
	private double y; /* The y coordinate of the snake head */
	private double dx; /* The x velocity of the head */
	private double dy; /* The y velocity of the head */
	/* Private constants */
	private static final int PAUSE_TIME = 100;
	private static final double SQUARE_SIZE = 15;
}