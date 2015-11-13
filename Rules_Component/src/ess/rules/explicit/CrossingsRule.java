package ess.rules.explicit;

import java.util.logging.Logger;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.SurfaceEntry;
import ess.rules.IRule;
import ess.rules.sets.ErrorType;

public class CrossingsRule implements IRule {
	
	private static final Logger log = Logger.getGlobal();

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		boolean noCrossing = checkCorner(Corner.TOP_LEFT, c.getSurface(), e) 
				&& checkCorner(Corner.TOP_RIGHT, c.getSurface(), e) 
				&& checkCorner(Corner.BOTTOM_LEFT, c.getSurface(), e)
				&& checkCorner(Corner.BOTTOM_RIGHT, c.getSurface(), e);
		if(!noCrossing) {
			log.fine("Found a crossing");
		}
		return noCrossing;
	}
	
	private boolean checkCorner(Corner corner, Surface surface, SurfaceEntry e) {
		Position cornerPos = e.getCorner(corner);
		if (surface.isEdgePosition(cornerPos)) {
			return true;
		}
		SurfaceEntry cornerNeighbourEntry = surface.getNeighbourCornerEntry(e, corner);
		SurfaceEntry sameRowEntry = surface.getRowCornerNeighbourEntry(e, corner);
		SurfaceEntry sameColumnEntry = surface.getColCornerNeighbourEntry(e, corner);
		
		// if the entry opposite this corner is not filled yet, there must be 1 neighbour not filled either, e.g.
		if (cornerNeighbourEntry == null) {
			return sameRowEntry == null || sameColumnEntry == null;
		} 
		// if the entry opposite this corner is filled, it must be the same as one of its neighbours
		return cornerNeighbourEntry.equals(sameRowEntry) || cornerNeighbourEntry.equals(sameColumnEntry);
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.CROSSINGS;
	}
	

}