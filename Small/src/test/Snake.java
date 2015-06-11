package test;

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class Snake extends GraphicsProgram {
	/* Initializes the graphics state */
	public void init() {
		setSize(1000,800);
		x = getWidth() / 2;
		y = getHeight() / 2;
		dir = EAST;
		addMouseListeners();
	}

	/* Runs the simulation */
	public void run() {
		while (getElementAt(x, y) == null) {
			removeAll();
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
		switch (dir) {
		case NORTH:
			y -= SQUARE_SIZE;
			break;
		case EAST:
			x += SQUARE_SIZE;
			break;
		case SOUTH:
			y += SQUARE_SIZE;
			break;
		case WEST:
			x -= SQUARE_SIZE;
			break;
		}
	}

	/* Reacts to a mouse-pressed event */
	public void mousePressed(MouseEvent e) {
		if (dir == NORTH || dir == SOUTH) {
			dir = (e.getX() > x) ? EAST : WEST;
		} else {
			dir = (e.getY() > y) ? SOUTH : NORTH;
		}
	}

	/* Instance variables */
	private double x; /* The x coordinate of the snake head */
	private double y; /* The y coordinate of the snake head */
	private int dir; /* The direction the head is moving */
	/* Private constants */
	private static final int NORTH = 0;
	private static final int EAST = 1;
	private static final int SOUTH = 2;
	private static final int WEST = 3;
	private static final int PAUSE_TIME = 100;
	private static final double SQUARE_SIZE = 15;
}