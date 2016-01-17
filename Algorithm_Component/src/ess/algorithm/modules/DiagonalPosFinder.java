package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

/**
 * This class is an implementation of <code>IPositionFinder</code> that returns the next
 * <code>Position</code> from top left to bottom right, changing between iterating through 
 * rows and iterating through columns. <br>
 * <br>
 * Example (order of positions that are returned): <br>
 * <br>
 * a b c d<br>
 * <br>
 * a b c d<br>
 * d <br>
 * e <br>
 * <br>
 * a b c d<br>
 * d f g h <br>
 * e <br>
 * <br>
 * a b c d<br>
 * d f g h <br>
 * e i <br>
 * <br>
 * a b c d<br>
 * d f g h <br>
 * e i j k
 */
public class DiagonalPosFinder implements IPositionFinder {

    /**
     * Returns the next free <code>Position</code> in the given <code>Surface</code>, which is the next
     * Position in the array from the top left row to the bottom right column holding a null value.
     * 
     * @see ess.algorithm.modules.IPositionFinder#findNextFreePosition(ess.data.Composite, ess.data.Position)
     */
    @Override
    public Position findNextFreePosition(Composite composite, Position pos) {
        int min = (pos == null) ? 0 : Math.min(pos.getRow(), pos.getCol());
        Surface s = composite.getSurface();
        for (int i = min; i < Math.min(s.getRows(), s.getCols()); i++) {
            for (int j = i; j < s.getCols(); j++) {
                if (s.getEntryAt(i, j) == null) {
                    return new Position(i, j);
                }
            }
            for (int k = i; k < s.getRows(); k++) {
                if (s.getEntryAt(k, i) == null) {
                    return new Position(k, i);
                }
            }
        }
        return null;
    }
}
