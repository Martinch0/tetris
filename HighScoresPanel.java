package tetris;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel for showing the highscores in a JFrame
 * @author Martin Mihov
 *
 */
public class HighScoresPanel extends JPanel implements ItemListener{

	/**
	 * Random generated ID.
	 */
	private static final long serialVersionUID = -1304979070579728516L;

	private JPanel scores;

	private final String LEVELS = "Levels";
	private final String SURVIVAL = "Survival";
	
	/**
	 * Constructs the panel.
	 * @param gameFrame the parent JFrame
	 */
	public HighScoresPanel(final GameFrame gameFrame) {
		final HighScoresPanel highscorePanel = this;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel heading = new JPanel();
		heading.setLayout(new BoxLayout(heading, BoxLayout.X_AXIS));
		heading.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel title = new JLabel("HighScores");
		title.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(highscorePanel);
				gameFrame.showMenu(true);
			}
		});
		heading.add(back);
		heading.add(Box.createRigidArea(new Dimension(20, 0)));
		heading.add(title);
		
		JComboBox<String> comboBox = new JComboBox<String>(new String[]{LEVELS, SURVIVAL});
		comboBox.addItemListener(this);
		
		scores = new JPanel(new CardLayout());
		scores.add(highscoreLevels(), LEVELS);
		scores.add(highscoreSurvival(), SURVIVAL);
		

		add(heading);
		add(comboBox);
		add(scores);
	}
	
	/**
	 * Shows the highscores in Levels Mode.
	 * @return Panel containing the highscores in Levels Mode.
	 */
	public JPanel highscoreLevels(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(12, 3));
		int[] levelsScores = GameFrame.gameData.getLevelsScores();
		for(int i=0; i<levelsScores.length; i++) {
			panel.add(new JLabel((i+1)+": "+levelsScores[i]));
		}
		return panel;
	}

	
	/**
	 * Shows the highscores in Survival Mode.
	 * @return Panel containing the highscores in Survival Mode.
	 */
	public JPanel highscoreSurvival(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		int[] survivalScores = GameFrame.gameData.getSurvivalScores();
		for(int i=0; i<survivalScores.length; i++) {
			panel.add(new JLabel((i+1)+": "+survivalScores[i]));
			panel.add(Box.createRigidArea(new Dimension(0,10)));
		}
		return panel;
	}

	/**
	 * Changes modes on selection from dropdown.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout c = (CardLayout) scores.getLayout();
		c.show(scores, (String)e.getItem());
	}
}
