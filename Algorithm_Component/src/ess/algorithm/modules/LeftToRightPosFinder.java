package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

/**
 * This class is an implementation IPositionFinder that returns positions
 * from left to right, iterating through the columns from top to bottom.
 */
public class LeftToRightPosFinder implements IPositionFinder {

	/* (non-Javadoc)
	 * @see ess.algorithm.modules.IPositionFinder#findNextFreePosition(ess.data.Composite, ess.data.Position)
	 */
	@Override
	public Position findNextFreePosition(Composite composite, Position pos) {
		int col = pos == null ? 0 : pos.getCol();
		Surface surface = composite.getSurface();
		for (int i = col; i < surface.getCols(); i++) {
			for (int j = 0; j < surface.getRows(); j++) {
				if (surface.getEntryAt(j, i) == null) {
					return new Position(j, i);
				}
			}
		}
		return null;
	}

}
