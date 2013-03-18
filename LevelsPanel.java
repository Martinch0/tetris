package tetris;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel that lets a user choose level in Levels Mode.
 * 
 * @author Martin Mihov
 * 
 */
public class LevelsPanel extends JPanel {

	/**
	 * Random generated ID.
	 */
	private static final long serialVersionUID = 7172211452609145515L;
	private GameFrame gameFrame;

	/**
	 * Creates the Panel with the buttons for levels.
	 * 
	 * @param gameFrame
	 *            the parent JFrame.
	 */
	public LevelsPanel(final GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		final LevelsPanel levelsPanel = this;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel heading = new JPanel();
		heading.setLayout(new BoxLayout(heading, BoxLayout.X_AXIS));

		JLabel title = new JLabel("Choose a level:");
		title.setFont(new Font(Font.SERIF, Font.BOLD, 30));

		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(levelsPanel);
				gameFrame.showMenu(true);
			}
		});
		heading.add(back);
		heading.add(Box.createRigidArea(new Dimension(20, 0)));
		heading.add(title);

		add(Box.createRigidArea(new Dimension(0, 10)));
		add(heading);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(addButtons(GameFrame.gameData.getMaxLevel()));
	}

	/**
	 * Creates the buttons Panel
	 * 
	 * @param lvl
	 *            the max available level
	 * @return the panel containing all buttons
	 */
	private JPanel addButtons(int lvl) {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(7, 5, 15, 15));
		for (int i = 0; i < 35; i++) {
			final int l = i + 1;
			JButton level = new JButton("" + (l));
			if (l > lvl)
				level.setEnabled(false);
			level.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("StartLevel: " + l);
					gameFrame.startNewGame(l);
				}
			});
			buttonsPanel.add(level);
		}
		return buttonsPanel;
	}
}
