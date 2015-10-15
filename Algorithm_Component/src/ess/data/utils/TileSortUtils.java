package ess.data.utils;

import java.util.Comparator;

import ess.data.Tile;

public class TileSortUtils {

	// prevent instantiation
	private TileSortUtils() {}
	
	
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

}
