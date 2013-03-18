package tetris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * Stores the game data and has methods for saving it to a file and loading it
 * from a file.
 * 
 * @author Martin Mihov
 * 
 */
public class GameData {

	private int[] highscoresLevel;
	private int[] highscoresSurvival;
	private int maxLevel;

	/**
	 * Loads the game data from a file if it exists. Otherwise it loads with
	 * initial data.
	 */
	public void loadData() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"data.dat"));
			setMaxLevel(in.readInt());
			highscoresLevel = new int[35];
			for (int i = 0; i < 35; i++) {
				highscoresLevel[i] = in.readInt();
			}
			for (int i = 0; i < 35; i++) {
				System.out.println((i + 1) + " " + highscoresLevel[i]);
			}
			highscoresSurvival = new int[10];
			for (int i = 0; i < 10; i++) {
				highscoresSurvival[i] = in.readInt();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println((i + 1) + ": " + highscoresSurvival[i]);
			}
			in.close();
		} catch (FileNotFoundException e) {
			initializeEmptyScores();
		} catch (IOException e) {
			initializeEmptyScores();
		}
	}

	/**
	 * Saves the game data to a file.
	 */
	public void saveData() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("data.dat"));
			out.writeInt(maxLevel);
			for (int highscore : highscoresLevel) {
				out.writeInt(highscore);
			}
			for (int highscore : highscoresSurvival) {
				out.writeInt(highscore);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * Initialises with first play data.
	 */
	private void initializeEmptyScores() {
		setMaxLevel(1);
		highscoresLevel = new int[35];
		Arrays.fill(highscoresLevel, 0);
		highscoresSurvival = new int[10];
		Arrays.fill(highscoresSurvival, 0);
	}

	/**
	 * Adds a new highscore made in Levels Mode.
	 * 
	 * @param level
	 *            the level in which the highscore was made.
	 * @param score
	 *            the score that was reached.
	 */
	public void newHighScoreLevel(int level, int score) {
		if (highscoresLevel[level - 1] < score) {
			highscoresLevel[level - 1] = score;
		}
		saveData();
	}

	/**
	 * Adds a new highscore made in Survival Mode.
	 * 
	 * @param score
	 *            the score that was reached.
	 */
	public void newHighScoreSurvival(int score) {
		int i;
		for (i = 0; i < 10; i++) {
			if (score > highscoresSurvival[i])
				break;
		}
		if (i < 10) {
			int j;
			for (j = 9; j > i; j--) {
				highscoresSurvival[j] = highscoresSurvival[j - 1];
			}
			highscoresSurvival[j] = score;
		}
		saveData();
	}

	/**
	 * Sets the current level as passed and makes the next level available.
	 * 
	 * @param level
	 */
	public void levelPassed(int level) {
		if (maxLevel == level)
			maxLevel++;
		saveData();
	}

	// getters and setters

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public int[] getSurvivalScores() {
		return highscoresSurvival;
	}

	public int[] getLevelsScores() {
		return highscoresLevel;
	}
}
