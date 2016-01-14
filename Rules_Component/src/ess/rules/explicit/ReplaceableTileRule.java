package ess.rules.explicit;

import java.util.ArrayList;
import java.util.HashMap;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of IRule checks if a tile that is about to be placed at
 * pos forms a combination with other tiles that can be replaced by a larger
 * tile. It does so by checking if every tile larger than the tile that is about
 * to be placed inside the composite's surface, aligned with any of the 4
 * corners, is either overlapping the surface or doesn't exactly align with
 * edges of tiles. If this is the case, the rule is not broken.
 * 
 * @author Monika Schrenk
 */
public class ReplaceableTileRule implements IRule {

    private HashMap<String, ArrayList<Tile>> tilesLargerThan; 
    private Composite composite;
    
    /**
     * Initializes an instance of ReplaceableTileRule.
     *
     * @param composite the composite
     */
    public ReplaceableTileRule(Composite composite) {
        this.composite = composite;
        tilesLargerThan = new HashMap<>();
        for (Tile t : composite.getTileSorts()) {
            ArrayList<Tile> largerTiles = composite.getTilesLargerThan(t.getRows(), t.getCols(), t.getNumberOfFields());
            tilesLargerThan.put(t.getId(), largerTiles);
        }
    }

    /* (non-Javadoc)
     * @see ess.rules.IRule#check(ess.data.Tile, ess.data.Position)
     */
    @Override
    public boolean check(Tile tile, Position pos) {
        ArrayList<Tile> tiles = tilesLargerThan.get(tile.getId());
        for (Tile rTile : tiles) {
            if (tileIsReplacement(tile, pos, rTile)) {
                return false;
            }
        }
        return true;
    }

    // TODO: Either turn JavaDoc for private method into comments or add private JavaDoc
    /**
     * Tests if tile can replace an area filled with smaller tiles if aligned
     * with any of the 4 corners of the tile that will be inserted into the
     * surface of Composite c.
     * 
     * @param tile
     *            the Tile that should be tested as a replacement
     * @param pos
     *            the position where tile should get inserted
     * @param rTile
     *            the tile that is tested as a replacement
     * @return true, if this tile replaces an area filled with smaller tiles,
     *         else false
     */
    private boolean tileIsReplacement(Tile tile, Position pos, Tile rTile) {
        for (Corner corner : Corner.values()) {
            if (tileIsReplacementInCorner(tile, pos, rTile, corner)) {
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
     * @param tile
     *            the Tile that should be tested as a replacement
     * @param pos
     *            the position where tile should get inserted
     * @param rTile
     *            the tile that is tested as a replacement
     * @param corner
     *            the Corner where the tile should be inserted
     * @return true, if tile replaces an area filled with smaller tiles when
     *         positioned in corner, else false
     */
    private boolean tileIsReplacementInCorner(Tile tile, Position pos, Tile rTile, Corner corner) {
        Position rPos = getReplacementPos(tile, pos, rTile, corner);
        if (rPos == null) {
            return false;
        }
        for (Edge edge : Edge.values()) {
            if (!isTileBorder(rTile, rPos, edge, tile, pos)) {
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
     * @param rTile
     *            the tile that is tested as a replacement
     * @param rPos
     *            the left upper corner where the replacement tile would be
     *            placed
     * @param edge
     *            the edge to be tested
     * @param tile
     *            the Tile that should be tested as a replacement
     * @param pos
     *            the position where tile should get inserted
     * @return true, if this edge is a tile border, else false
     */
    private boolean isTileBorder(Tile rTile, Position rPos, Edge edge, Tile tile, Position pos) {
        Position corner1 = composite.getSurface().getCornerPos(rTile, rPos, edge.getFirstCorner());
        Position corner2 = composite.getSurface().getCornerPos(rTile, rPos, edge.getSecondCorner());
        Tile inside;
        Tile outside;
        for (int i = corner1.getRow(); i <= corner2.getRow(); i++) {
            for (int j = corner1.getCol(); j <= corner2.getCol(); j++) {

                // testing positions of new tile -> skip test, because
                // replacement is possible anyway
                if (i >= pos.getRow() && i < pos.getRow() + tile.getRows() && j >= pos.getCol()
                        && j < pos.getCol() + tile.getCols()) {
                    continue;
                }
                inside = composite.getSurface().getEntryAt(i, j);
                outside = composite.getSurface().getEntryAt(i + edge.getNextRowOffset(), j + edge.getNextColOffset());

                // no replacement, if there is no tile inside or inside &
                // outside are the same
                if (outside != null && (inside == null || outside.equals(inside)) || inside == null && outside == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Creates a virtual tile that stores the corner positions to be tested. If the tile exceeds the
     * surface's borders, null is returned.
     * 
     * @param t
     *            the Tile that should be tested as a replacement
     * @param pos
     *            the position where tile should get inserted
     * @param rTile
     *            the tile that is tested as a replacement
     * @param corner
     *            the Corner where the tile should be inserted
     * @return a SurfaceEntry with the positions of tile placed in corner, null
     *         if this entry exceeds the surface.
     */
    private Position getReplacementPos(Tile t, Position pos, Tile rTile, Corner corner) {
        Position rPos = composite.getSurface().getTopLeft(rTile, composite.getSurface().getCornerPos(t, pos, corner), corner);
        return composite.getSurface().isInsideSurface(rPos) ? rPos : null;
    }

    /* (non-Javadoc)
     * @see ess.rules.IRule#getErrorType()
     */
    @Override
    public ErrorType getErrorType() {
        return ErrorType.REPLACEABLE_TILE;
    }

}
