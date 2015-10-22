package ess.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class TileUtils.
 */
public class TileUtils {

	// prevent instantiation
	private TileUtils() {}
	
	/**
	 * Find tile by ident.
	 *
	 * @param tiles the tiles
	 * @param ident the ident
	 * @return the tile
	 */
	public static Tile findTileByIdent(ArrayList<Tile> tiles, String ident) {
		for (Tile t : tiles) {
			if (ident.equals(t.getIdent())) {
				return t;
			}
		}
		return null; // TODO
	}
	
	
	/**
	 * Gets the height comparator.
	 *
	 * @return the height comparator
	 */
	public static Comparator<Tile> getHeightComparator() {
		return new Comparator<Tile>() {

			@Override
			public int compare(Tile t1, Tile t2) {
				if (t1.getRows() < t2.getRows()) {
					return -1;
				} else if (t1.getRows() > t2.getRows()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}

	/**
	 * Gets the width comparator.
	 *
	 * @return the width comparator
	 */
	public static Comparator<Tile> getWidthComparator() {
		return new Comparator<Tile>() {

			@Override
			public int compare(Tile t1, Tile t2) {
				if (t1.getCols() < t2.getCols()) {
					return -1;
				} else if (t1.getCols() > t2.getCols()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}
	
	/**
	 * Gets the area comparator.
	 *
	 * @return the area comparator
	 */
	public static Comparator<Tile> getAreaComparator() {
		return new Comparator<Tile>() {

			@Override
			public int compare(Tile t1, Tile t2) {
				if (t1.getNumberOfFields() < t2.getNumberOfFields()) {
					return -1;
				} else if (t1.getNumberOfFields() > t2.getNumberOfFields()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}
	
	/**
	 * Gets the tile list as string.
	 *
	 * @param tiles the tiles
	 * @return the tile list as string
	 */
	public static String getTileListAsString(Collection<Tile> tiles) {
		StringBuilder sb = new StringBuilder();
		for (Tile t : tiles) {
			sb.append(t).append("\n");
		}
		return sb.toString();
	}

}
