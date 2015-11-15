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
	
	private final ArrayList<Tile> tileSorts;
	private final ArrayList<String> surfaceTileList;
	private Surface surface;
	private int maxLineLength;

	/**
	 * Instantiates a new composite.
	 *
	 * @param rows the rows
	 * @param cols the cols
	 * @param surfaceTileList the surface
	 * @param tileSorts the tiles
	 */
	public Composite(int rows, int cols, ArrayList<String> surfaceTileList, ArrayList<Tile> tileSorts) {
		this.tileSorts = tileSorts;
		this.surfaceTileList = surfaceTileList;
		this.surface = new Surface(rows, cols);
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
	 * Gets the surface tile list
	 *
	 * @return the surface
	 */
	public ArrayList<String> getSurfaceTileList() {
		return surfaceTileList;
	}
	
	/**
	 * Gets the surface.
	 *
	 * @return the surface
	 */
	public Surface getSurface() {
		return surface;
	}
	
	public void setMaxLineLength(int maxLineLength) {
		this.maxLineLength = maxLineLength;
	}
	
	public int getMaxLineLength() {
		return maxLineLength;
	}
	
	public Tile findTileById(String id) {
		for (Tile t : tileSorts) {
			if (id.equals(t.getId())) {
				return t;
			}
		}
		return null;
	}
	
	public ArrayList<Tile> getTilesLargerThan(int rows, int cols, int numberOfFields) {
		ArrayList<Tile> filteredTiles = new ArrayList<>();
		for (Tile t : tileSorts) {
			if (t.getRows() >= rows && t.getCols() >= cols && t.getNumberOfFields() > numberOfFields) {
				filteredTiles.add(t);
			}
		}
		Collections.sort(filteredTiles, TileComparator.FIELDS_DESC);
		return filteredTiles;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Composite:\n"
				+ "Max. line length: " + maxLineLength + "\n"
				+ "Surface: " + surface
				+ "TileSorts:\n" + getTileListAsString()
				+ "SurfaceTileList:\n " + surfaceTileList.toString() + "\n";
	}
	
	/**
	 * Gets the tile list as string.
	 *
	 * @param tiles the tiles
	 * @return the tile list as string
	 */
	private String getTileListAsString() {
		StringBuilder sb = new StringBuilder();
		for (Tile t : tileSorts) {
			sb.append(t).append("\n");
		}
		return sb.toString();
	}

}
