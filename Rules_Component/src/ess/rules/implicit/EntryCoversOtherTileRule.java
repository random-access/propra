package ess.rules.implicit;

import java.util.logging.Logger;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.IRule;
import ess.rules.sets.ErrorType;

public class EntryCoversOtherTileRule implements IRule{
	
	private static final Logger log = Logger.getGlobal();

	@Override
	public boolean check(Composite c, Tile tile, Position pos) {
		for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows()-1; i++) {
			for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols()-1; j++) {
				if (c.getSurface().getEntryAt(i, j) != null) {
					log.finer("entry covers other entry at " + i + ", " + j); 
					return false;
				}
			}
		}
		// log.info("entry doesn't cover other entry"); 
		return true;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}

}
