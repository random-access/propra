package ess.rules.additional;

import ess.data.Composite;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of IRule uses the fact that if there is a single tile with smallest width w, all
 * higher tiles must be placed at least w+1 away from the sides (left and right) of the surface. The narrowest tile itself
 * must be placed w+1 away from the sides as well. Otherwise it would be necessary to place 2 of the narrowest 
 * tiles above each other which is not allowed, if SameTileRule is active.
 * Same fact is true for the height of tiles, if there is a single tile with a smaller height the the others.
 * 
 * This is an additional rule, which means that it will only be activated if all explicit rules are active to avoid 
 * conflicting rule checks.
 * 
 * @author Monika Schrenk
 *
 */
public class MinDistanceToBorderRule implements IRule {
    
    private boolean initialized;
    private Tile tileWithMinCols;
    private Tile tileWithMinRows;
    
    private enum Measurement {
        COLS, ROWS
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.OTHER;
    }

    @Override
    public boolean check(Composite composite, Tile tile, Position pos) {
        if (!initialized) {
            init(composite);
        }
        for (Edge e : Edge.values()) {
            if (invalidEdge(e, composite, tile, pos)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean invalidEdge(Edge e, Composite c, Tile t, Position p) {
        switch (e) {
            case TOP: 
            case BOTTOM:
                return tileWithMinRows != null
                    && hasMinDistanceToBorder(tileWithMinRows.getRows(), e, c.getSurface(), t, p)
                    && (t.getCols() > tileWithMinRows.getCols() || t.equals(tileWithMinRows));
            case LEFT:
            case RIGHT:
                return tileWithMinCols != null
                    && hasMinDistanceToBorder(tileWithMinCols.getCols(), e, c.getSurface(), t, p)
                    && (t.getRows() > tileWithMinCols.getRows() || t.equals(tileWithMinCols));
            default: 
                throw new IllegalArgumentException(e + " is not a valid edge!");
        }
        
    }
    
    private boolean hasMinDistanceToBorder(int minDistance, Edge edge, Surface s, Tile tile, Position pos) {
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
   
    private void init(Composite composite) {
        tileWithMinCols = initMinTile(composite, Measurement.COLS);
        tileWithMinRows = initMinTile(composite, Measurement.ROWS);
        initialized = true;
    }
    
    private Tile initMinTile(Composite c, Measurement m) {
        Tile minTile = null;
        boolean uniqueLength = false;
        for (Tile t : c.getTileSorts()) {
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


    private int getLength(Tile tile, Measurement m) {
        if (m == Measurement.COLS) {
            return tile.getCols();
        } else {
            return tile.getRows();
        }
    }


}
