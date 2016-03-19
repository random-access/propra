package ess.data;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import ess.strings.CustomErrorMessages;

/**
 * This class represents a Surface as a 2-dimensional array to be filled with Tiles during validating 
 * a solution or finding a solution. <br>
 * Besides some basic getters and setters, this class provides methods for inserting and deleting entries 
 * and several methods for basic checks concerning Tiles and Positions inside this Surface, which are used 
 * for rule checking in Rule_Component.
 * 
 * @author Monika Schrenk
 *
 */
public class Surface {

    private Tile[][] fields;
    private ArrayList<Tile> smallestTiles;

    /**
     * Instantiates a new <code>Surface</code>.
     * @param rows the number of rows of this Surface.
     * @param cols the number of columns of this Surface.
     */
    public Surface(int rows, int cols) {
        fields = new Tile[rows][cols];
        smallestTiles = new ArrayList<>();
    }

    /**
     * Gets the number of rows of this <code>Surface</code>.
     * @return number of rows
     */
    public int getRows() {
        return fields.length;
    }

    /**
     * Gets the number of columns of this <code>Surface</code>.
     * @return number of columns
     */
    public int getCols() {
        return fields[0].length;
    }

    /**
     * Gets the number of fields (rows x columns) of this <code>Surface</code>.
     * @return number of fields (rows x columns)
     */
    public Tile[][] getFields() {
        return fields;
    }

    /**
     * Returns the tile at a given position in this <code>Surface</code>.
     * <br>
     * If the given <code>Position</code> is outside the surface, null is returned.
     * @param pos a Position.
     * @return tile at pos or null, if pos is outside of the surface.
     */
    public Tile getEntryAt(Position pos) {
        return getEntryAt(pos.getRow(), pos.getCol());
    }

    /**
     * Returns the Tile at a given row and a given column in this <code>Surface</code>.<br>
     * If the specified row and column specify a position outside the surface, null is returned.
     * @param row the row to to look at.
     * @param col the column to look at.
     * @return tile at pos or null, if pos is outside of the surface.
     */
    public Tile getEntryAt(int row, int col) {
        return (isInsideSurface(row, col)) ? fields[row][col] : null;
    }

