package ess.data;

// TODO: Auto-generated Javadoc
/**
 * Tiles are rectangular areas filling the surface of a composite.
 * 
 * They are read from an input source (e.g. XML-files) and get 
 * converted into internal measurements via {@link #getRows()} and {@link #getCols()}.<br>
 * <br>
 * The attribute ident holds an identification String which can be used for identifying a tile, because it is 
 * unique in a composite. //TODO override equals method
 *
 * @author Monika Schrenk
 */
public class Tile {
	
	private String ident;
	private int cols, rows;

	/**
	 * Instantiates a new tile.
	 *
	 * @param ident an identification string unique in a composite.
	 * @param cols an integer greater than zero.
	 * @param rows an integer greater than zero.
	 */
	public Tile(String ident, int cols, int rows) {
		this.ident = ident;
		this.cols = cols;
		this.rows = rows;
	}
	
	/**
	 * Gets the ident of this tile, an identification string unique in a composite.
	 *
	 * @return the ident of this tile.
	 */
	public String getIdent() {
		return ident;
	}

	/**
	 * Gets the number of columns of this tile, an integer greater than zero.
	 *
	 * @return the number of columns of this tile.
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Gets the number of rows, an integer greater than zero.
	 *
	 * @return the number of rows of this tile, should be an integer greater than zero.
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Gets the number of fields of this tile (number of fields = rows * columns)
	 * 
	 * Used e.g. for sorting a collection of tiles by their size
	 *
	 * @return number of fields this tile has
	 */
	public int getNumberOfFields() {
		return cols * rows;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tile [ident=" + ident + ", rows=" + rows+ ", cols=" + cols
				+ "]";
	}

}
