package ess.data;

import java.security.InvalidParameterException;

import ess.strings.CustomErrorMessages;

/**
 * This class represents the surface to be filled with tiles during validating a solution or
 * finding a solution. <br>
 * It provides several methods for basic checks concerning tiles and positions inside this surface,
 * more complex checks can be found in this project's Rule_Component.
 * 
 * @author Monika Schrenk
 *
 */
public class Surface {
    
    // TODO JUnit Tests

    private Tile[][] fields;

    /**
     * Instantiates a new surface.
     * @param rows the number of rows of this surface.
     * @param cols the number of columns of this surface.
     */
    public Surface(int rows, int cols) {
        fields = new Tile[rows][cols];
    }

    /**
     * Get the number of rows of this surface.
     * @return number of rows
     */
    public int getRows() {
        return fields.length;
    }

    /**
     * Get the number of columns of this surface.
     * @return number of columns
     */
    public int getCols() {
        return fields[0].length;
    }

    /**
     * Get the number of fields (rows x columns) of this surface.
     * @return number of fields (rows x columns)
     */
    public Tile[][] getFields() {
        return fields;
    }

    /**
     * Returns the tile at a given position in the surface.<br>
     * If the specified position is outside the surface, null is returned.
     * @param pos a position.
     * @return tile at pos or null, if pos is outside of the surface.
     */
    public Tile getEntryAt(Position pos) {
        return getEntryAt(pos.getRow(), pos.getCol());
    }

    /**
     * Returns the tile at a given row and a given column in the surface.<br>
     * If the specified row and column specify a position outside the surface, null is returned.
     * @param row the row to to look at.
     * @param col the column to look at.
     * @return tile at pos or null, if pos is outside of the surface.
     */
    public Tile getEntryAt(int row, int col) {
        return (isInsideSurface(row, col)) ? fields[row][col] : null;
    }

