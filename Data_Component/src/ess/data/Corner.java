package ess.data;

/**
 * This enum represents a corner of a tile in a surface. It holds information
 * about the offset to the next row and the next column, helping to avoid
 * programming specific code for each corner for checking the rules.<br>
 * Example: The offset of the top right corner is -1 for the row, because the
 * index of the neighbour row is 1 number smaller than the index of the corner
 * position and 1 for the column, because the index of the neighbour column is 1
 * number higher than the index of the corner position.
 * 
 * @author Monika Schrenk
 * 
 *
 */
public enum Corner {
    TOP_LEFT(-1, -1), TOP_RIGHT(-1, 1), BOTTOM_LEFT(1, -1), BOTTOM_RIGHT(1, 1);

    private int nextRowOffset, nextColOffset;

    // private constructor to initialize the enum objects
    private Corner(int neighbourRowOffset, int neighbourColOffset) {
        this.nextRowOffset = neighbourRowOffset;
        this.nextColOffset = neighbourColOffset;
    }

    /**
     * Get the offset of the row next to the corner position outside the tile.
     * 
     * @return The offset to the next row.
     */
    public int getNextRowOffset() {
        return nextRowOffset;
    }

    /**
     * Get the offset of the column next to the corner position outside the
     * tile.
     * 
     * @return The offset to the next column.
     */
    public int getNextColOffset() {
        return nextColOffset;
    }

}
