package tetris;

import java.util.Arrays;

/**
 * Class to store the stats for the game.
 * 
 * @author Martin Mihov
 * 
 */
public class Stats {
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int PINK = 3;

	private int score = 0;
	private int[] destroyed = { 0, 0, 0, 0 };
	private int[] task;

	/**
	 * Adds score to the current score.
	 * 
	 * @param score
	 *            the score to be added.
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
	 * Adds a destroyed block to the stats
	 * 
	 * @param color
	 *            the colour of the destroyed block.
	 */
	public void addDestroyedBlock(int color) {
		assert (color >= 0 && color < 4);
		destroyed[color]++;
	}

	/**
	 * Resets the scores.
	 */
	public void resetScore() {
		score = 0;
		Arrays.fill(destroyed, 0);
		Arrays.fill(task, 0);
	}

	/**
	 * Checks if all tasks have been accomplished for the level.
	 * 
	 * @param level
	 *            the level
	 * @return whether the level has been passed or not.
	 */
	public boolean isLevelPassed(int level) {
		for (int i = 0; i < 4; i++) {
			if (task[i] > destroyed[i])
				return false;
		}
		return true;
	}

	// setters and getters

	public int getDestroyed(int color) {
		return destroyed[color];
	}

	public int[] getTask() {
		return task;
	}

	public int getTask(int color) {
		return task[color];
	}

	public void setTask(int[] task) {
		this.task = task;
	}

	public int[] getDestroyed() {
		return destroyed;
	}

	public void setDestroyed(int[] destroyed) {
		this.destroyed = destroyed;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
