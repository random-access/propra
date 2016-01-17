package ess.rules.implicit;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of <code>IRule</code> checks if a Tile that is about to be placed at the 
 * given Position covers any other Tile. It does so by checking if every field that should contain 
 * the new Tile is still empty. If this is the case, the rule is not broken.
 * 
 * @author Monika Schrenk
 */
public class TileCoversOtherTileRule implements IRule {
    
    private Composite composite;
    
    /**
     * Initializes an instance of <code>TileCoversOtherTileRule</code>.
     *
     * @param composite the Composite that will be checked against this set of rules.
     */
    public TileCoversOtherTileRule(Composite composite) {
        this.composite = composite;
    }

	/* (non-Javadoc)
	 * @see ess.rules.IRule#check(ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean check(Tile tile, Position pos) {
		for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows() - 1; i++) {
			for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols() - 1; j++) {
				if (composite.getSurface().getEntryAt(i, j) != null) {
					return false;
				}
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see ess.rules.IRule#getErrorType()
	 */
	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}
}
