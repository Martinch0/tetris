package tetris;

import java.awt.Color;

/**
 * A class representing a single square on the grid.
 * 
 * @author Martin Mihov
 * 
 */
public class Block {

	private int x, y;
	private Color color;

	/**
	 * Initializes an object.
	 * 
	 * @param x
	 *            the distance in squares from the left side of the grid.
	 * @param y
	 *            the distance in squares from the top of the grid.
	 * @param color
	 *            the colour of the square.
	 */
	public Block(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**
	 * Moves the square one position down.
	 */
	public void moveDown() {
		y++;
	}

	/**
	 * Moves the square one position to the left.
	 */
	public void moveLeft() {
		x--;
	}

	/**
	 * Moves the square one position to the right.
	 */
	public void moveRight() {
		x++;
	}

	public boolean equals(Object o) {
		if (o instanceof Block) {
			Block s = (Block) o;
			return (x == s.getX()) && (y == s.getY());
		}
		return false;
	}

	/**
	 * Checks if a Block is a neighbour of the current Block and they have the same colour.
	 * @param s The block to be checked.
	 * @return true if the block is a neighbour with the same colour and false otherwise.
	 */
	public boolean isColouredNeighbour(Block s) {
		if (s.getX() < x - 1 || s.getX() > x + 1 || s.getY() < y - 1
				|| s.getY() > y + 1 || equals(s)
				|| s.getColor().getRGB() != getColor().getRGB())
			return false;
		else
			return true;
	}

	// Getters and setters

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getXPos() {
		return x * GamePanel.CELL_WIDTH;
	}

	public int getYPos() {
		return y * GamePanel.CELL_WIDTH;
	}

}
