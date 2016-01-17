package ess.data;

/**
 * This class represents a certain field in a surface, defined by
 * its row and its column.<br>
 * Rows are counted from top to bottom, columns from left to right,
 * starting with zero.<br><br>
 * Instances of this class allow any integer values in row and column,
 * regardless if they are inside the corresponding surface or not. 
 * Any checks on this matter must happen in the surface itself.
 * 
 * @author Monika Schrenk
 *
 */
public class Position implements Comparable<Position> {

	private int row;
	private int col;
	
	/**
	 * Instantiate a new position.
	 * @param row the row of this position.
	 * @param col the column of this position.
	 */
	public Position(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Get the row of this position.
	 * @return the row of this position.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Set the row of this position.
	 * @param row the row of this position.
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
     * Get the column of this position.
     * @return the column of this position.
     */
	public int getCol() {
		return col;
	}

	/**
     * Set the column of this position.
     * @param column the column of this position.
     */
	public void setColumn(int column) {
		this.col = column;
	}
	
	
	/**
	 * Two positions have the same Hashcode, if row and column attributes have the same values.
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
	 * Two positions are equal, if row and column attributes have the same values.
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
	 * @param otherPos the other pos
	 * @return the int
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
