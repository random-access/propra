package ess.rules.implicit;

import java.util.logging.Logger;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.IRule;
import ess.rules.sets.ErrorType;

public class EntryExceedsSurfaceRule implements IRule {
	
	private static final Logger log = Logger.getGlobal();

	@Override
	public boolean check(Composite c, Tile tile, Position pos) {
		for (int i = pos.getRow(); i <= pos.getRow() + tile.getRows()-1; i++) {
			for (int j = pos.getCol(); j <= pos.getCol() + tile.getCols()-1; j++) {
				if (i < 0 || j < 0 || i >= c.getSurface().getRows() || j >= c.getSurface().getCols()) {
					log.finer("Entry exceeds surface at " + i + ", " + j);
					return false;
				}
			}
		}
		// log.info("Entry doesn't exceed surface");
		return true;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}

}

