package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/** 
 * This implementation of IRule checks if a tile that is about to be placed at pos 
 * is placed exactly beneath a tile with the same ID. It does so by checking if every
 * edge has at least 1 outer neighbor entry with a different tile id or null. 
 * If this is the case, the rule is not broken.
 * 
 * @author Monika Schrenk
 */
public class SameTileRule implements IRule {

	@Override
	public boolean check(Composite c, Tile tile, Position pos) {
		for (Edge edge : Edge.values()) {
			if (hasCommonEdgeWithSameTile(c, tile, pos, edge)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean hasCommonEdgeWithSameTile(Composite c, Tile tile, Position pos, Edge edge) {
		Position corner1 = c.getSurface().getCornerPos(tile, pos, edge.getFirstCorner());
		Position corner2 = c.getSurface().getCornerPos(tile, pos, edge.getSecondCorner());
		Tile outsideTile;
		for (int i = corner1.getRow(); i <= corner2.getRow(); i++) {
			for (int j = corner1.getCol(); j <= corner2.getCol(); j++) { 
				outsideTile = c.getSurface().getEntryAt(i + edge.getNextRowOffset(), j + edge.getNextColOffset());
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
}
