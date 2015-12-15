package ess.data;

/**
 * Tiles are rectangular areas filling the surface of a composite.
 * 
 * They are read from an input source (e.g. XML-files), external measurements get 
 * converted into internal measurements during import.<br>
 * <br>
 * The attribute id holds an identification String which can be used for identifying a tile, 
 * because it is unique in a composite.
 *
 * @author Monika Schrenk
 */
public class Tile {
	
	private String id;
	private int rows, cols;

	/**
	 * Instantiates a new tile with its identifier, its internal width and its internal height.
	 *
	 * @param id an identification string unique in a composite.
	 * @param rows an integer greater than zero.
	 * @param cols an integer greater than zero.
	 */
	public Tile(String id, int rows, int cols) {
		this.id = id;
		this.rows = rows;
		this.cols = cols;
		
	}
	
	/**
	 * Gets the id of this tile, an identification string unique in a composite.
	 *
	 * @return the id of this tile.
	 */
	public String getId() {
		return id;
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
	 * @return number of fields this tile has
	 */
	public int getNumberOfFields() {
		return cols * rows;
	}
	

	/**
     * Overridden by tile to be consistent with {@link #equals()}.
     * Two positions have the same Hashcode if their rows and columns are the same.
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cols;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + rows;
		return result;
	}
	
	/**
     * Two tiles t1 and t2 are equal under the following conditions:
     * <ul>
     *     <li>t1 != null && t2 != null AND</li>
     *     <li>t1.getId() == t2.getId() AND</li>
     *     <li>t1.getRows() == t2.getRows() AND</li>
     *     <li>t1.getCols() == t2.getCols().</li>
     * </ul>
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		if (obj == null) {
            return false;
        }
		if (getClass() != obj.getClass()) {
            return false;
        }
		Tile other = (Tile) obj;
		if (cols != other.cols) {
            return false;
        }
		if (id == null) {
			if (other.id != null) {
                return false;
            }
		} else if (!id.equals(other.id)) {
            return false;
        }
		if (rows != other.rows) {
            return false;
        }
		return true;
	}

	/**
     * human-readable, textual representation of this tile,
     * showing the values of its id, row and column.
     */
	@Override
	public String toString() {
		return "Tile [ident=" + id + ", rows=" + rows + ", cols=" + cols
				+ "]";
	}

}
