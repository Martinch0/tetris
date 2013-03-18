package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Represents the game field and displays it.
 * 
 * @author Martin Mihov
 * 
 */
public class GamePanel extends JPanel implements KeyListener {

	/**
	 * Random generated ID.
	 */
	private static final long serialVersionUID = -7711413563340045782L;

	public static final int CELL_WIDTH = 20;
	public static int GRID_WIDTH = 10;
	public static int GRID_HEIGHT = 18;

	private Grid grid;
	private Game game;
	private boolean allowMove = true;
	public static Shape shape;
	private int combo = 0;
	private int level;

	/**
	 * Initialises a game field.
	 * 
	 * @param level
	 *            The level of the current game. 0 for Survival mode.
	 */
	public GamePanel(int level) {
		this.setLevel(level);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(201, 100));
		grid = new Grid();
		shape = Shape.createShape();
		setFocusable(true);
		addKeyListener(this);
	}

	/**
	 * Moves the falling shape down and checks for game end.
	 */
	public void moveShapeDown() {
		if (grid.shapeCanExist(shape)) {
			Object[] blocks = shape.getBlocks();
			for (int i = 0; i < blocks.length; i++) {
				Block b = (Block) blocks[i];
				if (!grid.isCellEmpty(b.getX(), b.getY() + 1)) {
					grid.fallenBlock(b, shape);
					if (shape.getNumberOfBlocks() == 0) {
						if (checkForDestroys()) {
							if (fillShape() != 0)
								return;
						}
						if (game.isLevelPassed() && level != 0) {
							game.gameOver();
						}
						setAllowMove(true);
						Game.startNormalSpeed();
						shape = Shape.createShape();
						combo = 0;
					} else {
						setAllowMove(false);
						Game.startFallSpeed();
						moveShapeDown();
					}
					return;
				}
			}
			shape.moveDown();
			repaint();
		} else {
			game.gameOver();
		}
	}

	/**
	 * Fills all the blocks that need to fall to the falling shape.
	 * 
	 * @return the number of blocks that need to fall.
	 */
	private int fillShape() {
		shape = new Shape();
		Block[][] blocks = grid.getBlocks();
		for (int i = 0; i < blocks.length; i++) {
			boolean canFall = false;
			for (int j = blocks[i].length - 1; j >= 0; j--) {
				if (grid.isCellEmpty(i, j)) {
					canFall = true;
				} else if (canFall) {
					shape.addBlock(blocks[i][j]);
					grid.removeBlock(blocks[i][j]);
				}

			}
		}
		return shape.getNumberOfBlocks();
	}

	/**
	 * Checks if there are any combinations made that need to be destroyed.
	 * 
	 * @return whether one is found or not
	 */
	private boolean checkForDestroys() {
		boolean destroys = false;
		Block[][] blocks = grid.getBlocks();
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (blocks[i][j] != null) {
					ArrayList<Block> group = new ArrayList<Block>();
					getGroup(blocks[i][j], group);
					if (group.size() >= 4) {
						combo++;
						game.addScore(group.size() * combo);
						System.out.println("Add score: " + group.size()
								+ " and multiplier " + (combo));
						for (Iterator<Block> k = group.iterator(); k.hasNext();) {
							Block r = (Block) k.next();
							game.addDestroyedBlock(r.getColor());
							grid.removeBlock(r);
						}
						destroys = true;
					}
				}
			}
		}
		return destroys;
	}

	/**
	 * Returns a group of the same coloured blocks that are neighbours.
	 * 
	 * @param b
	 *            The initial block.
	 * @param i
	 *            The previous found blocks.
	 */
	private void getGroup(Block b, ArrayList<Block> i) {
		if (!i.contains(b)) {
			i.add(b);
			if (grid.isColouredNeighbour(b.getX() + 1, b.getY(), b))
				getGroup(new Block(b.getX() + 1, b.getY(), b.getColor()), i);
			if (grid.isColouredNeighbour(b.getX(), b.getY() + 1, b))
				getGroup(new Block(b.getX(), b.getY() + 1, b.getColor()), i);
			if (grid.isColouredNeighbour(b.getX() - 1, b.getY(), b))
				getGroup(new Block(b.getX() - 1, b.getY(), b.getColor()), i);
			if (grid.isColouredNeighbour(b.getX(), b.getY() - 1, b))
				getGroup(new Block(b.getX(), b.getY() - 1, b.getColor()), i);
		}
	}

	/**
	 * Moves the shape left, if it's allowed.
	 */
	private void moveShapeLeft() {
		if (isAllowMove()) {
			Object[] blocks = shape.getBlocks();
			for (int i = 0; i < blocks.length; i++) {
				Block b = (Block) blocks[i];
				if (!grid.isCellEmpty(b.getX() - 1, b.getY()))
					return;
			}
			shape.moveLeft();
			repaint();
		}
	}

	/**
	 * Moves the shape right, if it's allowed.
	 */
	private void moveShapeRight() {
		if (isAllowMove()) {
			Object[] blocks = shape.getBlocks();
			for (int i = 0; i < blocks.length; i++) {
				Block b = (Block) blocks[i];
				if (!grid.isCellEmpty(b.getX() + 1, b.getY()))
					return;
			}
			shape.moveRight();
			repaint();
		}
	}

	/**
	 * Rotates the shape, if it's allowed.
	 */
	private void rotateShape() {
		if (isAllowMove()) {
			Shape s = shape.rotate();
			if (grid.shapeCanExist(s)) {
				shape = s;
				repaint();
			}
		}
	}

	/**
	 * Paints the grid on the panel.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Block[][] blocks = grid.getBlocks();
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (blocks[i][j] != null) {
					drawBlock(blocks[i][j], g);
				}
			}
		}
		Object[] shapeBlocks = shape.getBlocks();
		for (int i = 0; i < shapeBlocks.length; i++) {
			drawBlock((Block) shapeBlocks[i], g);
		}
	}

	/**
	 * Draws a single block.
	 * 
	 * @param c
	 *            the block to be drawn.
	 * @param g
	 *            the graphics to be drawn onto.
	 */
	public void drawBlock(Block c, Graphics g) {
		g.setColor(Color.black);
		g.drawRect(c.getXPos(), c.getYPos(), CELL_WIDTH, CELL_WIDTH);
		g.setColor(c.getColor());
		g.fillRect(c.getXPos() + 1, c.getYPos() + 1, CELL_WIDTH - 1,
				CELL_WIDTH - 1);
	}

	/**
	 * Checks if move is allowed.
	 * 
	 * @return true if it's allowed and false otherwise.
	 */
	public boolean isAllowMove() {
		return allowMove;
	}

	/**
	 * Reacts on user input.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveShapeLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveShapeRight();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			rotateShape();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveShapeDown();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.showMenu(false);
		}
	}

	// Required methods from KeyListener

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// setters and getters

	public void setAllowMove(boolean allowMove) {
		this.allowMove = allowMove;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
