package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

/**
 * This class is an implementation IPositionFinder that returns positions
 * from top to bottom, iterating through the columns from left to right.
 */
public class TopToBottomPosFinder implements IPositionFinder {

    /**
     * TODO remove
     * Returns the next free position in the given surface, that means the next
     * position from top left to bottom right with value != -1.<br>
     * <br>
     * {@link Position} p holds the following values:
     * <ul>
     * <li>p.getRow(): the row, starting with 0 at the top of the array</li>
     * <li>p.getColumn(): the column, starting with 0 at the left side of the array</li>
     * </ul>
     * If there is no free position anymore in the surface, (-1,-1) will be
     * returned<br>
     * <br>
     * <b>Examples:</b>
     * <ul>
     * <li>p = (0,2): 1st row from top, 3rd column from left:<br>
     * _ _ x _ _ _ <br>
     * _ _ _ _ _ _</li><br>
     * <li>p = (2,1): 3rd row from top, 2nd column from left<br>
     * _ _ _ _ _ _ <br>
     * _ _ _ _ _ _ <br>
     * _ x _ _ _ _ <br>
     * _ _ _ _ _ _</li><br>
     * </ul>
     *
     * @param composite the composite
     * @param pos the pos
     * @return a point with the next free position in the given surface or null
     *         if no free position is available anymore
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
