package ess.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ess.algorithm.utils.SurfaceUtils;
import ess.algorithm.utils.TileUtils;

public class Composite {

	private final ArrayList<Tile> tiles;
	private final String[][] surface;

	public Composite(String[][] surface, ArrayList<Tile> tiles) {
		this.tiles = tiles;
		this.surface = surface;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public void sortTiles(Comparator<Tile> comparator) {
		Collections.sort(tiles, comparator);
	}

	public String[][] getSurface() {
		return surface;
	}

	public int getHeight() {
		return surface.length;
	}

	public int getWidth() {
		return surface[0].length;
	}

	@Override
	public String toString() {
		return "Composite\n"
				+ "TileSorts: " 
				+ "\n" + TileUtils.getTileListAsString(tiles)
				+ "Surface: "
				+ "\n" + SurfaceUtils.getSurfaceAsString(this.surface);
	}

}
