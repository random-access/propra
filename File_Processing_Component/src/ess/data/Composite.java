package ess.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * A composite contains an area to be filled with tiles according to the given rules 
 * or to be validated against those rules. This area is realised as a 2-dimensional String array
 * holding the ident attribute of a tile. Initially, the array is filled with null values.<br>
 * <br>
 * The tiles to be used are stored in an ArrayList, this list can be re-sorted using the different Comparators 
 * in TileUtils as input for {@link ess.data.Composite#sortTileSorts(Comparator) sortTiles()}<br>
 * <br>
 * Given an ident value, the corresponding tile can be found using 
 * {@link ess.algorithm.utils.TileUtils#findTileByIdent(ArrayList, String) TileUtils.findTileByIdent()}.
 */
public class Composite {
	
	private int rows, cols;
	private final ArrayList<Tile> tileSorts;
	private final ArrayList<String> surfaceTileList;

	/**
	 * Instantiates a new composite.
	 *
	 * @param rows the rows
	 * @param cols the cols
	 * @param surfaceTileList the surface
	 * @param tileSorts the tiles
	 */
	public Composite(int rows, int cols, ArrayList<String> surfaceTileList, ArrayList<Tile> tileSorts) {
		this.rows = rows;
		this.cols = cols;
		this.tileSorts = tileSorts;
		this.surfaceTileList = surfaceTileList;
	}
	
	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}


	/**
	 * Gets the cols.
	 *
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}



	/**
	 * Gets the tiles.
	 *
	 * @return the tiles
	 */
	public ArrayList<Tile> getTileSorts() {
		return tileSorts;
	}
	
	/**
	 * Sort tiles.
	 *
	 * @param comparator the comparator
	 */
	public void sortTileSorts(Comparator<Tile> comparator) {
		Collections.sort(tileSorts, comparator);
	}

	/**
	 * Gets the surface.
	 *
	 * @return the surface
	 */
	public ArrayList<String> getSurfaceTileList() {
		return surfaceTileList;
	}
	

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Composite:\n"
				+ "TileSorts:\n" + TileUtils.getTileListAsString(tileSorts)
				+ "SurfaceTileList:\n " + surfaceTileList.toString() + "\n"; // TODO
	}

}