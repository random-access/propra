package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

public class LeftToRightPosFinder implements IPositionFinder {

	@Override
	public Position findNextFreePosition(Composite composite, Position pos) {
		int col = pos == null ? 0 : pos.getCol();
		Surface surface = composite.getSurface();
		for (int i = col; i < surface.getCols(); i++) {
			for (int j = 0; j < surface.getRows(); j++) {
				if (surface.getEntryAt(j, i) == null) {
					// System.out.println("Next pos: " + j + ", " + i);
					return new Position(j, i);
				}
			}
		}
		return null;
	}

}
