package tetris;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for showing the menu panels.
 * 
 * @author Martinch0
 * 
 */
public class MenuPanel extends JPanel {

	/**
	 * Random generated ID.
	 */
	private static final long serialVersionUID = 7201035890294368502L;

	private GameFrame gameFrame;

	/**
	 * Constructs either the Main Menu or the Paused Game Menu
	 * 
	 * @param isMainMenu
	 *            whether it's the main menu or not
	 * @param gameFrame
	 *            the parent JFrame
	 */
	public MenuPanel(boolean isMainMenu, GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		if (isMainMenu)
			showMainMenu();
		else
			showPauseMenu();
	}

	/**
	 * Creates the mainMenu.
	 */
	private void showMainMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentY(Component.CENTER_ALIGNMENT);

		final MenuPanel menuPanel = this;

		JLabel heading = new JLabel("SquareFall");
		heading.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		heading.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton startNewGame = new JButton("Levels Mode");
		startNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		startNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(menuPanel);
				gameFrame.showLevelsPanel();
			}
		});
		JButton survivalMode = new JButton("Survival Mode");
		survivalMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		survivalMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(menuPanel);
				gameFrame.startNewGame(0);
			}
		});
		JButton instructions = new JButton("Instructions");
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		instructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(menuPanel);
				gameFrame.showInstructionsPanel();
			}
		});
		JButton highscores = new JButton("Highscores");
		highscores.setAlignmentX(Component.CENTER_ALIGNMENT);
		highscores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(menuPanel);
				gameFrame.showHighScoresPanel();
			}
		});

		add(Box.createRigidArea(new Dimension(0, 20)));
		add(heading);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(startNewGame);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(survivalMode);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(instructions);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(highscores);
	}

	/**
	 * Creates the Paused Game menu.
	 */
	private void showPauseMenu() {
		final MenuPanel menuPanel = this;
		JButton resumeGame = new JButton("Resume");
		resumeGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(menuPanel);
				gameFrame.resumeGame();
			}
		});
		JButton returnToMainMenu = new JButton("Exit To Main Menu");
		returnToMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(menuPanel);
				gameFrame.showMenu(true);
			}
		});

		add(resumeGame);
		add(returnToMainMenu);
	}
}
