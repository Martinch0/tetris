package tetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * A class for displaying the game.
 * 
 * @author Martin Mihov
 * 
 */
public class GameFrame extends JFrame {

	/**
	 * Random generated ID.
	 */
	private static final long serialVersionUID = 3487636152458113307L;

	private Game game;
	public static GameData gameData;

	/**
	 * Starts the main window.
	 * 
	 * @param args
	 *            Ignored.
	 */
	public static void main(String[] args) {
		initializeGameData();
		GameFrame frame = new GameFrame();
		frame.setSize(406, 396);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null); // center the JFrame

		frame.showMenu(true);

		// frame.startNewGame();
	}

	/**
	 * Loads the game data.
	 */
	private static void initializeGameData() {
		gameData = new GameData();
		gameData.loadData();
	}

	/**
	 * Shows the menu
	 * 
	 * @param isMainMenu
	 *            if true, shows the MainMenu, if false - the PausedGameMenu.
	 */
	public void showMenu(boolean isMainMenu) {
		MenuPanel menuPanel = new MenuPanel(isMainMenu, this);
		add(menuPanel, BorderLayout.CENTER);
		setFocusable(true);
		setVisible(true);
		menuPanel.requestFocus();
	}

	/**
	 * Starts a new game.
	 * 
	 * @param level
	 *            the level to be started. 0 for Survival Mode.
	 */
	public void startNewGame(int level) {
		StatsPanel statsPanel = new StatsPanel(new int[] { level * 5,
				level * 5, level * 5, level * 5 });
		GamePanel gamePanel = new GamePanel(level);
		game = new Game(gamePanel, statsPanel, this);
		game.startGame();
		repaint();
	}

	/**
	 * Resumes a game.
	 */
	public void resumeGame() {
		game.startGame();
		repaint();
	}

	/**
	 * Shows the Choose a level menu.
	 */
	public void showLevelsPanel() {
		LevelsPanel levelsPanel = new LevelsPanel(this);
		add(levelsPanel, BorderLayout.CENTER);
		setFocusable(true);
		setVisible(true);
		levelsPanel.requestFocus();
	}

	/**
	 * Shows the Instructions panel.
	 */
	public void showInstructionsPanel() {
		InstructionsPanel instructionsPanel = new InstructionsPanel(this);
		add(instructionsPanel, BorderLayout.CENTER);
		setFocusable(true);
		setVisible(true);
		instructionsPanel.requestFocus();
	}

	/**
	 * Shows the HighScore panel.
	 */
	public void showHighScoresPanel() {
		HighScoresPanel highscoresPanel = new HighScoresPanel(this);
		add(highscoresPanel, BorderLayout.CENTER);
		setFocusable(true);
		setVisible(true);
		highscoresPanel.requestFocus();
	}

}
