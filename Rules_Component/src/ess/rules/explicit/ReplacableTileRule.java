package ess.rules.explicit;

import java.util.ArrayList;
import java.util.logging.Logger;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.data.Tile;
import ess.rules.implicit.EntryExceedsSurfaceRule;
import ess.rules.sets.ErrorType;

public class ReplacableTileRule extends ExplicitRule {
	
	private static final Logger log = Logger.getGlobal();

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		Tile t = e.getTile();
		ArrayList<Tile> tiles = c.getTilesLargerThan(t.getRows(), t.getCols(), t.getNumberOfFields());
		for (Tile tile : tiles) {
			if (tileIsReplacement(c, e, tile)) {
				// log.info("replacement found for " + e);
				return false;
			}
		}
		// log.info("No larger tile replacing smaller tiles found.");
		return true;
	}

	/**
	 * Tests if tile can replace an area filled with smaller tiles fitted in any
	 * of the 4 corners of the surface entry e that was inserted last in the
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
	private boolean tileIsReplacement(Composite c, SurfaceEntry e, Tile tile) {
		for (Corner corner : Corner.values()) {
			if (tileIsReplacementInCorner(c, e, tile, corner)) {
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
	private boolean tileIsReplacementInCorner(Composite c, SurfaceEntry e, Tile tile, Corner corner) {
		SurfaceEntry coverEntry = getCoverEntry(c, e, tile, corner);
		if (coverEntry == null) {
			return false;
		}
		for (Edge edge : Edge.values()) {
			if (!isTileBorder(c, coverEntry, edge)) {
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
	private boolean isTileBorder(Composite c, SurfaceEntry coverEntry, Edge edge) {
		Position corner1 = coverEntry.getCorner(edge.getFirstCorner());
		Position corner2 = coverEntry.getCorner(edge.getSecondCorner());
		for (int i = corner1.getRow(); i <= corner2.getRow(); i++) {
			for (int j = corner1.getColumn(); j <= corner2.getColumn(); j++) {
				log.finest("Examining position " + i + "," + j + "...");
				SurfaceEntry inside = c.getSurface().getEntryAt(i, j);
				SurfaceEntry outside = c.getSurface().getEntryAt(i + edge.getNextRowOffset(), j + edge.getNextColOffset());
				if (outside != null) {
					if (inside == null || outside.equals(inside)) {
						return false;
					}
				} else {
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
	private SurfaceEntry getCoverEntry(Composite c, SurfaceEntry e, Tile tile, Corner corner) {
		SurfaceEntry entry = new SurfaceEntry(tile, e.getCorner(corner), corner);
		EntryExceedsSurfaceRule rule = new EntryExceedsSurfaceRule();
		if (rule.check(c, entry)) {
			return entry;
		}
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.REPLACEABLE_TILE;
	}

}
