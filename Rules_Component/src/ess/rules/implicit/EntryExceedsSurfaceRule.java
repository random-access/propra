package ess.rules.implicit;

import java.util.logging.Logger;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.SurfaceEntry;
import ess.rules.explicit.ReplacableTileRule;
import ess.rules.sets.ErrorType;

public class EntryExceedsSurfaceRule implements ImplicitRule {
	
	private static final Logger log = Logger.getGlobal();

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		for (int i = e.getCorner(Corner.TOP_LEFT).getRow(); i <= e.getCorner(Corner.BOTTOM_LEFT).getRow(); i++) {
			for (int j = e.getCorner(Corner.TOP_LEFT).getColumn(); j <= e.getCorner(Corner.TOP_RIGHT).getColumn(); j++) {
				if (i < 0 || j < 0 || i >= c.getSurface().getRows() || j >= c.getSurface().getCols()) {
					log.info("Entry exceeds surface at " + i + ", " + j);
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

