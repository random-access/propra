package ess.algorithm.modules;

import java.util.ArrayList;
import java.util.Collections;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.data.TileComparator;
import ess.exc.PropertyException;
import ess.utils.ProPraProperties;

public class SortedTileChooser implements ITileChooser {

	private ArrayList<Tile> tileSorts;

	public SortedTileChooser(Composite composite) throws PropertyException {
		tileSorts = new ArrayList<Tile>(composite.getTileSorts());
		applySortingStrategy();
	}

	@Override
	public Tile getNextTile(Position pos, Tile tile) {
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
