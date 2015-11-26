package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

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
		Tile diagonalNeighbourTile = surface.getDiagonalNeighbourTile(tile, pos, corner);
		Tile horizontalNeighbourTile = surface.getHorizontalNeighbourTile(tile, pos, corner);
		Tile verticalNeighbourTile = surface.getVerticalNeighbourTile(tile, pos, corner);
		
		// if the entry opposite this corner has no tile yet, there must be 1 neighbour not having a tile either
		if (diagonalNeighbourTile == null) {
			return horizontalNeighbourTile == null || verticalNeighbourTile == null;
		} 
		// if the entry opposite this corner is filled, it must be the same as one of its neighbours
		return diagonalNeighbourTile.equals(horizontalNeighbourTile) || diagonalNeighbourTile.equals(verticalNeighbourTile);
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.CROSSINGS;
	}
	

}