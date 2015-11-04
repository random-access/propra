package ess.rules.implicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.SurfaceEntry;
import ess.rules.sets.ErrorType;

public class EntryCoversOtherTileRule implements ImplicitRule{

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		for (int i = e.getCorner(Corner.TOP_LEFT).getRow(); i <= e.getCorner(Corner.BOTTOM_LEFT).getRow(); i++) {
			for (int j = e.getCorner(Corner.TOP_LEFT).getColumn(); j <= e.getCorner(Corner.TOP_RIGHT).getColumn(); j++) {
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
