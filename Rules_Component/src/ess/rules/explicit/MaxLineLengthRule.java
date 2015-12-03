package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

public class MaxLineLengthRule implements IRule {
    
    @Override
    public boolean check(Composite c, Tile tile, Position pos) {
        for (Edge edge : Edge.values()) {
            if (calculateLineLength(c, tile, pos, edge) > c.getMaxLineLength()) {
                return false;
            }
        }
        return true;
    }

    private int calculateLineLength(Composite c, Tile tile, Position pos, Edge edge) {
        Corner c1 = edge.getFirstCorner();
        Corner c2 = edge.getSecondCorner();

//        Position p1 = c.getSurface().getCornerPos(tile, pos, c1);
//        Position p2 = c.getSurface().getCornerPos(tile, pos, c2);
        
        int row1 = c.getSurface().getCornerRow(tile, pos, c1);
        int col1 = c.getSurface().getCornerCol(tile, pos, c1);
        int row2 = c.getSurface().getCornerRow(tile, pos, c2);
        int col2 = c.getSurface().getCornerCol(tile, pos, c2);

        return getLineLength(c.getSurface(), edge, row1, col1, -1) + getLineLength(c.getSurface(), edge, row2, col2, 1)
                + getEntryLength(edge, tile);
    }

    private int getEntryLength(Edge edge, Tile t) {
        switch(edge) {
            case TOP:
            case BOTTOM:
                return t.getCols();
            case LEFT:
            case RIGHT:
                return t.getRows();
            default:
                throw new IllegalArgumentException("Not a valid edge!");
        }
    }

    private int getLineLength(Surface s, Edge edge, int startRow, int startCol, int step) {
        boolean isLine = true;
        int currentLineLength = 0;
        Tile innerTile;
        Tile outerTile;
        switch(edge) {
            case TOP:
            case BOTTOM:
                int innerRow = startRow;
                int outerRow = innerRow + edge.getNextRowOffset();
                int currentCol = startCol + step;
                while (s.isInsideSurface(innerRow,  currentCol) && isLine) {
                    innerTile = s.getEntryAt(innerRow, currentCol);
                    outerTile = s.getEntryAt(outerRow, currentCol);
                    if (!s.isInsideSurface(outerRow, currentCol) || innerTile == null && outerTile == null || innerTile != null
                            && outerTile != null && innerTile == outerTile) {
                        isLine = false;
                    } else {
                        currentLineLength++;
                    }
                    currentCol += step;
                }
                break;
            case LEFT:
            case RIGHT:
                int innerCol = startCol;
                int outerCol = innerCol + edge.getNextColOffset();
                int currentRow = startRow + step;
                while (s.isInsideSurface(currentRow,  innerCol) && isLine) {
                    innerTile = s.getEntryAt(currentRow, innerCol);
                    outerTile = s.getEntryAt(currentRow, outerCol);
                    if (!s.isInsideSurface(currentRow, outerCol) || innerTile == null && outerTile == null || innerTile != null
                            && outerTile != null && innerTile == outerTile) {
                        isLine = false;
                    } else {
                        currentLineLength++;
                    }
                    currentRow += step;
                }
                break;
            default:
                throw new IllegalArgumentException("Not a valid edge!");
        }
        return currentLineLength;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.MAX_LINE_LENGTH;
    }
}
