package ess.rules;

import ess.data.Composite;
import ess.data.SurfaceEntry;

public class TileExceedsSurfaceRule implements IRule {

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		for (int i = e.getTopLeft().getRow(); i <= e.getBottomLeft().getRow(); i++) {
			for (int j = e.getTopLeft().getColumn(); j <= e.getTopRight().getColumn(); j++) {
				if (i < 0 || j < 0 || i >= c.getSurface().getRows() || j >= c.getSurface().getCols()) {
					return false;
				}
			}
		}
		return true;
	}
}
