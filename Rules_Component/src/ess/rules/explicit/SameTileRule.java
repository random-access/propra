package ess.rules.explicit;

import java.util.logging.Logger;

import ess.data.Composite;
import ess.data.Edge;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.rules.IRule;
import ess.rules.sets.ErrorType;

public class SameTileRule implements IRule {
	
	private static final Logger log = Logger.getGlobal();

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		for (Edge edge : Edge.values()) {
			if (hasEdgeWithSameTile(c,e,edge)) {
				log.fine("Common edge with same tile found.");
				return false;
			}
		}
		// log.info("Found no common egde with same tile.");
		return true;
	}
	
	private boolean hasEdgeWithSameTile(Composite c, SurfaceEntry e, Edge edge) {
		Position corner1 = e.getCorner(edge.getFirstCorner());
		Position corner2 = e.getCorner(edge.getSecondCorner());
		for (int i = corner1.getRow(); i <= corner2.getRow(); i++) {
			for (int j = corner1.getColumn(); j <= corner2.getColumn(); j++) { 
				SurfaceEntry outside = c.getSurface().getEntryAt(i + edge.getNextRowOffset(), j + edge.getNextColOffset());
				if (outside == null || !e.getTile().equals(outside.getTile())){
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
