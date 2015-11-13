package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

public class TopToBottomPosFinder implements IPositionFinder {

	@Override
	public Position findNextFreePosition(Composite composite, Position pos) {
		int row = pos == null ? 0 : pos.getRow();
		Surface surface = composite.getSurface();
		for (int i = row; i < surface.getRows(); i++) {
			for (int j = 0; j < surface.getCols(); j++) {
				if (surface.getEntryAt(i, j) == null) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}	
	
}
