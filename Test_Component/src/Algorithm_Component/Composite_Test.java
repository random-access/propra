package Algorithm_Component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import ess.algorithm.utils.SurfaceUtils;
import ess.algorithm.utils.TileUtils;
import ess.data.Composite;
import ess.data.Tile;

public class Composite_Test {
	
	private int width1 = 10, height1 = 5;
	private int width2 = 5, height2 = 2;
	private Composite composite1, composite2;

	@Before
	public void buildTestComposite1() {
		ArrayList<Tile> tileSorts = new ArrayList<Tile>();
		tileSorts.add(new Tile("_0", 20, 40));
		tileSorts.add(new Tile("_1", 20, 20));
		tileSorts.add(new Tile("_2", 60, 40));
		tileSorts.add(new Tile("_3", 40, 20));
		tileSorts.add(new Tile("_4", 40, 40));
		tileSorts.add(new Tile("_5", 40, 60));
		
		String[][] surface = SurfaceUtils.initSurface(width1, height1);
		
		composite1 = new Composite(surface, tileSorts);
	}
	
	@Before
	public void buildTestComposite2() {
		ArrayList<Tile> tileSorts = new ArrayList<Tile>();
		tileSorts.add(new Tile("_0", 20, 40));
		tileSorts.add(new Tile("_4", 20, 20));
		tileSorts.add(new Tile("_1", 60, 40));
		tileSorts.add(new Tile("_2", 40, 20));
		tileSorts.add(new Tile("_3", 40, 40));
		tileSorts.add(new Tile("_5", 40, 60));
		
		String[][] surface = SurfaceUtils.initSurface(width2, height2);
		
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

	@Test
	public void testTileSortsHeightComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTiles(TileUtils.getHeightComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTiles().size()-1; i++) {
			Tile currentTile = c.getTiles().get(i);
			Tile nextTile = c.getTiles().get(i + 1);
			assertTrue(currentTile.getHeight() <= nextTile.getHeight());
		}
	}

	@Test
	public void testTileSortsWidthComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTiles(TileUtils.getWidthComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTiles().size() - 1; i++) {
			Tile currentTile = c.getTiles().get(i);
			Tile nextTile = c.getTiles().get(i + 1);
			assertTrue(currentTile.getWidth() <= nextTile.getWidth());
		}
	}

	@Test
	public void testTileSortsAreaComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTiles(TileUtils.getAreaComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTiles().size() - 1; i++) {
			Tile currentTile = c.getTiles().get(i);
			Tile nextTile = c.getTiles().get(i + 1);
			assertTrue(currentTile.getArea() <= nextTile.getArea());
		}
	} 

}
