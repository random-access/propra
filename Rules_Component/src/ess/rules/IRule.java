package ess.rules;

import ess.data.Position;
import ess.data.Tile;

public interface IRule {
	
	public boolean checkWholeSurface(String[][] surface);
	
	public boolean checkCurrentMove(String[][] surface, Tile t, Position pos);
	
}
