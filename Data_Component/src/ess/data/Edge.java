package ess.data;

/**
 * This Enum represents an edge of a <code>Tile</code>. It holds information
 * about the offset to the next row and the next column, helping to avoid
 * programming specific code for each corner for checking the rules.
 * For each edge is either the row offset or the column offset equal to zero.<br>
 * There are 2 corners belonging to an edge, this info is stored here as well 
 * in order to simplify iteration along an edge.
 * <br><br>
 * <b>Example</b>: The offset of the top edge is -1 for the row, because the
 * index of the neighbor row is 1 number smaller than the index of the edge
 * position and 0 for the column, because the top edge doesn't have a common
 * border with another tile in any column. The top edge goes from top left to 
 * top right, so these 2 corners are belonging to the top edge.
 * 
 * @author Monika Schrenk
 * 
 */
public enum Edge {
    
    /**
     * Top edge.
     */
	TOP(-1, 0, Corner.TOP_LEFT, Corner.TOP_RIGHT),
	
	/**
	 * Left edge.
	 */
	LEFT(0, -1, Corner.TOP_LEFT, Corner.BOTTOM_LEFT), 
	
	/**
	 * Bottom edge.
	 */
	BOTTOM(1, 0, Corner.BOTTOM_LEFT, Corner.BOTTOM_RIGHT), 
	
	/**
	 * Right edge.
	 */
	RIGHT(0, 1, Corner.TOP_RIGHT, Corner.BOTTOM_RIGHT);
	
	private int nextRowOffset, nextColOffset;
	private Corner firstCorner, secondCorner;
	
	// initialize the Enums, private by default
	Edge(int nextRowOffset, int nextColOffset, Corner firstCorner, Corner secondCorner) {
		this.nextRowOffset = nextRowOffset;
		this.nextColOffset = nextColOffset;
		this.firstCorner = firstCorner;
		this.secondCorner = secondCorner;
	}
	
	/**
	 * Gets the offset to the neighbor row, which might hold another tile.
	 * @return the offset to the neighbor row.
	 */
	public int getNextRowOffset() {
		return nextRowOffset;
	}
	
	/**
	 * Gets the offset to the neighbor column, which might hold another tile.
	 * @return the offset to the neighbor column.
	 */
	public int getNextColOffset() {
		return nextColOffset;
	}

	/**
     * Gets the first corner of this edge.
     * @return the first corner of this edge.
     */
	public Corner getFirstCorner() {
		return firstCorner;
	}

	/**
     * Gets the second corner of this edge.
     * @return the second corner of this edge.
     */
	public Corner getSecondCorner() {
		return secondCorner;
	}

}
