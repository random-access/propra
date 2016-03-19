package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/** 
 * This implementation of <code>IRule</code> checks if a Tile that is about to be placed at the given 
 * Position is placed exactly beneath a Tile with the same ID. It does so by checking if every Edge has 
 * at least 1 outer neighbor Tile with a different id or null. 
 * If this is the case, the rule is not broken.
 * 
 * @author Monika Schrenk
 */
public class SameTileRule implements IRule {
    
    private Composite composite;
    
    /**
     * Initializes an instance of <code>SameTileRule</code>.
     * @param composite the Composite that will be checked against this set of rules.
     */
    public SameTileRule(Composite composite) {
        this.composite = composite;
    }

	@Override
	public boolean check(Tile tile, Position pos) {
		for (Edge edge : Edge.values()) {
			if (hasCommonEdgeWithSameTile(tile, pos, edge)) {
				return false;
			}
		}
		return true;
	}
	
	// Tests if a tile has a common edge with another tile with the same ID at 
	// a given edge.
	private boolean hasCommonEdgeWithSameTile(Tile tile, Position pos, Edge edge) {
	    Surface s = composite.getSurface();
		Position corner1 = s.getCornerPos(tile, pos, edge.getFirstCorner());
		Position corner2 = s.getCornerPos(tile, pos, edge.getSecondCorner());
		Tile outsideTile;
		for (int i = corner1.getRow(); i <= corner2.getRow(); i++) {
			for (int j = corner1.getCol(); j <= corner2.getCol(); j++) { 
				outsideTile = s.getEntryAt(i + edge.getNextRowOffset(), j + edge.getNextColOffset());
				if (outsideTile == null || !tile.equals(outsideTile)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.SAME_TILE;
	}
	
    @Override
    public String getAdditionalErrorMessage() {
        return "";
    }
}
