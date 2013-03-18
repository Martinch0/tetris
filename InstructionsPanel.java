package tetris;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel showing instructions for the game.
 * @author Martin Mihov
 *
 */
public class InstructionsPanel extends JPanel{

	/**
	 * Random generated ID.
	 */
	private static final long serialVersionUID = 9095567409906773047L;

	/**
	 * Constructs the panel with the instructions.
	 * @param gameFrame the parent JFrame
	 */
	public InstructionsPanel(final GameFrame gameFrame) {
		final InstructionsPanel instructionsPanel = this;
		setLayout(new GridLayout(4,1));
		
		JButton back = new JButton("Back to Main Menu");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(instructionsPanel);
				gameFrame.showMenu(true);
			}
		});
		
		add(back);
		add(new JLabel("Move the falling shape using the LEFT and RIGHT arrow keys."));
		add(new JLabel("Rotate it with the UP arrow key."));
		add(new JLabel("Drop it using the DOWN arrow key."));
	}
}
