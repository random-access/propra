package ess.rules.implicit;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of IRule checks if a tile that is about to be placed at pos 
 * covers any other tile. It does so by checking if every
 * field that should contain the new tile is still empty. If this is the case, the rule is 
 * not broken.
 * 
 * @author Monika Schrenk
 */
public class TileCoversOtherTileRule implements IRule {
    
    private Composite composite;
    
    /**
     * Initializes an instance of TileCoversOtherTileRule.
     *
     * @param composite the composite
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
