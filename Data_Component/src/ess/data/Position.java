package ess.data;

//TODO Javadoc
public class Position implements Comparable<Position>{
	
	private int row, column;

	public Position(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "row: " + row + ", col: " + column;
	}

	@Override
	public int compareTo(Position otherPos) {
		
		// any object is greater than null
		if (otherPos == null) {
			return 1;
		}
		
		// same object has same values
		if (otherPos == this) {
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
		if (otherPos.getCol() > this.getCol()) {
			return -1;
		}
		
		// rows & cols must be equel
		return 0;
	}
	
	
}
