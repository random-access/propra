package ess.rules.implicit;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.IRule;
import ess.rules.sets.ErrorType;

public class EntryCoversOtherTileRule implements IRule{

	@Override
	public boolean check(Composite c, Tile tile, Position pos) {
		for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows()-1; i++) {
			for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols()-1; j++) {
				if (c.getSurface().getEntryAt(i, j) != null) {
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
