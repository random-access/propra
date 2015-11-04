package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.SurfaceEntry;
import ess.rules.sets.ErrorType;

public class CrossingsRule extends ExplicitRule {

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		Surface surface = c.getSurface();
		boolean noCrossing = checkCorner(Corner.TOP_LEFT, surface, e) 
				&& checkCorner(Corner.TOP_RIGHT, surface, e) 
				&& checkCorner(Corner.BOTTOM_LEFT, surface, e)
				&& checkCorner(Corner.BOTTOM_RIGHT, surface, e);
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
		
		if (cornerNeighbourEntry == null) {
			return sameRowEntry == null || sameColumnEntry == null;
		} 
		return cornerNeighbourEntry.equals(sameRowEntry) || cornerNeighbourEntry.equals(sameColumnEntry);
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.CROSSINGS;
	}
	

}