package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

public class MinimalTileRule implements IRule {
    
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
        System.out.println("tile w min cols " + tileWithMinCols);
        System.out.println("tile w min rows " + tileWithMinRows);
        if (tileWithMinCols != null && tile.equals(tileWithMinCols) 
                && (invalidEdge(Edge.TOP, composite.getSurface(), tile, pos) 
                        || invalidEdge(Edge.BOTTOM, composite.getSurface(), tile, pos))) {
            return false;
        }
        if (tileWithMinRows != null && tile.equals(tileWithMinRows) 
                && (invalidEdge(Edge.LEFT, composite.getSurface(), tile, pos) 
                        || invalidEdge(Edge.RIGHT, composite.getSurface(), tile, pos))) {
            return false;
        }
        return true;
    }

    private boolean invalidEdge(Edge edge, Surface s, Tile tile, Position pos) {
        return (cornerIsOccupied(edge.getFirstCorner(), s, tile, pos) && cornerIsOccupied(edge.getSecondCorner(), s, tile, pos) 
                && hasSpaceAtEdge(edge, s, tile, pos));
    }

    private boolean cornerIsOccupied(Corner c, Surface s, Tile t, Position pos) {
        boolean result =  s.getDiagonalNeighbourPos(t, pos, c) == null || s.getDiagonalNeighbourTile(t, pos, c) != null;
        System.out.println("corner is occupied " + c + " ? " + result);
        return result;
    }
    
    private boolean hasSpaceAtEdge(Edge e, Surface s, Tile t, Position pos) {
        boolean result = !s.isBorderEdge(pos, t, e) 
                && s.getEntryAt(s.getCornerRow(t, pos, e.getFirstCorner()) + e.getNextRowOffset(), 
                s.getCornerCol(t, pos, e.getFirstCorner()) + e.getNextColOffset()) == null;
        System.out.println("has space at edge " + e + " ? " + result);
        return result;
                
    }
   
    private void init(Composite composite) {
        tileWithMinCols = initMinTile(composite, Measurement.COLS);
        tileWithMinRows = initMinTile(composite, Measurement.ROWS);
    }
    
    private Tile initMinTile(Composite c, Measurement m) {
        Tile minTile = null;
        boolean uniqueLength = false;
        for (Tile t : c.getTileSorts()) {
            if (minTile == null || getMeasurement(t, m) < getMeasurement(minTile, m)) {
                minTile = t;
                uniqueLength = true;
            } else if (getMeasurement(t, m) == getMeasurement(minTile, m)) {
                uniqueLength = false; 
            }
        }
        if (!uniqueLength) {
            minTile = null;
        }
        return minTile;
    }

    private int getMeasurement(Tile tile, Measurement m) {
        if (m == Measurement.COLS) {
            return tile == null ? 0 : tile.getCols();
        } else {
            return tile == null ? 0 : tile.getRows();
        }
    }


}
