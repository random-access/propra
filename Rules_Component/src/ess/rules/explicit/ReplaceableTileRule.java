package ess.rules.explicit;

import java.util.ArrayList;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

public class ReplaceableTileRule implements IRule {

	@Override
	public boolean check(Composite c, Tile tile, Position pos) {
		ArrayList<Tile> tiles = c.getTilesLargerThan(tile.getRows(), tile.getCols(), tile.getNumberOfFields());
		for (Tile rTile : tiles) {
			if (tileIsReplacement(c, tile, pos, rTile)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Tests if tile can replace an area filled with smaller tiles if aligned with any
	 * of the 4 corners of the tile that will be inserted into the
	 * surface of Composite c.
	 * 
	 * @param c
	 *            the composite with the surface to test
	 * @param e
	 *            the SurfaceEntry that was inserted last
	 * @param tile
	 *            the Tile that should be tested as a replacement
	 * @return true, if this tile replaces an area filled with smaller tiles,
	 *         else false
	 */
	private boolean tileIsReplacement(Composite c, Tile tile, Position pos, Tile rTile) {
		for (Corner corner : Corner.values()) {
			if (tileIsReplacementInCorner(c, tile, pos, rTile, corner)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests if the given tile can replace an area filled with smaller tiles if
	 * placed in corner c of the given SurfaceEntry e. Every edge of the tile is
	 * examined. A tile replaces an area filled with smaller tiles if all of its
	 * four edges are touching another SurfaceEntry
	 * 
	 * @param c
	 *            the composite with the surface to test
	 * @param e
	 *            the SurfaceEntry that was inserted last
	 * @param tile
	 *            the Tile that should be tested as a replacement
	 * @param corner
	 *            the Corner where the tile should be inserted
	 * @return true, if tile replaces an area filled with smaller tiles when
	 *         positioned in corner, else false
	 */
	private boolean tileIsReplacementInCorner(Composite c, Tile tile, Position pos, Tile rTile, Corner corner) {
		Position rPos = getReplacementPos(c, tile, pos, rTile, corner);
		if (rPos == null) {
			return false;
		}
		for (Edge edge : Edge.values()) {
			if (!isTileBorder(c, rTile, rPos, edge, tile, pos)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Tests if the given edge is the border between 2 tiles. The given edge is
	 * the border between 2 tiles if every position at the edge is touching a
	 * different SurfaceEntry
	 * 
	 * @param c
	 *            the composite with the surface to test
	 * @param coverEntry
	 *            the virtual entry holding the positions of the tile
	 * @param edge
	 *            the edge to be tested
	 * @return true, if this edge is a tile border, else false
	 */
	private boolean isTileBorder(Composite c, Tile rTile, Position rPos, Edge edge, Tile tile, Position pos) {
		Position corner1 = c.getSurface().getCornerPos(rTile, rPos, edge.getFirstCorner());
		Position corner2 = c.getSurface().getCornerPos(rTile, rPos, edge.getSecondCorner());
		Tile inside = null;
		Tile outside = null;
		for (int i = corner1.getRow(); i <= corner2.getRow(); i++) {
			for (int j = corner1.getCol(); j <= corner2.getCol(); j++) {
				
				// testing positions of new tile -> skip test, because replacement is possible anyway
				if (i >= pos.getRow() && i < pos.getRow() + tile.getRows()
						&& j >= pos.getCol() && j < pos.getCol() + tile.getCols()) {
					continue;
				}
				inside = c.getSurface().getEntryAt(i, j);
				outside = c.getSurface().getEntryAt(i + edge.getNextRowOffset(), j + edge.getNextColOffset());
				
				// no replacement, if there is no tile inside or inside & outside are the same
				if (outside != null) {
					if (inside == null || outside.equals(inside)) {
						return false;
					}
				} else {
					// no replacement if neither outside nor inside position has a tile
					if (inside == null)  {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Creates a virtual SurfaceEntry that stores the corner positions of the
	 * tile that is tested and returns it. If the SurfaceEntry exceeds the
	 * Surface's borders, null is returned.
	 * 
	 * @param c
	 *            the composite with the surface to test
	 * @param e
	 *            the SurfaceEntry that was inserted last
	 * @param tile
	 *            the Tile that should be tested as a replacement
	 * @param corner
	 *            the Corner where the tile should be inserted
	 * @return a SurfaceEntry with the positions of tile placed in corner, null
	 *         if this entry exceeds the surface.
	 */
	private Position getReplacementPos(Composite c, Tile t, Position pos, Tile rTile, Corner corner) {
		Position rPos = c.getSurface().getTopLeft(rTile, c.getSurface().getCornerPos(t, pos, corner), corner);
		return c.getSurface().isInsideSurface(rPos) ? rPos : null;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.REPLACEABLE_TILE;
	}

}
