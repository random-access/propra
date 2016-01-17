package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

/**
 * This class is an implementation of <code>IPositionFinder</code> that returns Positions
 * from left to right, iterating through the columns from top to bottom. <br>
 * <br>
 * Example (order of Positions that are returned): <br>
 * <br>
 * a <br>
 * b <br>
 * c <br>
 * <br>
 * a d <br>
 * b e <br>
 * c f <br>
 * <br>
 * a d g <br>
 * b e h <br>
 * c f i <br>
 * <br>
 * a d g j <br>
 * b e h k <br>
 * c f i l
 */
public class LeftToRightPosFinder implements IPositionFinder {

	/**
	 * Returns the next free <code>Position</code> in the given <code>Surface</code>, which is the next
     * Position in the array from left to right holding a null value.
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