    /**
     * Inserts a <code>Tile</code> into this <code>Surface</code> at the given <code>Position</code>. 
     * Example: called on an empty surface with 3 rows and 4 columns, for a 3x2
     * tile with insert position (0,2) will produce the following result: <br>
     * _ _ x x x _ <br>
     * _ _ x x x _ <br>
     * _ _ _ _ _ _ <br>
     * <br>
     * <b>Hint</b>: All fields that get filled with a Tile refer to the same Tile object, 
     * so testing if the next field belongs to this Tile as well can be performed via == operator.
     * To speed up calculation, this method only handles correct parameters, 
     * it must be made sure that pos is inside the surface's bounds, otherwise an
     * {@link IndexOutOfBoundsException} will be thrown.
     *
     * @param tile
     *            the Tile that should be inserted
     * @param pos
     *            the left upper corner of the Position where the Tile should be
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
     * Inserts a <code>Tile</code> at a given <code>Position</code>. Simply leaves out all fields that are 
     * not inside this <code>Surface</code>.
     * This method can be used for constructing composites which break implicit rules.
     * @param tile
     *            the Tile that should be inserted
     * @param pos
     *            the left upper corner of the Position the Tile should be
     *            inserted at
     */
    public void insertEntryWherePossible(Tile tile, Position pos) {
        if (pos == null) {
            return;
        }
        Tile newTile = new Tile(tile.getId(), tile.getRows(), tile.getCols());
        for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows() - 1; i++) {
            for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols() - 1; j++) {
                if (isInsideSurface(i, j)) {
                    fields[i][j] = newTile;
                }
            }
        }
    }
    
    

    /**
     * Removes a given <code>Tile</code> at the specified <code>Position</code>.
     * To speed up calculation, this method only handles correct parameters, 
     * it must be made sure that pos is inside the surface's bounds and
     * the correct tile gets removed, otherwise an {@link IndexOutOfBoundsException} will be thrown.
     * @param tile the Tile to be removed
     * @param pos the top left Position of the Tile
     */
    public void removeEntry(Tile tile, Position pos) {
        for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows() - 1; i++) {
            for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols() - 1; j++) {
                fields[i][j] = null;
            }
        }
    }

    /**
     * Returns <code>true</code>, if the given <code>Position</code> is an edge position of this <code>Surface</code>, 
     * otherwise <code>false</code><br>
     * A position is an edge position if its row is an edge row or its column is an edge column.
     * @param pos a position.
     * @return <code>true</code>, if the position's row is an edge row or the positions's column is an edge column,
     * otherwise <code>false</code>.
     * @see {@link #isBorderRow(int)} and {@link #isBorderCol(int)}
     */
    public boolean isBorderPosition(Position pos) {
        return isBorderRow(pos) || isBorderCol(pos);
    }

    /**
     * Returns <code>true</code>, if the row of a given position is an edge row, otherwise <code>false</code>.
     * @param pos a position.
     * @return <code>true</code>, if the position's row is an edge row,
     * otherwise <code>false</code>.
     * @see {@link #isBorderRow(int)}
     */
    public boolean isBorderRow(Position pos) {
        return isBorderRow(pos.getRow());
    }

    /**
     * Returns <code>true</code>, if the row of a given position is an edge row, otherwise <code>false</code>.<br><br>
     * A row is an edge row, if row == 0 or row == surface.getRows() - 1 
     * @param row a row.
     * @return <code>true</code>, if the position's row is an edge row,
     * otherwise <code>false</code>.
     */
    public boolean isBorderRow(int row) {
        return row == 0 || row == this.getRows() - 1;
    }

    /**
     * Returns <code>true</code>, if the column of a given position is an edge column, otherwise <code>false</code>.
     * @param pos a position.
     * @return <code>true</code>, if the position's column is an edge column,
     * otherwise <code>false</code>.
     * @see {@link #isBorderCol(int col)}
     */
    public boolean isBorderCol(Position pos) {
        return isBorderCol(pos.getCol());
    }
    
    /**
     * Returns <code>true</code>, if the column of a given position is an edge column, otherwise <code>false</code>.<br><br>
     * A column is an edge column, if col == 0 or col == surface.getRows() - 1
     * @param col a position.
     * @return <code>true</code>, if the position's column is an edge column,
     * otherwise <code>false</code>.
     */
    public boolean isBorderCol(int col) {
        return col == 0 || col == this.getCols() - 1;
    }

    /**
     * Returns <code>true</code> if the given row and the given col are inside
     * this surface. That means:
     * <ul>
     *      <li>0 <= row < this.getRows()</li>
     *      <li>0 <= col < this.getCols()</li>
     * </ul>
     * Otherwise, <code>false</code> is returned.
     * @param row a row.
     * @param col a column.
     * @return <code>true</code> if row and column are inside this surface, otherwise <code>false</code>.
     */
    public boolean isInsideSurface(int row, int col) {
        return row >= 0 && col >= 0 && row < this.getRows() && col < this.getCols();
    }

    /**
     * Returns <code>true</code> if the position's row and col are inside the surface, else <code>false</code>.
     * @param pos a position.
     * @return <code>true</code> if row and column of the given position are inside this surface, otherwise <code>false</code>.
     * @see {@link #isInsideSurface(int row, int col)}
     */
    public boolean isInsideSurface(Position pos) {
        return isInsideSurface(pos.getRow(), pos.getCol());
    }
   
    /**
     * Returns the Tile in the same row as the Position of the specified Corner of the given Tile. <br>
     * Example: <br>
     * x 3 x x x x <br>
     * x 3 1 1 x x <br>
     * If this method gets the top left corner of the Tile with the id 1 at (1,2) as input,
     * it will output 3.
     * 
     * @param tile a Tile.
     * @param pos Position at the given Corner.
     * @param c a Corner of the Tile.
     * @return the Tile at the same row as the Position at the given Corner.
     */
    public Tile getHorizontalNeighbourTile(Tile tile, Position pos, Corner c) {
        return getEntryAt(getCornerRow(tile, pos, c), getCornerCol(tile, pos, c) + c.getNextColOffset());
    }

    /**
     * Returns the Tile in the same column as the Position of the specified Corner of the given Tile. <br>
     * Example: <br>
     * x 3 x x x x <br>
     * x 3 1 1 x x <br>
     * If this method gets the top left corner of the Tile with the id 1 at (1,2) as input,
     * it will output null (because the entry above 1 is not set).
     * 
     * @param tile a Tile.
     * @param pos Position at the given Corner.
     * @param c a Corner of the Tile.
     * @return the Tile at the same column as the Position at the given Corner.
     */
    public Tile getVerticalNeighbourTile(Tile tile, Position pos, Corner c) {
        return getEntryAt(getCornerRow(tile, pos, c) + c.getNextRowOffset(), getCornerCol(tile, pos, c));
    }

    /**
     * Returns the Tile in the same diagonal as the Position of the specified Corner of the given Tile 
     * in the neighbor Tile. <br>
     * Example: <br>
     * x 2 x x x x <br>
     * x x 1 1 x x <br>
     * If this method gets the top left corner of the Tile with the id 1 at (1,2) as input,
     * it will output 2.
     * 
     * @param tile a Tile.
     * @param pos Position at the given Corner.
     * @param c a Corner of the Tile.
     * @return the Tile at the same diagonal as the Position at the given Corner.
     */
    public Tile getDiagonalNeighbourTile(Tile tile, Position pos, Corner c) {
        return getEntryAt(getCornerRow(tile, pos, c) + c.getNextRowOffset(), getCornerCol(tile, pos, c)
                + c.getNextColOffset());
    }

    /**
     * Returns the Position of a Corner of tile at a given Position in this Surface.
     * @param t the tile sort.
     * @param p the Position of the Tile inside the Surface.
     * @param c the Corner of the given Position.
     * @return the top left Position of the given Tile.
     * @see {@link #getCornerRow(Tile, Position, Corner)} and {@link #getCornerCol(Tile, Position, Corner)}
     */
    public Position getCornerPos(Tile t, Position p, Corner c) {
        return new Position(getCornerRow(t, p, c), getCornerCol(t, p, c));
    }

    /**
     * Get the row of the Corner of a Tile at the given Position in this Surface.
     * @param t the tile sort.
     * @param p the Position of the Tile inside the Surface.
     * @param c the Corner of the given Position.
     * @return the row of the given corner of this Tile.
     */
    public int getCornerRow(Tile t, Position p, Corner c) {
        switch(c) {
            case TOP_LEFT:
            case TOP_RIGHT:
                return p.getRow();
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return p.getRow() + t.getRows() - 1; 
        }
        // for enum values added in the future that are not included above by accident
        throw new InvalidParameterException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, c));
    }

    /**
     * Get the column of a Corner of a Tile at the given Position in this Surface.
     * @param t the tile sort.
     * @param p the Position of the Tile inside the Surface.
     * @param c the Corner of the given Position.
     * @return the column of the given Corner of this Tile.
     */
    public int getCornerCol(Tile t, Position p, Corner c) {
        switch(c) {
            case TOP_LEFT:
            case BOTTOM_LEFT:
                return p.getCol();
            case TOP_RIGHT:
            case BOTTOM_RIGHT:
                return p.getCol() + t.getCols() - 1;
        }
        // for enum values added in the future that are not included above by accident
        throw new InvalidParameterException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, c));
    }
    
    /**
     * Get the top left Position of a Tile in a Surface from another Corner and its 
     * Position in the Surface.
     * @param t the tile sort.
     * @param p the Position of the Tile inside the Surface.
     * @param c the Corner of the given Position.
     * @return the top left Position of the given Tile inside this Surface.
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
        }
        // for enum values added in the future that are not included above by accident
        throw new InvalidParameterException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, c));
    }
    
    /**
     * Add a tile to the list of smallest tiles placed inside this surface
     * @param smallestTile
     */
    public void addToSmallestTileList(Tile smallestTile) {
        smallestTiles.add(smallestTile);
    }
    
    /**
     * Returns a list of smallest tiles placed inside this surface.
     * Smallest tiles are all tiles with the least amount of fields.
     * This list gets filled during solving / validating a composite. 
     * Before those actions are finished, this list is empty.
     * @return a list of all smallest tiles inside this surface.
     */
    public ArrayList<Tile> getSmallestTile() {
        return smallestTiles;
    }

    /**
     * Human-readable, textual representation of this Surface, containing infos about the contents of
     * its fields.
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
