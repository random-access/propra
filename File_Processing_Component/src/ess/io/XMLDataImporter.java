package ess.io;

import java.awt.Point;
import java.util.ArrayList;

import ess.algorithm.exception.InvalidTilePosException;
import ess.algorithm.utils.SurfaceUtils;
import ess.algorithm.utils.TileUtils;
import ess.data.Composite;
import ess.data.Tile;
import ess.io.exc.InvalidTileSizeException;

public class XMLDataImporter {
	
	private static final int CONVERSION_UNIT = 20;

	public Composite importComposite(int width, int height, Tile[] tileSorts, String[] surfaceTiles)
			throws InvalidTileSizeException, InvalidTilePosException {
		width = getInternalSize(width);
		height = getInternalSize(height);
		ArrayList<Tile> tiles = convertTiles(tileSorts);
		String[][] surface = convertSurface(width, height, surfaceTiles, tiles);
		return new Composite(surface, tiles);
	}

	private String[][] convertSurface(int width, int height, String[] surfaceTiles, ArrayList<Tile> tiles)
			throws InvalidTilePosException {
		String[][] surface = SurfaceUtils.initSurface(width, height);
		for (int i = 0; i < surfaceTiles.length; i++) {
			Point pos = SurfaceUtils.getNextFreePosition(surface);
			Tile tile = TileUtils.findTileByIdent(tiles, surfaceTiles[i]);
			SurfaceUtils.insertTile(surface, tile, pos);
		}
		return surface;
	}

	private ArrayList<Tile> convertTiles(Tile[] tileSorts) throws InvalidTileSizeException {
		ArrayList<Tile> tiles = new ArrayList<>();
		for (Tile tile : tileSorts) {
			tile.setWidth(getInternalSize(tile.getWidth()));
			tile.setHeight(getInternalSize(tile.getHeight()));
			tiles.add(tile);
		}
		return tiles;
	}

	private int getInternalSize(int externalSize) throws InvalidTileSizeException {
		if (externalSize <= 0 || externalSize % CONVERSION_UNIT != 0) {
			throw new InvalidTileSizeException();
		}
		return externalSize / CONVERSION_UNIT;
	}

}
