package ess.algorithm.modules;

import java.util.ArrayList;
import java.util.Collections;

import ess.data.Composite;
import ess.data.Tile;
import ess.data.TileComparator;
import ess.exc.PropertyException;
import ess.utils.ProPraProperties;

/**
 * This class is an implementation of ITileChooser that chooses the next tile
 * sorted by the order set in config.properties.
 * 
 * @author Monika Schrenk
 */
public class SortedTileChooser implements ITileChooser {

	private ArrayList<Tile> tileSorts;

	/**
	 * Instantiates a new sorted tile chooser.
	 *
	 * @param composite The composite that stores the surface where the next tile should be inserted.
	 * @throws PropertyException if config.properties cannot be read or if it stores invalid values.
	 */
	public SortedTileChooser(Composite composite) throws PropertyException {
		tileSorts = new ArrayList<Tile>(composite.getTileSorts());
		applySortingStrategy();
	}

	/* (non-Javadoc)
	 * @see ess.algorithm.modules.ITileChooser#getNextTile(ess.data.Position, ess.data.Tile)
	 */
	@Override
	public Tile getNextTile(Tile tile) {
		if (!tileSorts.isEmpty()) {
			if (tile == null) {
				return tileSorts.get(0);
			}
			int nextIndex = tileSorts.indexOf(tile) + 1;
			if (nextIndex < tileSorts.size()) {
				return tileSorts.get(nextIndex);
			}
		}
		return null;
	}
	
	// Loads the sorting strategy from config.properties and sorts the tiles
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
