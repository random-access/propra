package ess.rules;

import ess.data.Position;
import ess.data.Tile;

public class SurfaceTooLargeRule implements IRule{

	@Override
	public boolean checkWholeSurface(String[][] surface) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkCurrentMove(String[][] surface, Tile tile, Position pos) {
		return pos == null;
	}

}