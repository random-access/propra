package ess.algorithm.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import ess.data.Tile;

public class TileUtils {

	// prevent instantiation
	private TileUtils() {}
	
	public static Tile findTileByIdent(ArrayList<Tile> tiles, String ident) {
		for (Tile t : tiles) {
			if (ident.equals(t.getIdent())) {
				return t;
			}
		}
		return null; // TODO
	}
	
	
	public static Comparator<Tile> getHeightComparator() {
		return new Comparator<Tile>() {

			@Override
			public int compare(Tile t1, Tile t2) {
				if (t1.getHeight() < t2.getHeight()) {
					return -1;
				} else if (t1.getHeight() > t2.getHeight()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}

	public static Comparator<Tile> getWidthComparator() {
		return new Comparator<Tile>() {

			@Override
			public int compare(Tile t1, Tile t2) {
				if (t1.getWidth() < t2.getWidth()) {
					return -1;
				} else if (t1.getWidth() > t2.getWidth()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}
	
	public static Comparator<Tile> getAreaComparator() {
		return new Comparator<Tile>() {

			@Override
			public int compare(Tile t1, Tile t2) {
				if (t1.getArea() < t2.getArea()) {
					return -1;
				} else if (t1.getArea() > t2.getArea()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}
	
	public static String getTileListAsString(Collection<Tile> tiles) {
		StringBuilder sb = new StringBuilder();
		for (Tile t : tiles) {
			sb.append(t).append("\n");
		}
		return sb.toString();
	}

}
