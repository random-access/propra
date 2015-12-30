package ess.data;

import java.util.Comparator;

/**
 * This utility enum provides a set of useful comparators for sorting tile lists
 * by different criteria.
 * 
 * @author Monika Schrenk
 *
 */
public enum TileComparator implements Comparator<Tile> {
    
    // TODO JUnit Tests

    /**
     * Sorts two tiles by their row number, in ascending order.
     */
    ROWS_ASC {
        @Override
        public int compare(Tile t1, Tile t2) {
            if (t1 == null || t2 == null) {
                return compareWithNulls(t1, t2);
            }
            return Integer.valueOf(t1.getRows()).compareTo(t2.getRows());
        }
    },

    /**
     * Sorts two tiles by their row number, in descending order.
     */
    ROWS_DESC {

        @Override
        public int compare(Tile t1, Tile t2) {
            if (t1 == null || t2 == null) {
                return compareWithNulls(t1, t2);
            }
            return (-1) * Integer.valueOf(t1.getRows()).compareTo(t2.getRows());
        }

    },

    /**
     * Sorts two tiles by their column number, in ascending order.
     */
    COLS_ASC {
        @Override
        public int compare(Tile t1, Tile t2) {
            if (t1 == null || t2 == null) {
                return compareWithNulls(t1, t2);
            }
            return Integer.valueOf(t1.getCols()).compareTo(t2.getCols());
        }
    },

    /**
     * Sorts two tiles by their column number, in descending order.
     */
    COLS_DESC {

        @Override
        public int compare(Tile t1, Tile t2) {
            if (t1 == null || t2 == null) {
                return compareWithNulls(t1, t2);
            }
            return (-1) * Integer.valueOf(t1.getCols()).compareTo(t2.getCols());
        }

    },

    /**
     * Sorts two tiles by their number of fields (rows x cols), in ascending
     * order.
     */
    FIELDS_ASC {
        @Override
        public int compare(Tile t1, Tile t2) {
            if (t1 == null || t2 == null) {
                return compareWithNulls(t1, t2);
            }
            return Integer.valueOf(t1.getNumberOfFields()).compareTo(t2.getNumberOfFields());
        }
    },

    /**
     * Sorts two tiles by their number of fields (rows x cols), in descending
     * order.
     */
    FIELDS_DESC {

        @Override
        public int compare(Tile t1, Tile t2) {
            if (t1 == null || t2 == null) {
                return compareWithNulls(t1, t2);
            }
            return (-1) * Integer.valueOf(t1.getNumberOfFields()).compareTo(t2.getNumberOfFields());
        }

    };

    // handles tiles that are null
    private static int compareWithNulls(Tile t1, Tile t2) {
        if (t1 == null && t2 == null) {
            return 0;
        }
        return t1 == null ? 1 : -1;
    }

}
