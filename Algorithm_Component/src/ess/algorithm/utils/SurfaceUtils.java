package ess.algorithm.utils;

import java.awt.Point;

import ess.algorithm.exception.InvalidTilePosException;
import ess.data.Tile;

/**
 * This is a helper class, providing some generally useful operations on
 * surfaces, covering:
 * <ul>
 * <li>Surface initialization</li>
 * <li>Insertion of tiles into the surface</li>
 * <li>Human-readable textual output (useful for debugging)</li>
 * </ul>
 * 
 * This class is meant to operate on the internal data representation, meaning
 * all measurements (tile and surface) are divided by 20, as the composite only
 * deals with objects that have a multiple of 20 as height and width.
 * 
 * @author Monika Schrenk
 *
 */
public class SurfaceUtils {

	// prevents instantiation
	private SurfaceUtils() {}

	/**
	 * Creates an empty surface and initializes it with -1 (assuming there are
	 * no tile IDs below zero)
	 * 
	 * @param width
	 *            number of columns the surface should have
	 * @param height
	 *            number of rows the surface should have
	 * @return
	 */
	public static String[][] initSurface(int width, int height) {
		String[][] surface = new String[height][width];
		for (int i = 0; i < surface.length; i++) {
			for (int j = 0; j < surface[0].length; j++) {
				surface[i][j] = null;
			}
		}
		return surface;
	}

	/**
	 * Inserts a given tile in the given surface at the given position For
	 * example: insertTile called on an empty surface with 3 rows and 4 columns,
	 * for a 3x2 tile with insert position (0,2) will produce the following
	 * result: <br>
	 * _ _ x x x _ <br>
	 * _ _ x x x _ <br>
	 * _ _ _ _ _ _ <br>
	 * 
	 * @param surface
	 *            a 2-dimensional int array, initially filled with -1
	 * @param tile
	 *            the tile that should be inserted
	 * @param position
	 *            the left upper corner of the position the tile should be
	 *            inserted
	 * @throws InvalidTilePosException
	 */
	public static void insertTile(String[][] surface, Tile tile, Point position)
			throws InvalidTilePosException {
		for (int i = position.x; i < position.x + tile.getHeight(); i++) {
			for (int j = position.y; j < position.y + tile.getWidth(); j++) {
				if (i < 0 || j < 0 || i >= surface.length
						|| j >= surface[0].length) {
					throw new InvalidTilePosException(
							"Tile exceeds surface at " + position);
				}
				surface[i][j] = tile.getIdent();
			}
		}
	}

	/**
	 * Returns the next free position in the given surface, that means the next
	 * position from top left to bottom right with value != -1.<br>
	 * <br>
	 * Point p holds the following values:
	 * <ul>
	 * <li>p.x: the row, starting with 0 at the top of the array</li>
	 * <li>p.y: the column, starting with 0 at the left side of the array</li>
	 * </ul>
	 * If there is no free position anymore in the surface, (-1,-1) will be
	 * returned<br>
	 * <br>
	 * <b>Examples:</b>
	 * <ul>
	 * <li>p = (0,2): 1st row from top, 3rd column from left:<br>
	 * _ _ x _ _ _ <br>
	 * _ _ _ _ _ _</li><br>
	 * <li>p = (2,1): 3rd row from top, 2nd column from left<br>
	 * _ _ _ _ _ _ <br>
	 * _ _ _ _ _ _ <br>
	 * _ x _ _ _ _ <br>
	 * _ _ _ _ _ _</li><br>
	 * </ul>
	 * 
	 * @param surface
	 *            a 2-dimensional int array, initially filled with -1
	 * @return a point with the next free position in the given surface or
	 *         (-1,-1) if no free position is available anymore
	 */
	public static Point getNextFreePosition(String[][] surface) {
		for (int i = 0; i < surface.length; i++) {
			for (int j = 0; j < surface[0].length; j++) {
				if (surface[i][j] == null) {
					return new Point(i, j);
				}
			}
		}
		return new Point(-1, -1);
	}

	/**
	 * Prints the surface to standard output
	 * 
	 * @param surface
	 *            a 2-dimensional int array
	 */
	public static void printSurface(String[][] surface) {
		for (int i = 0; i < surface.length; i++) {
			for (int j = 0; j < surface[0].length; j++) {
				System.out.print(surface[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Returns a human-readable, textual representation of the surface
	 * 
	 * @param surface
	 *            a 2-dimensional int array
	 * @return a String with the textual representation of the given surface
	 */
	public static String getSurfaceAsString(String[][] surface) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < surface.length; i++) {
			for (int j = 0; j < surface[0].length; j++) {
				sb.append(surface[i][j]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
