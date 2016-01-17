package ess.algorithm.modules;

import ess.data.Tile;

/**
 * This interface should be implemented by all classes that are responsible 
 * for choosing the next <code>Tile</code> to be placed in a given <code>Surface</code>.
 * 
 * @author Monika Schrenk
 */
public interface ITileChooser {

    /**
     * Returns the next <code>Tile</code> that should be placed into a given <code>Surface</code>.
     * @param currentTile The last Tile that was placed at pos or null if 
     * no Tile was placed there yet.
     * @return The next Tile to be place into the Surface , or null
     * if ITileChooser has no suggestions anymore.
     */
	Tile getNextTile(Tile currentTile);
}
