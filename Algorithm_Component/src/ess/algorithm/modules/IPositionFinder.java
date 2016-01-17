package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;

/** 
 * This interface should be implemented by all classes that are responsible for finding the next free 
 * position to place a <code>Tile</code> inside a <code>Composite</code>.
 * 
 * @author Monika Schrenk
 *
 */
public interface IPositionFinder {
	
    /**
     * Returns the next free <code>Position</code> where a <code>Tile</code> should be placed 
     * (which means the top right Corner of the Tile that should be placed there).
     * @param composite a Composite holding the Surface in which a Tile should be placed.
     * @param pos Position where the last Tile was placed.
     * @return A free Position for placing the next Tile.
     */
	Position findNextFreePosition(Composite composite, Position pos);
}
