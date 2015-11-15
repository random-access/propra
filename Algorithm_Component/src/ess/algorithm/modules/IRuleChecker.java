package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;

public interface IRuleChecker {
	
	public boolean checkExplicitRules(Composite c, Tile tile, Position pos);
	
	public boolean checkImplicitRules(Composite c, Tile tile, Position pos);
}
