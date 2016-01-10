package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

/**
 * This class is an implementation IPositionFinder that returns positions
 * from top to bottom, iterating through the columns from left to right. <br>
 * <br>
 * Example (order of positions that are returned): <br>
 * <br>
 * a b c d<br>
 * <br>
 * a b c d <br>
 * d e f g <br>
 * <br>
 * a b c d<br>
 * d e f g <br>
 * h i j k 
 */
public class TopToBottomPosFinder implements IPositionFinder {

    /**
     * Returns the next free position in the given surface, which is the next
     * position in the array from top to bottom holding a null value.
     * 
     * @see IPositionFinder#findNextFreePosition(Composite, Position)
     */
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
