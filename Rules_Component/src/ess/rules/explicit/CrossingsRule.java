package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of IRule checks if a tile that is about to be placed at pos 
 * creates a cross of tiles. It does so by checking every position a corner of tile will be
 * inserted, comparing the column neighbor tile, row neighbor tile and diagonal neighbor tile.
 *  If at least two of the neighbor tiles with a common edge are the same, the rule is 
 * not broken.
 * 
 * @author Monika Schrenk
 */
public class CrossingsRule implements IRule {
    
    private Composite composite;
    
    /**
     * Initializes an instance of CrossingsRule.
     *
     * @param composite the composite
     */
    public CrossingsRule(Composite composite) {
        this.composite = composite;
    }

	/* (non-Javadoc)
	 * @see ess.rules.IRule#check(ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean check(Tile tile, Position pos) {
		return checkCorner(Corner.TOP_LEFT, tile, pos) 
				&& checkCorner(Corner.TOP_RIGHT, tile, pos) 
				&& checkCorner(Corner.BOTTOM_LEFT, tile, pos)
				&& checkCorner(Corner.BOTTOM_RIGHT, tile, pos);
	}
	
	// Tests if there are (or will be for sure later) crossings at a given corner
	// if the given tile is place at the given position.
	private boolean checkCorner(Corner corner, Tile tile, Position pos) {
	    Surface surface = composite.getSurface();
		Position cornerPos = surface.getCornerPos(tile, pos, corner);
		if (surface.isBorderPosition(cornerPos)) {
			return true;
		}
		// only inner tiles are checked here so all positions will be inside surface
		Tile diagonalNeighbourTile = surface.getDiagonalNeighbourTile(tile, pos, corner);
		Tile horizontalNeighbourTile = surface.getHorizontalNeighbourTile(tile, pos, corner);
		Tile verticalNeighbourTile = surface.getVerticalNeighbourTile(tile, pos, corner);
		
		// if the entry opposite this corner has no tile yet, there must be 1 neighbor not having a tile either
		if (diagonalNeighbourTile == null) {
			return horizontalNeighbourTile == null || verticalNeighbourTile == null;
		} 
		// if the entry opposite this corner is filled, it must be the same as one of its neighbors
		return diagonalNeighbourTile == horizontalNeighbourTile || diagonalNeighbourTile == verticalNeighbourTile;
	}

	/* (non-Javadoc)
	 * @see ess.rules.IRule#getErrorType()
	 */
	@Override
	public ErrorType getErrorType() {
		return ErrorType.CROSSINGS;
	}
	

}