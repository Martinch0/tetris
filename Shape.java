package tetris;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A class representing a falling block. It contains 1-4 squares in a randomly
 * generated shape and allows rotation.
 * 
 * @author Martin Mihov
 * 
 */
public class Shape {

	private ArrayList<Block> shape = new ArrayList<Block>();

	/**
	 * Factory method to create a new random shape.
	 * 
	 * @return the created random block.
	 */
	public static Shape createShape() {
		Shape b = new Shape();
		int i = (int) ((Math.random() * 10) % 4);
		b.addInitialBlock();
		for (; i > 0; i--)
			b.addAdjacentSquare();
		return b;
	}

	/**
	 * Adds a Block next to one of the blocks already in the shape.
	 */
	private void addAdjacentSquare() {
		int nX = 0, nY = 0;
		int i = getRandNumber(shape.size()) - 1;
		Block current = shape.get(i);
		Color randColor = getRandColor();
		int pos = getRandNumber(4) - 1;
		while (true) {
			switch (pos) {
			case 0:
				nX = 0;
				nY = 1;
				break;
			case 1:
				nX = 0;
				nY = -1;
				break;
			case 2:
				nX = 1;
				nY = 0;
				break;
			case 3:
				nX = -1;
				nY = 0;
				break;
			default:
				nX = 0;
				nY = 0;
			}
			Block s = new Block(current.getX() + nX, current.getY() + nY,
					randColor);
			if (s.getY() <= -1 && !shape.contains(s)) {
				shape.add(s);
				break;
			}
			pos = (pos + 1) % 4;
		}
	}

	/**
	 * Adds a random Block to the shape.
	 */
	private void addInitialBlock() {
		shape.add(new Block(5, -1, getRandColor()));
	}

	/**
	 * Adds a block to the shape.
	 * 
	 * @param s
	 *            the shape to be added.
	 */
	public void addBlock(Block s) {
		shape.add(s);
	}

	/**
	 * Removes a block from the shape.
	 * 
	 * @param s
	 *            the block to be removed
	 */
	public void removeBlock(Block s) {
		shape.remove(s);
	}

	/**
	 * 
	 * @return the array of Blocks
	 */
	public Object[] getBlocks() {
		return shape.toArray();
	}

	/**
	 * Returns one of 4 random colours: blue, green, red or pink
	 * 
	 * @return the random colour
	 */
	private Color getRandColor() {
		int i = getRandNumber(4);
		switch (i) {
		case 1:
			return Color.blue;
		case 2:
			return Color.green;
		case 3:
			return Color.red;
		case 4:
			return Color.pink;
		default:
			return Color.blue;
		}
	}

	/**
	 * 
	 * @param i
	 *            max number
	 * @return Returns a random number
	 */
	private int getRandNumber(int i) {
		return ((int) ((Math.random() * 10) % i)) + 1;
	}

	/**
	 * 
	 * @return the number of blocks in the shape.
	 */
	public int getNumberOfBlocks() {
		return shape.size();
	}

	/**
	 * Clears the shape.
	 */
	public void clear() {
		shape.clear();
	}

	public void moveDown() {
		for (Block b : shape) {
			b.moveDown();
		}
	}

	public void moveLeft() {
		for (Block b : shape) {
			b.moveLeft();
		}
	}

	public void moveRight() {
		for (Block b : shape) {
			b.moveRight();
		}
	}

	public Shape rotate() {
		Shape s = new Shape();
		s.addBlock(shape.get(0));
		Block first = shape.get(0);
		for (int i = 1; i < shape.size(); i++) {
			Block old = shape.get(i);
			int x = first.getY() - old.getY();
			int y = -(first.getX() - old.getX());
			Block b = new Block(first.getX() + x, first.getY() + y,
					old.getColor());
			s.addBlock(b);
		}
		return s;
	}
}
