package ess.rules.additional;

import ess.data.Composite;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

public class MinDistanceToBorderRule implements IRule {
    
    private boolean initialized;
    private Tile tileWithMinCols;
    private Tile tileWithMinRows;
    
    private enum Measurement {
        COLS, ROWS;
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
                if (tileWithMinRows == null) {
                    return false;
                }
                return hasMinDistanceToBorder(tileWithMinRows.getRows(), e, c.getSurface(), t, p) 
                        && (t.getCols() > tileWithMinRows.getCols() || t.equals(tileWithMinRows));
            case LEFT:
            case RIGHT:
                if (tileWithMinCols == null) {
                    return false;
                }
                return hasMinDistanceToBorder(tileWithMinCols.getCols(), e, c.getSurface(), t, p) 
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
            return tile == null ? 0 : tile.getCols();
        } else {
            return tile == null ? 0 : tile.getRows();
        }
    }


}
