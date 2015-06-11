package test;

import acm.graphics.*;
import acm.program.*;

public class RotateLeft extends GraphicsProgram {
	public void run() {
		setSize(1000, 800);
		GImage original = new GImage("lina.jpg");
		GImage rotatedLeft = rotateLeft(original);
		GImage rotatedRight = rotateRight(original);
		double x0 = (getWidth() - original.getWidth()) / 2;
		double y0 = getHeight() / 2 - original.getHeight() - 5;
		double x1 = getWidth() / 2 - original.getHeight() - 5;
		double y1 = getHeight() / 2 + 5;

		double x2 = getWidth() / 2 + 5;
		double y2 = getHeight() / 2 + 5;
		add(original, x0, y0);
		add(rotatedLeft, x1, y1);
		add(rotatedRight, x2, y2);
	}

	/**
	 * Creates a new image which consists of the bits in the original rotated
	 * left by 90 degrees.
	 * 
	 * @param image
	 *            The original image
	 * @return The image rotated left by 90 degrees
	 */
	private GImage rotateLeft(GImage image) {
		int[][] oldPixels = image.getPixelArray();
		int width = oldPixels[0].length;
		int height = oldPixels.length;
		int[][] newPixels = new int[width][height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				newPixels[width - j - 1][i] = oldPixels[i][j];
			}
		}
		return new GImage(newPixels);
	}

	private GImage rotateRight(GImage image) {
		int[][] oldPixels = image.getPixelArray();
		int width = oldPixels[0].length;
		int height = oldPixels.length;
		int[][] newPixels = new int[width][height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				newPixels[j][height-i-1] = oldPixels[i][j];
			}
		}
		return new GImage(newPixels);
	}
}