package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * A class to handle the game loop and initialising and finishing a game.
 * 
 * @author Martin Mihov
 * 
 */
public class Game implements ActionListener {

	private static final int INITIAL_SPEED = 500;

	private static Timer timer;
	private static int speed = INITIAL_SPEED;
	private static int fallSpeed = 60;
	private GamePanel gamePanel;
	private StatsPanel statsPanel;
	private GameFrame gameFrame;
	private int actionsPerformed = 0;

	/**
	 * Constructor for initialising the new game.
	 * 
	 * @param gamePanel
	 *            The GamePanel which will show the grid.
	 * @param statsPanel
	 *            The StatsPanel which will keep track of the stats and display
	 *            them.
	 * @param gameFrame
	 *            The JFrame in which the game is started.
	 */
	public Game(GamePanel gamePanel, StatsPanel statsPanel, GameFrame gameFrame) {
		timer = new Timer(getSpeed(), this);
		timer.setRepeats(true);
		setSpeed(INITIAL_SPEED);
		this.gamePanel = gamePanel;
		this.gamePanel.setGame(this);
		this.gameFrame = gameFrame;
		this.statsPanel = statsPanel;
	}

	/**
	 * The actions made every game loop.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gamePanel.getLevel() == 0) { // if the game is in Survival Mode.
			actionsPerformed++;
			if (actionsPerformed > 10 && speed > 70) {// TODO changed after
														// testing as it is too
														// fast.
				actionsPerformed = 0;
				speed = (int) (speed * 0.95);// Increase the speed.
				setSpeed(speed);
			}
		}
		gamePanel.moveShapeDown();
		gamePanel.repaint();
	}

	/**
	 * Starts the game loop.
	 */
	public void start() {
		timer.start();
	}

	/**
	 * Stops the game loop.
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * Sets the speed to fast falling.
	 */
	public static void startFallSpeed() {
		timer.setDelay(fallSpeed);
	}

	/**
	 * Sets the speed to the normal game speed.
	 */
	public static void startNormalSpeed() {
		timer.setDelay(speed);
	}

	/**
	 * Adds score to the current score.
	 * 
	 * @param s
	 *            The score to be added.
	 */
	public void addScore(int s) {
		statsPanel.addScore(s);
	}

	/**
	 * Adds a colour to the destroyed blocks stats.
	 * 
	 * @param color
	 *            The colour of the block that has been destroyed.
	 */
	public void addDestroyedBlock(Color color) {
		if (color.getRGB() == Color.red.getRGB())
			statsPanel.addDestroyedBlock(Stats.RED);
		if (color.getRGB() == Color.green.getRGB())
			statsPanel.addDestroyedBlock(Stats.GREEN);
		if (color.getRGB() == Color.blue.getRGB())
			statsPanel.addDestroyedBlock(Stats.BLUE);
		if (color.getRGB() == Color.pink.getRGB())
			statsPanel.addDestroyedBlock(Stats.PINK);
	}

	/**
	 * Shows the menu.
	 * 
	 * @param isMainMenu
	 *            if true, shows the MainMenu, otherwise shows the
	 *            PausedGameMenu
	 */
	public void showMenu(boolean isMainMenu) {
		stop();
		gameFrame.remove(gamePanel);
		gameFrame.remove(statsPanel);
		gameFrame.showMenu(isMainMenu);
	}

	/**
	 * Starts the game.
	 */
	public void startGame() {
		if (gamePanel.getLevel() != 0) {
			int s = INITIAL_SPEED;
			for (int i = 0; i < gamePanel.getLevel(); i++) {
				s = (int) (s * 0.95);
			}
			System.out.println("speed: " + s);
			setSpeed(s);
		}

		gameFrame.add(gamePanel, BorderLayout.WEST);
		gameFrame.add(statsPanel, BorderLayout.EAST);

		gameFrame.setFocusable(true);
		gameFrame.setVisible(true);

		gamePanel.requestFocus();
		start();
	}

	/**
	 * Ends the game.
	 */
	public void gameOver() {
		if (gamePanel.getLevel() == 0) {
			System.out.println("Save survival");
			GameFrame.gameData.newHighScoreSurvival(statsPanel.getScore());
		} else {
			if (statsPanel.isLevelPassed(gamePanel.getLevel())) {
				System.out.println("Level passed? " + gamePanel.getLevel());
				GameFrame.gameData.newHighScoreLevel(gamePanel.getLevel(),
						statsPanel.getScore());
				GameFrame.gameData.levelPassed(gamePanel.getLevel());
			}
		}
		statsPanel.resetScore();
		gameFrame.remove(gamePanel);
		gameFrame.remove(statsPanel);
		showMenu(true);
	}

	/**
	 * Checks if a level has been passed successfully.
	 * 
	 * @return true if all conditions are met, false otherwise.
	 */
	public boolean isLevelPassed() {
		return statsPanel.isLevelPassed(gamePanel.getLevel());
	}

	// setters and getters

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		Game.speed = speed;
		startNormalSpeed();
	}
}
