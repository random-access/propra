package ess.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains all data a <code>Solver</code> or a <code>Validator</code> needs for executing the algorithm. 
 * It consists of:
 * <ul>
 * <li>a surface, which gets filled with tiles during solving or validating a composite,</li>
 * <li>an ArrayList of Tiles containing all possible tiles that can be used to build the Composite,</li>
 * <li>an ArrayList of Strings holding the output of a Composite and</li>
 * <li>an int holding the maximum length of straight lines that is allowed in this Composite.</li>
 * </ul> 
 * 
 * @author Monika Schrenk
 * 
 */
public class Composite {
	
	private final ArrayList<Tile> tileSorts;
	private ArrayList<String> surfaceTileList;
	private Surface surface;
	private int maxLineLength;

	/**
	 * Instantiates a new <code>Composite</code>.
	 *
	 * @param rows Number of rows of the surface.
	 * @param cols Number of columns of the surface.
	 * @param surfaceTileList List of Tiles (represented by their id's) that fill the Surface, ordered from top left 
	 * to bottom right. Gets either imported by a <code>Validator</code> or exported by a <code>Solver</code>.
	 * @param tileSorts List of Tiles that can be used for filling the Surface in application-specific measurements.
	 */
	public Composite(int rows, int cols, ArrayList<String> surfaceTileList, ArrayList<Tile> tileSorts) {
		this.tileSorts = tileSorts;
		this.surfaceTileList = surfaceTileList;
		this.surface = new Surface(rows, cols);
	}


	/**
	 * Gets the tile sorts that can be used for filling the Surface.
	 *
	 * @return the tile sorts
	 */
	public ArrayList<Tile> getTileSorts() {
		return tileSorts;
	}
	
	/**
	 * Sorts the tileSorts according to the logic of a given <code>TileComparator</code>.
	 * 
	 * @see TileComparator
	 *
	 * @param comparator a TileComparator which holds the sorting logic.
	 */
	public void sortTileSorts(TileComparator comparator) {
		Collections.sort(tileSorts, comparator);
	}

	/**
	 * Gets list of Tiles (represented by their id's) that fill the Surface, ordered from top left to bottom right.
	 *
	 * @return list of tile-id's
	 */
	public ArrayList<String> getSurfaceTileList() {
		return surfaceTileList;
	}
	
	/**
	 * Sets list of Tiles (represented by their id's) that fill the surface, ordered from top left to bottom right.
	 * 
	 * @param surfaceTileList an ArrayList with tile IDs.
	 */
	public void setSurfaceTileList(ArrayList<String> surfaceTileList) {
		this.surfaceTileList = surfaceTileList;
	}
	
	/**
	 * Gets the <code>Surface</code> of this Composite. During solving or validating a Composite, 
	 * the Surface gets filled with Tiles.
	 *
	 * @return surface of a composite.
	 */
	public Surface getSurface() {
		return surface;
	}
	
	/**
	 * Sets the maximum length of a straight line between 2 tiles that is allowed to
	 * occur in a surface.
	 * The maximum line length is a positive integer.
	 * @param maxLineLength maximum length of a straight line in a surface
	 */
	public void setMaxLineLength(int maxLineLength) {
		this.maxLineLength = maxLineLength;
	}
	
	/**
	 * Sets the maximum length of a straight line between 2 tiles that is allowed to
	 * occur in a surface.
	 * @return maximum length of a straight line in a surface
	 */
	public int getMaxLineLength() {
		return maxLineLength;
	}
	
	/**
	 * Returns the first tile in the list of tile sorts with identifier ID, assuming
	 * that each tile in tileSorts has a different ID. For XML input, this is checked 
	 * during DTD validation.
	 * @param id The identifier of a tile.
	 * @return The first tile in the list of tile sorts with identifier id.
	 */
	public Tile findTileById(String id) {
		for (Tile t : tileSorts) {
			if (id.equals(t.getId())) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Gets a list of tiles being larger than the given measurements.
	 * A tile is larger than the given parameters if it meets the following criteria:
	 * <ul>
	 * <li>Number of rows is greater or equal to rows AND</li>
	 * <li>Number of columns is greater or equal to cols AND</li>
	 * <li>Number of fields is greater or equal to numberOfFields.</li>
	 * </ul>
	 * @param rows The number of rows.
	 * @param cols The number of columns.
	 * @param numberOfFields The number of fields.
	 * @return A list of tiles holding all tiles from tileSorts in this composite that are larger than the given measurements.
	 */
	public ArrayList<Tile> getTilesLargerThan(int rows, int cols, int numberOfFields) {
		ArrayList<Tile> filteredTiles = new ArrayList<>();
		for (Tile t : tileSorts) {
			if (t.getNumberOfFields() > numberOfFields && t.getRows() >= rows && t.getCols() >= cols) {
				filteredTiles.add(t);
			}
		}
		return filteredTiles;
	}

	/**
	 * A human readable, textual representation of a Composite, describing all attributes.
	 *
	 * @return A string holding the textual representation of all data from this composite.
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
