package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Tile;

public interface ITileChooser {
	
	public Tile chooseNextTile(Composite composite);
	
}
