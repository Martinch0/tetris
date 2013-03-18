package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Panel to show the stats of the current game.
 * 
 * @author Martin Mihov
 * 
 */
public class StatsPanel extends JPanel {

	/**
	 * Random generated ID.
	 */
	private static final long serialVersionUID = -3974341102292327756L;

	private Stats stats;

	/**
	 * Initialises the panel.
	 * 
	 * @param task
	 */
	public StatsPanel(int[] task) {
		stats = new Stats();
		stats.setTask(task);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(200, 300));
	}

	/**
	 * Paints the corresponding stats about the current game.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Score: " + stats.getScore(), 10, 20);
		g.drawString("Red: " + stats.getDestroyed(Stats.RED), 10, 60);
		g.drawString("Green: " + stats.getDestroyed(Stats.GREEN), 10, 80);
		g.drawString("Blue: " + stats.getDestroyed(Stats.BLUE), 10, 100);
		g.drawString("Pink: " + stats.getDestroyed(Stats.PINK), 10, 120);

		if (stats.getTask(0) != 0) {
			g.drawString("Task" + stats.getTask(0) + " " + stats.getTask(1)
					+ " " + stats.getTask(2) + " " + stats.getTask(3), 10, 140);
			float filled;
			g.drawRect(30, 230, 20, 100);
			g.setColor(Color.red);
			filled = 99.0f * ((float) stats.getDestroyed(Stats.RED) / (float) stats
					.getTask(Stats.RED));
			if (filled > 99.0f)
				filled = 99.0f;
			g.fillRect(31, 231 + (99 - (int) filled), 19, (int) filled);
			g.setColor(Color.black);

			g.drawRect(70, 230, 20, 100);
			g.setColor(Color.green);
			filled = 99.0f * ((float) stats.getDestroyed(Stats.GREEN) / (float) stats
					.getTask(Stats.GREEN));
			if (filled > 99.0f)
				filled = 99.0f;
			g.fillRect(71, 231 + (99 - (int) filled), 19, (int) filled);
			g.setColor(Color.black);

			g.drawRect(110, 230, 20, 100);
			g.setColor(Color.blue);
			filled = 99.0f * ((float) stats.getDestroyed(Stats.BLUE) / (float) stats
					.getTask(Stats.BLUE));
			if (filled > 99.0f)
				filled = 99.0f;
			g.fillRect(111, 231 + (99 - (int) filled), 19, (int) filled);
			g.setColor(Color.black);

			g.drawRect(150, 230, 20, 100);
			g.setColor(Color.pink);
			filled = 99.0f * ((float) stats.getDestroyed(Stats.PINK) / (float) stats
					.getTask(Stats.PINK));
			if (filled > 99.0f)
				filled = 99.0f;
			g.fillRect(151, 231 + (99 - (int) filled), 19, (int) filled);
			g.setColor(Color.black);
		}
	}

	/**
	 * Adds score to the current score.
	 * 
	 * @param score
	 *            the score to be added.
	 */
	public void addScore(int score) {
		stats.addScore(score);
		this.repaint();
	}

	/**
	 * Adds destroyed block to the stats.
	 * 
	 * @param color
	 *            the color of the block.
	 */
	public void addDestroyedBlock(int color) {
		stats.addDestroyedBlock(color);
		this.repaint();
	}

	/**
	 * Resets the score of the current game.
	 */
	public void resetScore() {
		stats.resetScore();
	}

	/**
	 * Checks if all tasks have been completed.
	 * 
	 * @param level
	 *            the current level of the game
	 * @return whether the level has been passed or not.
	 */
	public boolean isLevelPassed(int level) {
		return stats.isLevelPassed(level);
	}

	// getters and setters

	public int getScore() {
		return stats.getScore();
	}

	public int[] getDestroyed() {
		return stats.getDestroyed();
	}

	public void setDestroyed(int[] destroyed) {
		stats.setDestroyed(destroyed);
	}
}
