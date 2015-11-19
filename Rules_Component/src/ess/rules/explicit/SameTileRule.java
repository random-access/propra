package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.IRule;
import ess.rules.sets.ErrorType;

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
		for (int i = corner1.getRow(); i <= corner2.getRow(); i++) {
			for (int j = corner1.getCol(); j <= corner2.getCol(); j++) { 
				Tile outsideTile = c.getSurface().getEntryAt(i + edge.getNextRowOffset(), j + edge.getNextColOffset());
				if (outsideTile == null || !tile.equals(outsideTile)){
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
