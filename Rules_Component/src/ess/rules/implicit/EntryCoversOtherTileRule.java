package ess.rules.implicit;

import java.util.logging.Logger;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.SurfaceEntry;
import ess.rules.explicit.ReplaceableTileRule;
import ess.rules.sets.ErrorType;

public class EntryCoversOtherTileRule implements ImplicitRule{
	
	private static final Logger log = Logger.getGlobal();

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		for (int i = e.getCorner(Corner.TOP_LEFT).getRow(); i <= e.getCorner(Corner.BOTTOM_LEFT).getRow(); i++) {
			for (int j = e.getCorner(Corner.TOP_LEFT).getColumn(); j <= e.getCorner(Corner.TOP_RIGHT).getColumn(); j++) {
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
