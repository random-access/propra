package ess.rules;

import ess.data.Position;
import ess.data.Tile;

public class NoCrossingsRule implements IRule {

	@Override
	public boolean checkWholeSurface(String[][] surface) {
		for (int i = 0; i < surface.length-1; i++) {
			for (int j = 0; j < surface[0].length-1; j++) {
				// TODO
			}
		}
		return true;
	}

	@Override
	public boolean checkCurrentMove(String[][] surface, Tile t, Position pos) {
		return false;
	}

}
