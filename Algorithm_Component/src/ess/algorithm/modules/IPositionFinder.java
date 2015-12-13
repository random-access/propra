package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;

/** 
 * This interface should be implemented by all classes that are responsible for finding the next free 
 * position to place a tile inside a composite.
 * 
 * @author Monika Schrenk
 *
 */
public interface IPositionFinder {
	
    /**
     * Returns the next free position where a tile should be placed 
     * (which means the top right corner position of the tile should be placed there).
     * @param composite The composite holding the surface in which the tile should be placed.
     * @param pos The position where the last tile was placed.
     * @return A free position for placing the next tile.
     */
	public Position findNextFreePosition(Composite composite, Position pos);
	
}
