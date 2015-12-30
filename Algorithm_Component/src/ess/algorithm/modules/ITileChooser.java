package ess.algorithm.modules;

import ess.data.Tile;

/**
 * This interface should be implemented by all classes that are responsible 
 * for choosing the next tile to be placed in the surface.
 * 
 * @author Monika Schrenk
 */
public interface ITileChooser {

    /**
     * Returns the next tile that should be placed into the surface.
     * @param currentTile The last tile that was placed at pos or null if 
     * no tile was placed there yet.
     * @return The next tile to be place into the surface , or null
     * if ITileChooser has no suggestions anymore.
     */
	Tile getNextTile(Tile currentTile);
	
	// TODO work with tile indices, store a list of tile <-> index in Solver ?

}
