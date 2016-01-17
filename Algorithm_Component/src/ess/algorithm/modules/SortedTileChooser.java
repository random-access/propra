package ess.algorithm.modules;

import java.util.ArrayList;
import java.util.Collections;

import ess.data.Composite;
import ess.data.Tile;
import ess.data.TileComparator;
import ess.exc.PropertyException;
import ess.utils.ProPraProperties;

/**
 * This class is an implementation of <code>ITileChooser</code> that chooses the next Tile
 * sorted by the order set in config.properties.
 * 
 * @author Monika Schrenk
 */
public class SortedTileChooser implements ITileChooser {

	private ArrayList<Tile> tileSorts;

	/**
	 * Instantiates a new <code>SortedTileChooser</code>.
	 *
	 * @param composite The Composite that stores the Surface where the next Tile should be inserted.
	 * @throws PropertyException if config.properties cannot be read or if it stores invalid values.
	 */
	public SortedTileChooser(Composite composite) throws PropertyException {
		tileSorts = new ArrayList<>(composite.getTileSorts());
		applySortingStrategy();
	}

	/**    
	 * Choose the next Tile to place using an order defined in config.properties. 
	 * @see ITileChooser#getNextTile(ess.data.Position, ess.data.Tile)
	 */
	@Override
	public Tile getNextTile(Tile lastTile) {
		if (!tileSorts.isEmpty()) {
			if (lastTile == null) {
				return tileSorts.get(0);
			}
			int nextIndex = tileSorts.indexOf(lastTile) + 1;
			if (nextIndex < tileSorts.size()) {
				return tileSorts.get(nextIndex);
			}
		}
		return null;
	}
	
	// Loads the sorting strategy (or strategies) from config.properties and sorts the tiles
	private void applySortingStrategy() throws PropertyException {
		ProPraProperties properties = ProPraProperties.getInstance();
		String sortings = properties.getValue(ProPraProperties.KEY_TILE_CHOOSER_STRATEGY);
		if (sortings.length() == 0) {
            return;
        }
		String[] sortingValues = sortings.split(",");
		for (String v : sortingValues) {
			TileComparator c = TileComparator.valueOf(v.toUpperCase().trim());
			Collections.sort(tileSorts, c);
		}
	}
}
