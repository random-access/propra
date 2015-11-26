package ess.data;

import java.util.Comparator;

//TODO Javadoc
public enum TileComparator implements Comparator<Tile>{
	
	ROWS_ASC {
		@Override
		public int compare(Tile t1, Tile t2) {
			if (t1 == null || t2 == null) {
				return compareWithNulls(t1,t2);
			}
			return Integer.valueOf(t1.getRows()).compareTo(Integer.valueOf(t2.getRows()));
		}	
	},
	ROWS_DESC {

		@Override
		public int compare(Tile t1, Tile t2) {
			if (t1 == null || t2 == null) {
				return compareWithNulls(t1,t2);
			}
			return (-1) * Integer.valueOf(t1.getRows()).compareTo(Integer.valueOf(t2.getRows()));
		}
		
	},
	COLS_ASC {
		@Override
		public int compare(Tile t1, Tile t2) {
			if (t1 == null || t2 == null) {
				return compareWithNulls(t1,t2);
			}
			return Integer.valueOf(t1.getCols()).compareTo(Integer.valueOf(t2.getCols()));
		}
	},
	COLS_DESC {

		@Override
		public int compare(Tile t1, Tile t2) {
			if (t1 == null || t2 == null) {
				return compareWithNulls(t1,t2);
			}
			return (-1) * Integer.valueOf(t1.getCols()).compareTo(Integer.valueOf(t2.getCols()));
		}
		
	},
	FIELDS_ASC {
		@Override
		public int compare(Tile t1, Tile t2) {
			if (t1 == null || t2 == null) {
				return compareWithNulls(t1,t2);
			}
			return Integer.valueOf(t1.getNumberOfFields()).compareTo(Integer.valueOf(t2.getNumberOfFields()));
		}
	}, 
	FIELDS_DESC {

		@Override
		public int compare(Tile t1, Tile t2) {
			if (t1 == null || t2 == null) {
				return compareWithNulls(t1,t2);
			}
			return (-1) * Integer.valueOf(t1.getNumberOfFields()).compareTo(Integer.valueOf(t2.getNumberOfFields()));
		}
		
	},;
	
	private static int compareWithNulls(Tile t1, Tile t2) {
		if (t1 == null && t2 == null) {
			return 0;
		}
		return t1 == null ? 1 : -1;
	}

}
