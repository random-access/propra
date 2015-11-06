package ess.algorithm.modules;

import java.util.ArrayList;
import java.util.Collections;

import ess.data.Composite;
import ess.data.Tile;
import ess.data.TileComparator;

public class LargeToSmallTileChooser implements ITileChooser {

	private ArrayList<Tile> tileSorts;

	public LargeToSmallTileChooser(Composite composite) {
		tileSorts = new ArrayList<Tile>(composite.getTileSorts());
		Collections.sort(tileSorts, TileComparator.FIELDS_DESC);
	}

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
}
