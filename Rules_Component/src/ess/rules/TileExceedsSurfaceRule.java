package ess.rules;

import ess.data.Position;
import ess.data.Tile;

public class TileExceedsSurfaceRule implements IRule {

	@Override
	public boolean checkWholeSurface(String[][] surface) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkCurrentMove(String[][] surface, Tile tile, Position pos) {
		for (int i = pos.getRow(); i < pos.getRow() + tile.getRows(); i++) {
			for (int j = pos.getColumn(); j < pos.getColumn() + tile.getCols(); j++) {
				if (i < 0 || j < 0 || i >= surface.length || j >= surface[0].length) {
					return false;
				}
			}
		}
		return true;
	}
}
