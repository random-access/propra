package ess.algorithm.components;

import ess.data.Composite;
import ess.data.Tile;

public interface ITileChooser {
	
	public Tile chooseNextTile(String[][] surface, Composite composite);
	
}
