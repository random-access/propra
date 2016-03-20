package ess.rules.endconditions;

import java.util.ArrayList;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of <code>IRule</code> checks if all tiles were used for building the composite. 
 * It does so by iterating through the surface and adding every tile in a list, until the size of 
 * the list of used tiles is equal to the size of available tiles. If this is the case, all tiles were
 * used, and the check gets aborted returning true, else it continues until the end, returning false.
 * The list of used tiles gets stored in Surface because it is useful for some UI tasks (smallest tile used,
 * largest tile used,...)
 * 
 * @author Monika Schrenk
 */
public class UsedAllTilesRule implements IRule{
    
    private Composite composite;
    private ArrayList<Tile> usedTiles;

    /**
     * Initializes an instance of <code>UsedAllTilesRule</code>.
     *
     * @param composite the Composite that will be checked against this set of rules.
     */
    public UsedAllTilesRule(Composite composite) {
        this.composite = composite;
        usedTiles = new ArrayList<>();
    }

    /* (non-Javadoc)
     * @see ess.rules.IRule#getErrorType()
     */
    @Override
    public ErrorType getErrorType() {
        return ErrorType.NOT_ALL_TILES_USED;
    }

    /* (non-Javadoc)
     * @see ess.rules.IRule#check(ess.data.Tile, ess.data.Position)
     */
    @Override
    public boolean check(Tile tile, Position pos) {
        for (int i = 0; i < composite.getSurface().getRows(); i++) {
            for (int j = 0; j < composite.getSurface().getCols(); j++) {
                Tile currentTile = composite.getSurface().getEntryAt(i, j);
                if (!usedTiles.contains(currentTile)) {
                    usedTiles.add(currentTile);
                }
                if (usedTiles.size() == composite.getTileSorts().size()) {
                    composite.getSurface().setUsedTiles(usedTiles);
                    return true;
                }
            }
        }
        composite.getSurface().setUsedTiles(usedTiles);
        return false;
    }
    
    /* (non-Javadoc)
     * @see ess.rules.IRule#getAdditionalErrorMessage()
     */
    @Override
    public String getAdditionalErrorMessage() {
        StringBuilder sb = new StringBuilder("Unbenutzte Fliesen: ");
        for (Tile t : composite.getTileSorts()) {
            if (!usedTiles.contains(t)) {
                sb.append("Fliese " + t.getId()).append(", ");
            }
        }
        if (sb.length() >= 2) {
            sb.replace(sb.length() -2, sb.length(), "");
        }
        return sb.toString();
    }

}