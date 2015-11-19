package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.IRule;
import ess.rules.sets.ErrorType;

public class CrossingsRule implements IRule {

	@Override
	public boolean check(Composite c, Tile tile, Position pos) {
		return checkCorner(Corner.TOP_LEFT, c.getSurface(), tile, pos) 
				&& checkCorner(Corner.TOP_RIGHT, c.getSurface(), tile, pos) 
				&& checkCorner(Corner.BOTTOM_LEFT, c.getSurface(), tile, pos)
				&& checkCorner(Corner.BOTTOM_RIGHT, c.getSurface(), tile, pos);
	}
	
	private boolean checkCorner(Corner corner, Surface surface, Tile tile, Position pos) {
		Position cornerPos = surface.getCornerPos(tile, pos, corner);
		if (surface.isEdgePosition(cornerPos)) {
			return true;
		}
		// only inner tiles are checked here so all positions will be inside surface
		Tile cornerNeighbourTile = surface.getNeighbourCornerEntry(tile, pos, corner);
		Tile sameRowTile = surface.getRowCornerNeighbourEntry(tile, pos, corner);
		Tile sameColumnTile = surface.getColCornerNeighbourEntry(tile, pos, corner);
		
		// if the entry opposite this corner has no tile yet, there must be 1 neighbour not having a tile either
		if (cornerNeighbourTile == null) {
			return sameRowTile == null || sameColumnTile == null;
		} 
		// if the entry opposite this corner is filled, it must be the same as one of its neighbours
		return cornerNeighbourTile.equals(sameRowTile) || cornerNeighbourTile.equals(sameColumnTile);
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.CROSSINGS;
	}
	

}