package ess.data;

import java.util.HashMap;

import ess.data.utils.SurfaceUtils;

public class Composite {

	private final HashMap<Integer, Tile> tileSorts; //TODO is this the best data structure??
	private final int[][] surface;

	public Composite(int [][] surface, HashMap<Integer, Tile> tileSorts) {
		this.tileSorts = tileSorts;
		this.surface = surface;
	}

	public HashMap<Integer, Tile> getTileSorts() {
		return tileSorts;
	}

	public int[][] getSurface() {
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
		return "Composite "
				+ "\nTileSorts: " + tileSorts
				+ "\nSurface: "
				+ "\n" + SurfaceUtils.getSurfaceAsString(this.surface);
	}

}
