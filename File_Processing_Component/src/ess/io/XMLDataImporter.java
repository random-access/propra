package ess.io;

import java.awt.Point;
import java.util.SortedMap;

import ess.data.Composite;
import ess.data.Tile;
import ess.data.utils.SurfaceUtils;
import ess.io.exc.InvalidTileSizeException;

public class XMLDataImporter {
	
	private static final int CONVERSION_UNIT = 20;
	
	private int width;
	private int height;
	private SortedMap<Integer,Tile> tileSorts;
	private int[] surfaceTiles;
	private int[][] surface;

	public XMLDataImporter(int width, int height, SortedMap<Integer,Tile> tileSorts, int[] surfaceTiles) {
		this.width = width;
		this.height = height;
		this.tileSorts = tileSorts;
		this.surfaceTiles = surfaceTiles; 
	}

	public Composite importComposite() throws InvalidTileSizeException {
		convertSurfaceSize();
		convertTiles();
		convertSurface();
		
		return null;
	}
	
	private void convertSurface() {
		surface = SurfaceUtils.initSurface(width, height);
		for (int i = 0; i < surfaceTiles.length; i++) {
			Point pos = SurfaceUtils.getNextFreePosition(surface);
			Tile tile = tileSorts.get(surfaceTiles[i]);
			SurfaceUtils.insertTile(surface, tile, pos);
		}
	}

	private void convertTiles() throws InvalidTileSizeException {
		for (Tile t : tileSorts.values()) {
			t.setWidth(getInternalSize(t.getWidth()));
			t.setHeight(getInternalSize(t.getHeight()));
		}
	}
	
	private void convertSurfaceSize() throws InvalidTileSizeException {
		width = getInternalSize(width);
		height = getInternalSize(height);
	}
	
	private int getInternalSize(int externalSize) throws InvalidTileSizeException {
		if (externalSize <= 0 || externalSize % CONVERSION_UNIT != 0) {
			throw new InvalidTileSizeException();
		}
		return externalSize / CONVERSION_UNIT;
	}

}
