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
 * This implementation of <code>IRule</code> checks if a tile that is about to be placed at the given
 * Position exceeds the maximum gap length (causes a border in the composite's surface having a straight 
 * line longer than composite's <code>maxGapLength</code>. It does so by checking if the sum of the Edge 
 * of the Tile that will be placed, the left extension of this line and the right extension of this line 
 * is larger than the composite's <code>maxGapLength</code>. It checks all 4 edges. If all of these values 
 * are shorter than the composite's <code>maxGapLength</code>, the rule is not broken.
 * 
 * @author Monika Schrenk
 */
public class MaxLineLengthRule implements IRule {
    
    private Composite composite;
    
    /**
     * Initializes an instance of <code>MaxLineLengthRule</code>.
     *
     * @param composite the Composite that will be checked against this set of rules.
     */
    public MaxLineLengthRule(Composite composite) {
        this.composite = composite;
    }
    
    /* (non-Javadoc)
     * @see ess.rules.IRule#check(ess.data.Tile, ess.data.Position)
     */
    @Override
    public boolean check(Tile tile, Position pos) {
        for (Edge edge : Edge.values()) {
            if (calculateLineLength(tile, pos, edge) > composite.getMaxLineLength()) {
                return false;
            }
        }
        return true;
    }

    // Calculates the total length of a straight line in the composite which contains 
    // the given edge of the given tile. 
    // The total length is the length of the tile's edge in addition to the the extension
    // of this line going "backwards" and going "forward".
    private int calculateLineLength(Tile tile, Position pos, Edge edge) {
        Corner c1 = edge.getFirstCorner();
        Corner c2 = edge.getSecondCorner();
        
        int row1 = composite.getSurface().getCornerRow(tile, pos, c1);
        int col1 = composite.getSurface().getCornerCol(tile, pos, c1);
        int row2 = composite.getSurface().getCornerRow(tile, pos, c2);
        int col2 = composite.getSurface().getCornerCol(tile, pos, c2);

        return getLineLength(edge, row1, col1, -1) + getLineLength(edge, row2, col2, 1)
                + getEntryLength(edge, tile);
    }

    // Returns the length of a tile at the given edge.
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

    // Returns the length of the extension of a tile's edge, either going "back" or going "forward".
    private int getLineLength(Edge edge, int startRow, int startCol, int step) {
        Surface s = composite.getSurface();
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

    /* (non-Javadoc)
     * @see ess.rules.IRule#getErrorType()
     */
    @Override
    public ErrorType getErrorType() {
        return ErrorType.MAX_LINE_LENGTH;
    }
    
    /* (non-Javadoc)
     * @see ess.rules.IRule#getAdditionalErrorMessage()
     */
    @Override
    public String getAdditionalErrorMessage() {
        return "";
    }
}
