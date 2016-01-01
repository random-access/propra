package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;
import ess.strings.CustomErrorMessages;

/**
 * This implementation of IRule checks if a tile that is about to be placed at pos 
 * exceeds the maximum line length (causes a border in the composite's surface having 
 * a straight line longer than composite's maxTileLenght. It does so by checking if the
 * sum of the edge of the tile that will be placed, the left extension of this line
 * and the right extension of this line is larger than maxTileLength. It checks all
 * 4 edges. If all of these values are shorter than maxLineLength, the rule is 
 * not broken.
 * 
 * @author Monika Schrenk
 */
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
        }
        throw new IllegalArgumentException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, edge));
    }

    private int getLineLength(Surface s, Edge edge, int startRow, int startCol, int step) {
        boolean isLine = true;
        int currentLineLength = 0;
        Tile innerTile;
        Tile outerTile;
        switch(edge) {
            case TOP:
            case BOTTOM:
                int outerRow = startRow + edge.getNextRowOffset();
                int currentCol = startCol + step;
                while (s.isInsideSurface(startRow,  currentCol) && isLine) {
                    innerTile = s.getEntryAt(startRow, currentCol);
                    outerTile = s.getEntryAt(outerRow, currentCol);
                    if (!s.isInsideSurface(outerRow, currentCol)
                           || innerTile == null && outerTile == null 
                           || innerTile == outerTile) {
                        isLine = false;
                    } else {
                        currentLineLength++;
                    }
                    currentCol += step;
                }
                return currentLineLength;
            case LEFT:
            case RIGHT:
                int outerCol = startCol + edge.getNextColOffset();
                int currentRow = startRow + step;
                while (s.isInsideSurface(currentRow,  startCol) && isLine) {
                    innerTile = s.getEntryAt(currentRow, startCol);
                    outerTile = s.getEntryAt(currentRow, outerCol);
                    if (!s.isInsideSurface(currentRow, outerCol) 
                            || innerTile == null && outerTile == null 
                            || innerTile == outerTile) {
                        isLine = false;
                    } else {
                        currentLineLength++;
                    }
                    currentRow += step;
                }
                return currentLineLength;
        }
        throw new IllegalArgumentException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, edge));
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.MAX_LINE_LENGTH;
    }
}
