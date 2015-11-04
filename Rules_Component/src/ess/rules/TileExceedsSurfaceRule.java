package ess.rules;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.SurfaceEntry;

public class TileExceedsSurfaceRule implements IRule {

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		for (int i = e.getCorner(Corner.TOP_LEFT).getRow(); i <= e.getCorner(Corner.BOTTOM_LEFT).getRow(); i++) {
			for (int j = e.getCorner(Corner.TOP_LEFT).getColumn(); j <= e.getCorner(Corner.TOP_RIGHT).getColumn(); j++) {
				if (i < 0 || j < 0 || i >= c.getSurface().getRows() || j >= c.getSurface().getCols()) {
					return false;
				}
			}
		}
		return true;
	}
}
