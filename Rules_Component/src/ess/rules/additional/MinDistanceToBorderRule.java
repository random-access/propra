package ess.rules.additional;

import ess.data.Composite;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of <code>IRule</code> uses the fact that if there is a single Tile with smallest 
 * width w, all higher Tiles must be placed at least w+1 away from the sides (left and right) of the 
 * surface. The narrowest Tile itself must be placed w+1 away from the sides as well. Otherwise it would 
 * be necessary to place two of the narrowest tiles above each other which is not allowed, if 
 * <code>SameTileRule</code> is active. Same fact is true for the height of Tiles, if there is a single 
 * Tile with a smaller height than the others.
 * <br><br>
 * This is an additional rule, which means that it will only be activated if all
 * explicit rules are active to avoid conflicting rule checks.
 * 
 * @author Monika Schrenk
 *
 */
public class MinDistanceToBorderRule implements IRule {

    private Tile tileWithMinCols;
    private Tile tileWithMinRows;
    private Composite composite;

    // Used as input to either work with a tile's rows or columns.
    private enum Measurement {
        COLS, ROWS
    }

    /**
     * Initializes an instance of <code>MinDistanceToBorderRule</code>.
     *
     * @param composite the Composite that will be checked against this set of rules.
     */
    public MinDistanceToBorderRule(Composite composite) {
        this.composite = composite;
        tileWithMinCols = initMinTile(Measurement.COLS);
        tileWithMinRows = initMinTile(Measurement.ROWS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ess.rules.IRule#getErrorType()
     */
    @Override
    public ErrorType getErrorType() {
        return ErrorType.OTHER;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ess.rules.IRule#check(ess.data.Tile, ess.data.Position)
     */
    @Override
    public boolean check(Tile tile, Position pos) {
        for (Edge e : Edge.values()) {
            if (invalidEdge(e, tile, pos)) {
                return false;
            }
        }
        return true;
    }

    // returns true, if a tile's edge is too close to the surface's borders
    // if the tile is placed at the given position.
    private boolean invalidEdge(Edge e, Tile t, Position p) {
        switch(e) {
            case TOP:
            case BOTTOM:
                return tileWithMinRows != null && hasMinDistanceToBorder(tileWithMinRows.getRows(), e, t, p)
                        && (t.getCols() > tileWithMinRows.getCols() || t.equals(tileWithMinRows));
            case LEFT:
            case RIGHT:
                return tileWithMinCols != null && hasMinDistanceToBorder(tileWithMinCols.getCols(), e, t, p)
                        && (t.getRows() > tileWithMinCols.getRows() || t.equals(tileWithMinCols));
            default:
                throw new IllegalArgumentException(e + " is not a valid edge!");
        }

    }

    // Tests if a tile has the same distance to the surface's borders at the
    // given edge as the tile with minimal measurements.
    private boolean hasMinDistanceToBorder(int minDistance, Edge edge, Tile tile, Position pos) {
        Surface s = composite.getSurface();
        int currentRow = s.getCornerRow(tile, pos, edge.getFirstCorner());
        int currentCol = s.getCornerCol(tile, pos, edge.getFirstCorner());
        int distance = -1;
        while (s.isInsideSurface(currentRow, currentCol) && distance <= minDistance) {
            currentRow += edge.getNextRowOffset();
            currentCol += edge.getNextColOffset();
            distance++;
        }
        return distance == minDistance;
    }

    // Returns the tile with the least columns / rows, if this value is unique.
    // Otherwise null is returned.
    private Tile initMinTile(Measurement m) {
        Tile minTile = null;
        boolean uniqueLength = false;
        for (Tile t : composite.getTileSorts()) {
            if (minTile == null || getLength(t, m) < getLength(minTile, m)) {
                minTile = t;
                uniqueLength = true;
            } else if (getLength(t, m) == getLength(minTile, m)) {
                uniqueLength = false;
            }
        }
        if (!uniqueLength) {
            minTile = null;
        }
        return minTile;
    }

    // Returns either the number of rows or the number of columns of tile,
    // depending on the measurement.
    private int getLength(Tile tile, Measurement m) {
        if (m == Measurement.COLS) {
            return tile.getCols();
        } else {
            return tile.getRows();
        }
    }

    @Override
    public String getAdditionalErrorMessage() {
        return "";
    }
}
