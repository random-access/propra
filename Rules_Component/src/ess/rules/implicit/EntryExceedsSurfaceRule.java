package ess.rules.implicit;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of IRule checks if a tile that is about to be placed at pos 
 * exceeds the surface of composite. It does so by checking if every
 * field that should contain the new tile is inside the bounds of composite's surface. 
 * If this is the case, the rule is not broken.
 * 
 * @author Monika Schrenk
 */
public class EntryExceedsSurfaceRule implements IRule {

	@Override
	public boolean check(Composite c, Tile tile, Position pos) {
		for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows() - 1; i++) {
			for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols() - 1; j++) {
				if (i < 0 || j < 0 || i >= c.getSurface().getRows() || j >= c.getSurface().getCols()) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}

}