    /**
     * Inserts a given tile in the given surface at the given position, for
     * example: called on an empty surface with 3 rows and 4 columns, for a 3x2
     * tile with insert position (0,2) will produce the following result: <br>
     * _ _ x x x _ <br>
     * _ _ x x x _ <br>
     * _ _ _ _ _ _ <br>
     * <br>
     * <b>Hint</b>: All fields that get filled with a tile refer to the same tile object, 
     * so testing if the next field belongs to this tile as well can be performed via == operator.
     *
     * @param tile
     *            the tile that should be inserted
     * @param pos
     *            the left upper corner of the position the tile should be
     *            inserted
     */
    public void insertEntry(Tile tile, Position pos) {
        Tile newTile = new Tile(tile.getId(), tile.getRows(), tile.getCols());
        for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows() - 1; i++) {
            for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols() - 1; j++) {
                fields[i][j] = newTile;
            }
        }
    }

    /**
     * Removes a given tile in this surface at the specified position.
     * @param tile the tile sort to be removed
     * @param pos the top left position of the tile
     */
    public void removeEntry(Tile tile, Position pos) {
        for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows() - 1; i++) {
            for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols() - 1; j++) {
                fields[i][j] = null;
            }
        }
    }

    /**
     * Returns true, if the given position is an edge position of the surface, otherwise false<br>
     * A position is an edge position if its row is an edge row or its column is an edge column.
     * @param pos a position.
     * @return true, if the position's row is an edge row or the positions's column is an edge column,
     * otherwise false.
     * @see {@link #isBorderRow(int)} and {@link #isBorderCol(int)}
     */
    public boolean isBorderPosition(Position pos) {
        return isBorderRow(pos) || isBorderCol(pos);
    }

    /**
     * Returns true, if the row of a given position is an edge row.
     * @param pos a position.
     * @return true, if the position's row is an edge row,
     * otherwise false.
     * @see {@link #isBorderRow(int)}
     */
    public boolean isBorderRow(Position pos) {
        return isBorderRow(pos.getRow());
    }

    /**
     * Returns true, if the row of a given position is an edge row.<br><br>
     * A row is an edge row, if row == 0 or row == surface.getRows() - 1 
     * @param row a row.
     * @return true, if the position's row is an edge row,
     * otherwise false.
     */
    public boolean isBorderRow(int row) {
        return row == 0 || row == this.getRows() - 1;
    }

    /**
     * Returns true, if the column of a given position is an edge column.
     * @param pos a position.
     * @return true, if the position's column is an edge column,
     * otherwise false.
     * @see {@link #isBorderCol(int col)}
     */
    public boolean isBorderCol(Position pos) {
        return isBorderCol(pos.getCol());
    }
    
    /**
     * Returns true, if the column of a given position is an edge column.<br><br>
     * A column is an edge column, if col == 0 or col == surface.getRows() - 1
     * @param col a position.
     * @return true, if the position's column is an edge column,
     * otherwise false.
     */
    public boolean isBorderCol(int col) {
        return col == 0 || col == this.getCols() - 1;
    }

    /**
     * Returns true if the given row and the given col are inside
     * this surface, that means:
     * <ul>
     *      <li>0 <= row < this.getRows()</li>
     *      <li>0 <= col < this.getCols()</li>
     * </ul>
     * Otherwise, false is returned.
     * @param row a row.
     * @param col a column.
     * @return true if row and column are inside this surface, otherwise false.
     */
    public boolean isInsideSurface(int row, int col) {
        return row >= 0 && col >= 0 && row < this.getRows() && col < this.getCols();
    }

    /**
     * Returns true if the position's row and col are inside the surface, else false.
     * @param pos a position.
     * @return true if row and column of the given position are inside this surface, otherwise false.
     * @see {@link #isInsideSurface(int row, int col)}
     */
    public boolean isInsideSurface(Position pos) {
        return isInsideSurface(pos.getRow(), pos.getCol());
    }
   
    /**
     * Returns the tile in the same row as the position of the specified corner of the given tile. <br>
     * Example: <br>
     * x 3 x x x x <br>
     * x 3 1 1 x x <br>
     * If this method gets the top left corner of the tile with the id 1 at (1,2) as input,
     * it will output 3.
     * 
     * @param tile a tile.
     * @param pos position at the given corner.
     * @param c a corner of the tile.
     * @return tile at the same row as the position at the given corner.
     */
    public Tile getHorizontalNeighbourTile(Tile tile, Position pos, Corner c) {
        Position nPos = new Position(getCornerRow(tile, pos, c), getCornerCol(tile, pos, c) + c.getNextColOffset());
        return (isInsideSurface(nPos)) ? getEntryAt(nPos) : null;
    }

    /**
     * Returns the tile in the same column as the position of the specified corner of the given tile. <br>
     * Example: <br>
     * x 3 x x x x <br>
     * x 3 1 1 x x <br>
     * If this method gets the top left corner of the tile with the id 1 at (1,2) as input,
     * it will output null (because the entry above 1 is not set).
     * 
     * @param tile a tile.
     * @param pos position at the given corner.
     * @param c a corner of the tile.
     * @return tile at the same column as the position at the given corner.
     */
    public Tile getVerticalNeighbourTile(Tile tile, Position pos, Corner c) {
        Position nPos = new Position(getCornerRow(tile, pos, c) + c.getNextRowOffset(), getCornerCol(tile, pos, c));
        return (isInsideSurface(nPos)) ? getEntryAt(nPos) : null;
    }

    /**
     * Returns the tile in the same diagonal as the position of the specified corner of the given tile 
     * in the neighbor tile. <br>
     * Example: <br>
     * x 2 x x x x <br>
     * x x 1 1 x x <br>
     * If this method gets the top left corner of the tile with the id 1 at (1,2) as input,
     * it will output 2.
     * 
     * @param tile a tile.
     * @param pos position at the given corner.
     * @param c a corner of the tile.
     * @return tile at the same diagonal as the position at the given corner.
     */
    public Tile getDiagonalNeighbourTile(Tile tile, Position pos, Corner c) {
        Position nPos = new Position(getCornerRow(tile, pos, c) + c.getNextRowOffset(), getCornerCol(tile, pos, c)
                + c.getNextColOffset());
        return (isInsideSurface(nPos)) ? getEntryAt(nPos) : null;
    }
    
    
    /* public Position getDiagonalNeighbourPos(Tile tile, Position pos, Corner c) {
        Position nPos = new Position(getCornerRow(tile, pos, c) + c.getNextRowOffset(), getCornerCol(tile, pos, c)
                + c.getNextColOffset());
        return (isInsideSurface(nPos)) ? nPos : null;
    } */

    /**
     * Returns the position of a corner of tile at a given position in this surface.
     * @param t the tile sort.
     * @param p the position of the tile inside the surface.
     * @param c the corner of the given position.
     * @return the top left position of the given tile.
     * @see {@link #getCornerRow(Tile, Position, Corner)} and {@link #getCornerCol(Tile, Position, Corner)}
     */
    public Position getCornerPos(Tile t, Position p, Corner c) {
        return new Position(getCornerRow(t, p, c), getCornerCol(t, p, c));
    }

    /**
     * Get the row of a corner of a tile at the given position in this surface.
     * @param t the tile sort.
     * @param p the position of the tile inside the surface.
     * @param c the corner of the given position.
     * @return the row of the given corner of this tile.
     */
    public int getCornerRow(Tile t, Position p, Corner c) {
        switch(c) {
            case TOP_LEFT:
            case TOP_RIGHT:
                return p.getRow();
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return p.getRow() + t.getRows() - 1;
            default:
                throw new InvalidParameterException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, c));
        }
    }

    /**
     * Get the column of a corner of a tile at the given position in this surface.
     * @param t the tile sort.
     * @param p the position of the tile inside the surface.
     * @param c the corner of the given position.
     * @return the column of the given corner of this tile.
     */
    public int getCornerCol(Tile t, Position p, Corner c) {
        switch(c) {
            case TOP_LEFT:
            case BOTTOM_LEFT:
                return p.getCol();
            case TOP_RIGHT:
            case BOTTOM_RIGHT:
                return p.getCol() + t.getCols() - 1;
            default:
                throw new InvalidParameterException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, c));
        }
    }
    
    /**
     * Get the top left position of a tile in a surface from another corner and its 
     * position in the surface.
     * @param t the tile sort.
     * @param p the position of the tile inside the surface.
     * @param c the corner of the given position.
     * @return the top left position of the given tile inside this surface.
     */
    public Position getTopLeft(Tile t, Position p, Corner c) {
        switch(c) {
            case TOP_LEFT:
                return p;
            case TOP_RIGHT:
                return new Position(p.getRow(), p.getCol() - t.getCols() + 1);
            case BOTTOM_LEFT:
                return new Position(p.getRow() - t.getRows() + 1, p.getCol());
            case BOTTOM_RIGHT:
                return new Position(p.getRow() - t.getRows() + 1, p.getCol() - t.getCols() + 1);
            default:
                throw new InvalidParameterException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, c));
        }
    }

    /**
     * Human-readable, textual representation of the surface, containing their fields.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("rows: ").append(fields.length).append(", cols: ").append(fields[0].length).append("\n");
        for (Tile[] field : fields) {
            for (int j = 0; j < fields[0].length; j++) {
                Tile t = field[j];
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
