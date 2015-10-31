package ess.rules;

import ess.data.Position;
import ess.data.Surface;
import ess.data.SurfaceEntry;
import ess.rules.helpers.Corner;

public abstract class AbstractTileRule {
	
	protected Position getCornerPosition(Corner corner, SurfaceEntry e) {
		switch (corner) {
		case TOP_LEFT:
			return e.getTopLeft();
		case TOP_RIGHT:
			return e.getTopRight();
		case BOTTOM_LEFT:
			return e.getBottomLeft();
		case BOTTOM_RIGHT:
			return e.getBottomRight();
		default:
			return null;
		}
	}
	
	protected boolean isEdgePosition(Surface surface, Position pos) {
		return pos.getRow() == 0 || pos.getColumn() == 0 || pos.getRow() == surface.getRows()-1 || pos.getColumn() == surface.getCols()-1;
	}
	
	protected boolean isInsideSurface(Surface surface, Position pos) {
		return pos.getRow() >= 0 || pos.getColumn() >= 0 || pos.getRow() < surface.getRows() || pos.getColumn() < surface.getCols();
	}
}
