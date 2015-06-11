package test;

import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;

public class Histogram extends ConsoleProgram {
	public void run() {
//		println(1 + 2 + "3" + 4 + 5);
		Statistics s = new Statistics();
		double[] scores = s.readScoresArray(DATA_FILE);
		println("length = " + scores.length);
		initHistogram();
		createHistogram(scores);
		printHistogram();
	}

	/* Initializes the histogram array */
	private void initHistogram() {
		histogramArray = new int[11];
		for (int i = 0; i <= 10; i++) {
			histogramArray[i] = 0;
		}
	}

	/* Creates a histogram from the array of scores */
	private void createHistogram(double[] scores) {
		for (int i = 0; i < scores.length; i++) {
			double score = scores[i];
			if (score < 0 || score > 100) {
				throw new ErrorException("That score is out of range");
			} else {
				int range = (int) (score / 10);
				//store how many scores fall into each range, eg. histogramArray[0] means number of scores fall into 0-10
				histogramArray[range]++;
			}
		}
	}

	/* Displays the histogram */
	private void printHistogram() {
		for (int range = 0; range <= 10; range++) {
			
			String stars = createStars(histogramArray[range]);
			//print label and corresponding stars
			println(createLabelForRange(range) + ": " + stars);
		}
	}

	/* Creates the label indicating the range */
	private String createLabelForRange(int range) {
		switch (range) {
		case 0:
			return "00-09";
		case 10:
			return " 100";
		default:
			return (10 * range) + "-" + (10 * range + 9);
		}
	}

	/* Creates a string consisting of n stars */
	private String createStars(int n) {
		String stars = "";
		for (int i = 0; i < n; i++) {
			stars += "*";
		}
		return stars;
	}

	/* Private instance variables */
	private int[] histogramArray;
	/* Name of the data file */
	private static final String DATA_FILE = "Midterm.txt";
}