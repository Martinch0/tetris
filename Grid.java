package tetris;

/**
 * Class representing a game grid.
 * 
 * @author Martin Mihov
 * 
 */
public class Grid {
	private Block[][] blocks;

	/**
	 * Creates the grid.
	 */
	public Grid() {
		blocks = new Block[GamePanel.GRID_WIDTH][GamePanel.GRID_HEIGHT];
	}

	/**
	 * Adds a block from the falling square to the grid.
	 * 
	 * @param b
	 *            the fallen block.
	 * @param shape
	 *            the falling shape
	 */
	public void fallenBlock(Block b, Shape shape) {
		blocks[b.getX()][b.getY()] = b;
		shape.removeBlock(b);
	}

	/**
	 * 
	 * @return the array of blocks.
	 */
	public Block[][] getBlocks() {
		return blocks;
	}

	/**
	 * Checks if a cell is valid and if it's empty
	 * 
	 * @param x
	 *            The X coordinate of the cell
	 * @param y
	 *            The Y coordinate of the cell
	 * @return true or false whether the cell is empty or not
	 */
	public boolean isCellEmpty(int x, int y) {
		if (y >= GamePanel.GRID_HEIGHT || x < 0 || x >= GamePanel.GRID_WIDTH)
			return false;
		if (y < 0)
			return blocks[x][0] == null;
		return blocks[x][y] == null;
	}

	/**
	 * Checks if the given block is a neighbour of the current block and they
	 * have the same colour.
	 * 
	 * @param x
	 *            the X coordinate of the block
	 * @param y
	 *            the Y coordinate of the block
	 * @param b
	 *            the other block to check for
	 * @return true or false whether the cell is a neighbour with the same
	 *         colour or not
	 */
	public boolean isColouredNeighbour(int x, int y, Block b) {
		if (x < 0 || y < 0 || x >= GamePanel.GRID_WIDTH
				|| y >= GamePanel.GRID_HEIGHT || blocks[x][y] == null)
			return false;
		return b.getColor().getRGB() == blocks[x][y].getColor().getRGB();
	}

	/**
	 * Removes a block from the grid.
	 * 
	 * @param b
	 *            the block to be removed
	 */
	public void removeBlock(Block b) {
		blocks[b.getX()][b.getY()] = null;
	}

	/**
	 * Checks if a shape can exist in the grid.
	 * 
	 * @param s
	 *            the shape to be checked
	 * @return whether the shape can exist or not.
	 */
	public boolean shapeCanExist(Shape s) {
		Object[] o = s.getBlocks();
		for (int i = 0; i < o.length; i++) {
			Block b = (Block) o[i];
			if (!isCellEmpty(b.getX(), b.getY())) {
				return false;
			}
		}
		return true;
	}
}
