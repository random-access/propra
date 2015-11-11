package ess.algorithm.modules;

import ess.data.Position;
import ess.data.Tile;

public interface ITileChooser {

	public Tile getNextTile(Position pos, Tile currentTile);

}
