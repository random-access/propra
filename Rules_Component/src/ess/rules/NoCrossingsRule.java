package ess.rules;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.SurfaceEntry;

public class NoCrossingsRule implements IRule {

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		Surface surface = c.getSurface();
		return checkCorner(Corner.TOP_LEFT, surface, e) 
				&& checkCorner(Corner.TOP_RIGHT, surface, e) 
				&& checkCorner(Corner.BOTTOM_LEFT, surface, e)
				&& checkCorner(Corner.BOTTOM_RIGHT, surface, e);
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


}