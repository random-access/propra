package ess.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A composite contains all data a solver or a validator need for executing its algorithm. It consists of 
 * <ul>
 * <li>A surface, a 2-dimensional array of tiles which gets filled with tiles during solving or validation a composite</li>
 * <li>An arraylist of tiles containing all possible tiles that can be used to build the composite</li>
 * <li>An arraylist of Strings holding the output of a composite </li>
 * <li>An integer holding the maximum length of straight lines that is allowed in this composite</li>
 * </ul> 
 * @author Monika Schrenk
 */
public class Composite {
	
	private final ArrayList<Tile> tileSorts;
	private ArrayList<String> surfaceTileList;
	private Surface surface;
	private int maxLineLength;

	// TODO check if 2 tilesort w same ID exist
	// TODO check if 2 tilesorts w same size exist
	// TODO check if ID of tiles in composite (ValidationInstance) exists in tilesort section
	/**
	 * Instantiates a new composite.
	 *
	 * @param rows Number of rows for surface.
	 * @param cols Number of columns for surface.
	 * @param surfaceTileList Tiles that fill the surface from top left to bottom right.
	 * @param tileSorts List of tiles that can be used for filling the surface, this value gets converted into internal 
	 * measurements during import.
	 */
	public Composite(int rows, int cols, ArrayList<String> surfaceTileList, ArrayList<Tile> tileSorts) {
		this.tileSorts = tileSorts;
		this.surfaceTileList = surfaceTileList;
		this.surface = new Surface(rows, cols);
	}


	/**
	 * Get the tile sorts that can be used for filling the surface.
	 *
	 * @return the tile sorts
	 */
	public ArrayList<Tile> getTileSorts() {
		return tileSorts;
	}
	
	/**
	 * Sorts the tile sorts according to the logic of a given tile comparator.
	 * 
	 * @see TileComparator
	 *
	 * @param comparator a tile comparator
	 */
	public void sortTileSorts(TileComparator comparator) {
		Collections.sort(tileSorts, comparator);
	}

	/**
	 * Get a list of the IDs of tiles being placed inside the surface, ordered
	 * from top left to bottom right.
	 *
	 * @return list of tile-IDs
	 */
	public ArrayList<String> getSurfaceTileList() {
		return surfaceTileList;
	}
	
	public void setSurfaceTileList(ArrayList<String> surfaceTileList) {
		this.surfaceTileList = surfaceTileList;
	}
	
	/**
	 * Get the surface of a composite. During solving or validating a composite, 
	 * the surface gets filled with tiles.
	 *
	 * @return surface of a composite
	 */
	public Surface getSurface() {
		return surface;
	}
	
	/**
	 * Set the maximum length of a straight line between 2 tiles that is allowed to
	 * occur in a surface.
	 * The maximum line length is a positive integer.
	 * @param maxLineLength maximum length of a straight line in a surface
	 */
	public void setMaxLineLength(int maxLineLength) {
		// TODO make sure that maxLineLength is a positive integer
		this.maxLineLength = maxLineLength;
	}
	
	/**
	 * Set the maximum length of a straight line between 2 tiles that is allowed to
	 * occur in a surface.
	 * @return maximum length of a straight line in a surface
	 */
	public int getMaxLineLength() {
		return maxLineLength;
	}
	
	/**
	 * Returns the first tile in the list of tile sorts with identificator id. 
	 * The list of tile sorts must therefore only hold tiles with different id's, this should
	 * be tested before filling the list of tile sorts.
	 * @param id The identificator of a tile.
	 * @return The first tile in the list of tile sorts with identificator id.
	 */
	public Tile findTileById(String id) {
		// TODO make sure each tile in tileSorts has a different ID
		for (Tile t : tileSorts) {
			if (id.equals(t.getId())) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Get a list of tiles being larger than the given measurements.
	 * A tile is larger than the given parameters if it meets the following criteria:
	 * <ul>
	 * <li>Number of rows is greater or equal to rows AND</li>
	 * <li>Number of columns is greater or equal to cols AND</li>
	 * <li>Number of fields is greater or equal to numberOfFields</li>
	 * </ul>
	 * @param rows The number of rows.
	 * @param cols The number of columns.
	 * @param numberOfFields The number of fields.
	 * @return A list of tiles holding all tiles from tileSorts in this composite that are larger than the given measurements.
	 */
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
	 * A human readable, textual representation of a composite, containing all attributes.
	 *
	 * @return A string holding the data of a composite.
	 */
	@Override
	public String toString() {
		return "Composite:\n"
				+ "Max. line length: " + maxLineLength + "\n"
				+ "Surface: " + surface
				+ "TileSorts:\n" + getTileListAsString()
				+ "SurfaceTileList:\n " + surfaceTileList.toString() + "\n";
	}
	
	// Format the list of tile sorts to a String
	private String getTileListAsString() {
		StringBuilder sb = new StringBuilder();
		for (Tile t : tileSorts) {
			sb.append(t).append("\n");
		}
		return sb.toString();
	}

}
