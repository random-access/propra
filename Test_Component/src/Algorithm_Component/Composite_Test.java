package Algorithm_Component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import ess.data.Composite;
import ess.data.Tile;
import ess.data.utils.SurfaceUtils;
import ess.data.utils.TileSortUtils;

public class Composite_Test {
	
	private int width1 = 10, height1 = 5;
	private int width2 = 5, height2 = 2;
	private Composite composite1, composite2;

	@Before
	public void buildTestComposite1() {
		HashMap<Integer, Tile> tileSorts = new HashMap<Integer, Tile>();
		tileSorts.put(0, new Tile(0, 20, 40));
		tileSorts.put(1, new Tile(1, 20, 20));
		tileSorts.put(2, new Tile(2, 60, 40));
		tileSorts.put(3, new Tile(3, 40, 20));
		tileSorts.put(4, new Tile(4, 40, 40));
		tileSorts.put(5, new Tile(5, 40, 60));
		
		int [][] surface = SurfaceUtils.initSurface(width1, height1);
		
		composite1 = new Composite(surface, tileSorts);
	}
	
	@Before
	public void buildTestComposite2() {
		HashMap<Integer, Tile> tileSorts = new HashMap<Integer, Tile>();
		tileSorts.put(0, new Tile(0, 20, 40));
		tileSorts.put(4, new Tile(4, 20, 20));
		tileSorts.put(1, new Tile(1, 60, 40));
		tileSorts.put(2, new Tile(2, 40, 20));
		tileSorts.put(3, new Tile(3, 40, 40));
		tileSorts.put(5, new Tile(5, 40, 60));
		
		int [][] surface = SurfaceUtils.initSurface(width2, height2);
		
		composite2 = new Composite(surface, tileSorts);
	}
	
	@Test
	public void testSurfaceMeasurements() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c1 = composite1;
		System.out.println(composite1.toString());
		Composite c2 = composite2;
		System.out.println(composite2.toString());
		
		// Act (on the object or method under test.)
		
		// Assert (that the expected results have occurred.))
		assertEquals(c1.getHeight(), height1);
		assertEquals(c1.getWidth(), width1);
		assertEquals(c2.getHeight(), height2);
		assertEquals(c2.getWidth(), width2);
	}

	/* @Test
	public void testTileSortsHeightComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTiles(TileSortUtils.getHeightComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTileSorts().size()-1; i++) {
			Tile currentTile = c.getTileSorts().get(i);
			Tile nextTile = c.getTileSorts().get(i + 1);
			assertTrue(currentTile.getHeight() <= nextTile.getHeight());
		}
	}

	@Test
	public void testTileSortsWidthComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTiles(TileSortUtils.getWidthComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTileSorts().size() - 1; i++) {
			Tile currentTile = c.getTileSorts().get(i);
			Tile nextTile = c.getTileSorts().get(i + 1);
			assertTrue(currentTile.getWidth() <= nextTile.getWidth());
		}
	}

	@Test
	public void testTileSortsAreaComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTiles(TileSortUtils.getAreaComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTileSorts().size() - 1; i++) {
			Tile currentTile = c.getTileSorts().get(i);
			Tile nextTile = c.getTileSorts().get(i + 1);
			assertTrue(currentTile.getArea() <= nextTile.getArea());
		}
		System.out.println();
	} */

}
