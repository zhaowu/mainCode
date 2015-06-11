/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	// stores the most recently rolled dice numbers
	private int[] dieResults = new int[N_DICE];
	// stores the score for each category of each players
	private int[][] categoryScores;
	private int category;
	// stores the already selected categories
	private int[][] selectedCategories;

	/* Java main method to ensure that this program starts correctly */
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		// ask user to input number of players
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		// if user input a number larger than MAX_PLAYERS, ask him/her to input
		// again
		while (true) {
			if (nPlayers <= MAX_PLAYERS)
				break;
			nPlayers = dialog.readInt("You can only enter up to " + MAX_PLAYERS
					+ " number of players");
		}
		// For example, nPlayers = 3
		// playerNames[0], playerNames[1], playerNames[2]
		// categoryScores[0][...],categoryScores[1][...],categoryScores[2][...],categoryScores[3][...]
		playerNames = new String[nPlayers];
		categoryScores = new int[nPlayers + 1][N_CATEGORIES + 1];
		selectedCategories = new int[nPlayers + 1][N_CATEGORIES + 1];
		// ask user to input name for each player
		// playNames[0]=player 1,playNames[1]=player 2, etc.
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}

		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		/* You fill this in */
		// N_SCORING_CATEGORIES rounds, nPlayers players
		// In each round, there are 3 steps for each
		// player:(1)initializeFirstRoll(2)secondAndThirdRoll(3)selectCategory
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for (int j = 1; j <= nPlayers; j++) {
				initializeFirstRoll(j);
				secondAndThirdRoll(j);
				selectCategory(j);
			}
		}
		// After all players finish all their rounds, calculate results and
		// decide who's the winner
		calculateResults();
		calculateWinner();
	}

	// step (1):initializeFirstRoll
	// playerNumber:1-6
	private void initializeFirstRoll(int playerNumber) {
		// dieResults store 5(N_DICE) random numbers
		for (int i = 0; i < N_DICE; i++) {
			int dieRoll = rgen.nextInt(1, 6);
			dieResults[i] = dieRoll;
		}
		// ask user to click on "Roll Dice" button
		display.printMessage(playerNames[playerNumber - 1]
				+ "'s turn! Click the" + "\"Roll Dice\""
				+ "button to roll the dice.");
		// if user don't press "Roll Dice" button, program will not continue
		display.waitForPlayerToClickRoll(playerNumber);
		// display dieResults on screen
		display.displayDice(dieResults);
	}

	// step (2):secondAndThirdRoll
	private void secondAndThirdRoll(int playerNumber) {
		// For each player, there are 2 more rounds besides the 1st round
		for (int i = 0; i < 2; i++) {
			display.printMessage("Select the dice you wish to re-reroll and click "
					+ "\"Roll Again\"");
			// The waitForPlayerToSelectDice() method returns only after the
			// player has made a selection and clicks the "Roll Again" button
			display.waitForPlayerToSelectDice();
			for (int j = 0; j < N_DICE; j++) {
				// check which dice are selected
				if (display.isDieSelected(j)) {
					// reroll if selected
					int dieRoll = rgen.nextInt(1, 6);
					// update to dieResults
					dieResults[j] = dieRoll;
				}
			}
			// display dieResults on screen
			display.displayDice(dieResults);
		}
	}

	/**
	 * Pre-condition: The player has rolled 3 times. The player selects the
	 * category for the dice result. Can't select a category that has been
	 * selected
	 */
	// step (3):selectCategory
	private void selectCategory(int playerNumber) {
		display.printMessage("Select a category for this roll result");
		while (true) {
			// assign value to category
			// the waitForPlayerToSelectCategory() method waits for the player
			// to click on one of the categories and returns the index of the
			// category, which will be one of the constants defined in
			// YahtzeeConstants.
			// this method does not check to see whether the category is valid
			// for the dice values or whether this category has already been
			// used by this player
			category = display.waitForPlayerToSelectCategory();
			// now we know the value of diceResults,playerNumber and category
			// (for one round), we can use them later
			// if this category has not been selected by this player before, we
			// can calculate the points for that category now
			if (selectedCategories[playerNumber][category] == 0) {
				calculateCategoryScore(playerNumber);
				break;
			}
			// if this category has been selected by this player before, ask
			// user to select the category again
			display.printMessage("You have already selected this category. Please select another one");
		}
	}

	// in step (3), if the player choose an unselected category, then calculate
	// points in that category according to dieResults
	private void calculateCategoryScore(int playerNumber) {
		// that unselected category is now selected
		selectedCategories[playerNumber][category] = 1;
		int totalScore;
		// check if dieResults belong to category
		// if yes
		if (checkCategory(dieResults, category)) {
			// set score of that category to corresponding value (detail setting
			// is defined in setCategory method)
			setCategoryScore(playerNumber, category);
			int score = categoryScores[playerNumber][category];
			// display that value in the category on screen
			display.updateScorecard(category, playerNumber, score);
			calculateTotalScores(playerNumber);
			totalScore = categoryScores[playerNumber][TOTAL];
			// update new total score on screen
			display.updateScorecard(TOTAL, playerNumber, totalScore);
			// if no
		} else {
			// set score of that category to 0
			categoryScores[playerNumber][category] = 0;
			// display that value in the category on screen
			display.updateScorecard(category, playerNumber, 0);
			calculateTotalScores(playerNumber);
			totalScore = categoryScores[playerNumber][TOTAL];
			// update new total score on screen
			display.updateScorecard(TOTAL, playerNumber, totalScore);
		}
	}

	// This method defines how to calculate scores of each category
	private void setCategoryScore(int playerNumber, int category) {
		int score = 0;
		if (category >= ONES && category <= SIXES) {
			for (int i = 0; i < N_DICE; i++) {
				if (dieResults[i] == category) {
					score += dieResults[i];
				}
			}
		} else if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND
				|| category == CHANCE) {
			for (int i = 0; i < N_DICE; i++) {
				score += dieResults[i];
			}
		} else if (category == FULL_HOUSE) {
			score = 25;
		} else if (category == SMALL_STRAIGHT) {
			score = 30;
		} else if (category == LARGE_STRAIGHT) {
			score = 40;
		} else if (category == YAHTZEE) {
			score = 50;
		}
		// After calculation, store the score of that specific category
		categoryScores[playerNumber][category] = score;

	}

	private void calculateTotalScores(int playerNumber) {
		int upperScore = 0;
		int lowerScore = 0;
		int totalScore = 0;
		for (int i = ONES; i <= SIXES; i++) {
			upperScore += categoryScores[playerNumber][i];
		}
		for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			lowerScore += categoryScores[playerNumber][i];
		}
		totalScore = upperScore + lowerScore;
		// There are three special category, points of these categories are
		// summation of certain other categories
		categoryScores[playerNumber][UPPER_SCORE] = upperScore;
		categoryScores[playerNumber][LOWER_SCORE] = lowerScore;
		categoryScores[playerNumber][TOTAL] = totalScore;
	}

	// once we have all scores available, we can calculate results
	private void calculateResults() {
		for (int i = 1; i <= nPlayers; i++) {
			// update upper score and lower score on screen
			display.updateScorecard(UPPER_SCORE, i,
					categoryScores[i][UPPER_SCORE]);
			display.updateScorecard(LOWER_SCORE, i,
					categoryScores[i][LOWER_SCORE]);
			// check if upper bonus is gained or not
			if (categoryScores[i][UPPER_SCORE] >= 63) {
				categoryScores[i][UPPER_BONUS] = 35;
			}
			// update upper bonus on screen
			display.updateScorecard(UPPER_BONUS, i,
					categoryScores[i][UPPER_BONUS]);
			// update total score after calculating upper bonus
			categoryScores[i][TOTAL] = categoryScores[i][TOTAL]
					+ categoryScores[i][UPPER_BONUS];
			// update total score on screen
			display.updateScorecard(TOTAL, i, categoryScores[i][TOTAL]);
		}
	}

	// once total score is finalized, we can decide who's the winner
	private void calculateWinner() {
		int winningScore = 0;
		int winningPlayerNumber = 0;
		// walk through TOTAL points of all players, find the maximum TOTAL
		// points
		for (int i = 1; i <= nPlayers; i++) {
			// due to definition of categoryScores[nPlayer+1][N_CATEGORIES + 1]
			int x = categoryScores[i][TOTAL];
			if (x > winningScore) {
				winningScore = x;
				winningPlayerNumber = i - 1;
			}
		}
		display.printMessage("Congratulations, "
				+ playerNames[winningPlayerNumber] + " , you're the winner!");
	}

	// instead of YahtzeeMagicStub.checkCategory(), here's the new
	// checkCategory()
	private boolean checkCategory(int[] dieResults, int category) {
		boolean categoryMatch = false;
		if (category >= ONES && category <= SIXES || category == CHANCE) {
			categoryMatch = true;
		} else {
			ArrayList<Integer> ones = new ArrayList<Integer>();
			ArrayList<Integer> twos = new ArrayList<Integer>();
			ArrayList<Integer> threes = new ArrayList<Integer>();
			ArrayList<Integer> fours = new ArrayList<Integer>();
			ArrayList<Integer> fives = new ArrayList<Integer>();
			ArrayList<Integer> sixes = new ArrayList<Integer>();

			for (int i = 0; i < N_DICE; i++) {
				if (dieResults[i] == 1) {
					ones.add(1);
				} else if (dieResults[i] == 2) {
					twos.add(1);
				} else if (dieResults[i] == 3) {
					threes.add(1);
				} else if (dieResults[i] == 4) {
					fours.add(1);
				} else if (dieResults[i] == 5) {
					fives.add(1);
				} else if (dieResults[i] == 6) {
					sixes.add(1);
				}
			}
			if (category == THREE_OF_A_KIND) {
				int n = 3;
				if (ones.size() >= n || twos.size() >= n || threes.size() >= n
						|| fours.size() >= n || fives.size() >= n
						|| sixes.size() >= n) {
					categoryMatch = true;
				}
			} else if (category == FOUR_OF_A_KIND) {
				int n = 4;
				if (ones.size() >= n || twos.size() >= n || threes.size() >= n
						|| fours.size() >= n || fives.size() >= n
						|| sixes.size() >= n) {
					categoryMatch = true;
				}
			} else if (category == YAHTZEE) {
				int n = 5;
				if (ones.size() == n || twos.size() == n || threes.size() == n
						|| fours.size() == n || fives.size() == n
						|| sixes.size() == n) {
					categoryMatch = true;
				}
			} else if (category == FULL_HOUSE) {
				int n = 3;
				if (ones.size() == n || twos.size() == n || threes.size() == n
						|| fours.size() == n || fives.size() == n
						|| sixes.size() == n) {
					int m = 2;
					if (ones.size() == m || twos.size() == m
							|| threes.size() == m || fours.size() == m
							|| fives.size() == m || sixes.size() == m) {
						categoryMatch = true;
					}
				}
			} else if (category == LARGE_STRAIGHT) {
				if (ones.size() >= 1 && twos.size() >= 1 && threes.size() >= 1
						&& fours.size() >= 1) {
					categoryMatch = true;
				} else if (twos.size() >= 1 && threes.size() >= 1
						&& fours.size() >= 1 && fives.size() >= 1) {
					categoryMatch = true;
				} else if (threes.size() >= 1 && fours.size() >= 1
						&& fives.size() >= 1 && sixes.size() >= 1) {
					categoryMatch = true;
				}
			}

		}

		return categoryMatch;
	}

}
