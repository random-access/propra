package ess.data;

/**
 * This class represents a certain field in a <code>Surface</code>, defined by
 * its row and its column.<br>
 * Rows are counted from top to bottom, columns from left to right,
 * starting with zero.<br><br>
 * Instances of this class allow any integer values in row and column,
 * regardless if they are inside the corresponding surface or not. 
 * Any checks on this matter must happen in a separate method to avoid 
 * {@link IndexOutOfBoundsException} etc.
 * 
 * @author Monika Schrenk
 *
 */
public class Position implements Comparable<Position> {

	private int row;
	private int col;
	
	/**
	 * Instantiates a new <code>Position</code>.
	 * @param row the row of this Position.
	 * @param col the column of this Position.
	 */
	public Position(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Gets the row of this <code>Position</code>.
	 * @return the row of this Position.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Sets the row of this <code>Position</code>.
	 * @param row the row of this Position.
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
     * Gets the column of this <code>Position</code>.
     * @return the column of this Position.
     */
	public int getCol() {
		return col;
	}

	/**
     * Sets the column of this <code>Position</code>.
     * @param column the column of this Position.
     */
	public void setColumn(int column) {
		this.col = column;
	}
	
	
	/**
	 * Two Positions have the same hash code, if row and column attributes have the same values.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}
	
	
	/**
	 * Two Positions are equal, if row and column attributes have the same values.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Position other = (Position) obj;
		return col == other.col && row == other.row;
	}
	
	
	/**
	 * Human-readable, textual representation of this position,
	 * showing the values of its row and column.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
	
	/**
	 * Compares two positions to make them sortable in a Collection.
	 * Given two positions, pos1 and pos2, pos2 is larger that pos1 under the
	 * following conditions:
	 * <ul>
	 *     <li>pos1 == null OR</li>
	 *     <li>pos1.getRow() <= pos2.getRow() AND</li>
	 *     <li>if pos1.getRow() == pos2.getRos(), pos1.getColumn() <= pos2.getColumn()</li>
	 * </ul>
	 *
	 * @param otherPos the other Position
	 * @return 
	 * <ul>
	 *     <li>-1 if this Position is smaller than otherPos</li> 
	 *     <li>0 if both positions are equal</li>
	 *     <li>1 if this Position is larger than otherPos</li>
	 * </ul>
	 */
	@Override
	public int compareTo(Position otherPos) {
		
		// any object is greater than null
		if (otherPos == null) {
			return 1;
		}
		
		// same object or same row & column
		if (this.equals(otherPos)) {
			return 0;
		}
		if (otherPos.getRow() < this.getRow()) {
			return 1;
		} 
		if (otherPos.getRow() > this.getRow()) {
			return -1;
		}
		
		// rows must be equal
		if (otherPos.getCol() < this.getCol()) {
			return 1;
		}
		return -1;
	}
	
	
}
