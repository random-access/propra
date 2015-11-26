package ess.rules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;

public interface IRule {
	
	public ErrorType getErrorType();
	
	public boolean check(Composite c, Tile t, Position pos);
	
}
