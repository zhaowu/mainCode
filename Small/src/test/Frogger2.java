package test;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;

/*
 * This program gets a frog to jump one square in the closest
 * direction to a mouse click.
 */
public class Frogger2 extends GraphicsProgram {
	public void init() {
		setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		frog = new GImage("lina.jpg");
		frog.setSize(75, 75);
		//NCOLUMNS is int, so NCOLUMNS/2=3!
		fx = (NCOLUMNS / 2+0.5 ) * SQUARE_SIZE;
		fy = (NROWS - 0.5) * SQUARE_SIZE;		
		add(frog, fx - frog.getWidth() / 2, fy - frog.getHeight() / 2);
		addMouseListeners();
	}

	/* Responds to a mouse click */
	public void mouseClicked(MouseEvent e) {
		double mx = e.getX();
		double my = e.getY();
		//decide move along x-axis or y-axis
		if (Math.abs(mx - fx) > Math.abs(my - fy)) {
			//decide move to +x or -x
			if (mx > fx) {
				moveFrog(SQUARE_SIZE, 0);
			} else {
				moveFrog(-SQUARE_SIZE, 0);
			}
		} else {
			//decide move to +y or -y
			if (my > fy) {
				moveFrog(0, SQUARE_SIZE);
			} else {
				moveFrog(0, -SQUARE_SIZE);
			}
		}
	}

	/* Moves the frog by dx/dy as long as it remains inside the world */
	private void moveFrog(double dx, double dy) {
		if (insideFroggerWorld(fx + dx, fy + dy)) {
			fx += dx;
			fy += dy;
			frog.move(dx, dy);
		}
	}

	/* Returns true if the point (x, y) is inside the frog's world */
	private boolean insideFroggerWorld(double x, double y) {
		return (x >= 0 && x <= NCOLUMNS * SQUARE_SIZE && y >= 0 && y <= NROWS
				* SQUARE_SIZE);
	}

	/* Private constants */
	private static final int SQUARE_SIZE = 75;
	private static final int NROWS = 4;
	private static final int NCOLUMNS = 7;
	/* Private instance variables */
	private GImage frog; /* The image of the frog */
	private double fx; /* The x-coordinate of the frog's center */
	private double fy; /* The y-coordinate of the frog's center */
	/* Sets the graphics window size */
	public static final int APPLICATION_WIDTH = NCOLUMNS * SQUARE_SIZE;
	public static final int APPLICATION_HEIGHT = NROWS * SQUARE_SIZE;
}