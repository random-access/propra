package ess.data;

import java.awt.Point;
import java.security.InvalidParameterException;

import ess.strings.CustomErrorMessages;

//TODO Javadoc
public class Surface {

	// private int rows, cols;
	private Tile[][] fields;

	public Surface(int rows, int cols) {
		// this.rows = rows;
		// this.cols = cols;
		fields = new Tile[rows][cols];
	}

	public int getRows() {
		return fields.length;
	}

	public int getCols() {
		return fields[0].length;
	}

	public Tile[][] getFields() {
		return fields;
	}
	
	public Tile getEntryAt(Position pos) {
		return (isInsideSurface(pos)) ? fields[pos.getRow()][pos.getCol()] : null;
	}
	
	public Tile getEntryAt(int row, int col) {
		return(isInsideSurface(row, col)) ? fields[row][col] : null;
	}

	/**
	 * Inserts a given tile in the given surface at the given position, for
	 * example: called on an empty surface with 3 rows and 4 columns, for a 3x2
	 * tile with insert position (0,2) will produce the following result: <br>
	 * _ _ x x x _ <br>
	 * _ _ x x x _ <br>
	 * _ _ _ _ _ _ <br>
	 * .
	 *
	 * @param surface
	 *            a 2-dimensional int array, initially filled with -1
	 * @param tile
	 *            the tile that should be inserted
	 * @param position
	 *            the left upper corner of the position the tile should be
	 *            inserted
	 */
	public void insertEntry(Tile t, Position pos) {
		Tile tile = new Tile (t.getId(), t.getRows(), t.getCols());
		for (int i = pos.getRow(); i <= pos.getRow()+t.getRows()-1; i++) {
			for (int j = pos.getCol(); j <= pos.getCol()+t.getCols()-1; j++) {
				fields[i][j] = tile;
			}
		}
	}
	
	public void removeEntry(Tile t, Position pos) {
		for (int i = pos.getRow(); i <= pos.getRow()+t.getRows()-1; i++) {
			for (int j = pos.getCol(); j <= pos.getCol()+t.getCols()-1; j++) {
				fields[i][j] = null;
			}
		}
	}

	/**
	 * Returns the next free position in the given surface, that means the next
	 * position from top left to bottom right with value != -1.<br>
	 * <br>
	 * {@link Point} p holds the following values:
	 * <ul>
	 * <li>p.x: the row, starting with 0 at the top of the array</li>
	 * <li>p.y: the column, starting with 0 at the left side of the array</li>
	 * </ul>
	 * If there is no free position anymore in the surface, (-1,-1) will be
	 * returned<br>
	 * <br>
	 * <b>Examples:</b>
	 * <ul>
	 * <li>p = (0,2): 1st row from top, 3rd column from left:<br>
	 * _ _ x _ _ _ <br>
	 * _ _ _ _ _ _</li><br>
	 * <li>p = (2,1): 3rd row from top, 2nd column from left<br>
	 * _ _ _ _ _ _ <br>
	 * _ _ _ _ _ _ <br>
	 * _ x _ _ _ _ <br>
	 * _ _ _ _ _ _</li><br>
	 * </ul>
	 * 
	 * @param surface
	 *            a 2-dimensional int array, initially filled with -1
	 * @return a point with the next free position in the given surface or null
	 *         if no free position is available anymore
	 */
	public Position getNextFreePosition() {
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[0].length; j++) {
				if (fields[i][j] == null) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}
	

	public boolean isEdgePosition(Position pos) {
		return isEdgeRow(pos) || isEdgeCol(pos);
	}
	
	public boolean isEdgeRow(Position pos) {
		return isEdgeRow(pos.getRow()); 
	}
	
	public boolean isEdgeRow(int row) {
		return row == 0 || row == this.getRows()-1; 
	}
	
	public boolean isEdgeCol(Position pos) {
		return isEdgeCol(pos.getCol());
	}
	
	public boolean isEdgeCol(int col) {
		return col == 0 || col == this.getCols()-1; 
	}
	
	public boolean isInsideSurface (int row, int col) {
		// TODO use rule from rules component
		return row >= 0 && col >= 0 && row < this.getRows() && col < this.getCols();
	}
	
	public boolean isInsideSurface(Position pos) {
		// TODO use rule from rules component
		return isInsideSurface(pos.getRow(), pos.getCol());
	}
	
	public Tile getRowCornerNeighbourEntry(Tile tile, Position pos, Corner c) {
		Position nPos = new Position(getCornerRow(tile, pos, c), 
				getCornerCol(tile, pos, c) + c.getNextColOffset());
		return (isInsideSurface(nPos)) ? getEntryAt(nPos) : null;
	}
	
	public Tile getColCornerNeighbourEntry(Tile tile, Position pos, Corner c) {
		Position nPos = new Position(getCornerRow(tile, pos, c)+c.getNextRowOffset(), 
				getCornerCol(tile, pos, c));
		return (isInsideSurface(nPos)) ? getEntryAt(nPos) : null;
	}
	
	public Tile getNeighbourCornerEntry(Tile tile, Position pos, Corner c) {
		Position nPos = new Position(getCornerRow(tile, pos, c)+c.getNextRowOffset(), 
				getCornerCol(tile, pos, c) + c.getNextColOffset());
		return (isInsideSurface(nPos)) ? getEntryAt(nPos) : null;
	}
	
	public Position getCornerPos(Tile t, Position p, Corner c) {
		return new Position(getCornerRow(t, p, c), getCornerCol(t, p, c));
	}
	
	private int getCornerRow(Tile t, Position p, Corner c) {
		switch (c) {
		case TOP_LEFT:
		case TOP_RIGHT:
			return p.getRow();
		case BOTTOM_LEFT:
		case BOTTOM_RIGHT:
			return p.getRow()+ t.getRows()-1;
			default:
				throw new InvalidParameterException(CustomErrorMessages.ERROR_INVALID_ENUM + c);
		}
	}
	private int getCornerCol(Tile t, Position p, Corner c) {
		switch (c) {
		case TOP_LEFT:
		case BOTTOM_LEFT:
			return p.getCol();
		case TOP_RIGHT:
		case BOTTOM_RIGHT:
			return p.getCol()+t.getCols()-1;
			default:
				throw new InvalidParameterException(CustomErrorMessages.ERROR_INVALID_ENUM + c);
		}
	}
	
	public Position getTopLeft(Tile t, Position p, Corner c) {
		switch (c) {
		case TOP_LEFT:
			return p;
		case TOP_RIGHT:
			return new Position (p.getRow(), p.getCol()-t.getCols()+1);
		case BOTTOM_LEFT:
			return new Position (p.getRow()-t.getRows()+1, p.getCol());
		case BOTTOM_RIGHT:
			return new Position (p.getRow()-t.getRows()+1, p.getCol()-t.getCols()+1);
			default:
				throw new InvalidParameterException(CustomErrorMessages.ERROR_INVALID_ENUM + c);
		}
	}

	/**
	 * Returns a human-readable, textual representation of the surface.
	 *
	 * @see {@link Object#toString()}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("rows: ").append(fields.length).append(", cols: ").append(fields[0].length).append("\n");
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[0].length; j++) {
				Tile t = fields[i][j];
				if (t == null) {
					sb.append("__ ");
				} else {
					sb.append(t.getId()).append(" ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
