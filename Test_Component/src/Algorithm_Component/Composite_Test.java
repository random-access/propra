package Algorithm_Component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ess.data.Composite;
import ess.data.Tile;
import ess.data.TileUtils;

public class Composite_Test {
	
	private int rows1 = 5, cols1 = 10;
	private int rows2 = 2, cols2 = 5;
	private Composite composite1, composite2;

	@Before
	public void buildTestComposite1() {
		ArrayList<Tile> tileSorts = new ArrayList<Tile>();
		tileSorts.add(new Tile("_0", 40, 20));
		tileSorts.add(new Tile("_1", 20, 20));
		tileSorts.add(new Tile("_2", 40, 60));
		tileSorts.add(new Tile("_3", 20, 40));
		tileSorts.add(new Tile("_4", 40, 40));
		tileSorts.add(new Tile("_5", 60, 40));
		
		ArrayList<String> surface = new ArrayList<String>();
		
		composite1 = new Composite(rows1, cols1, surface, tileSorts);
	}
	
	@Before
	public void buildTestComposite2() {
		ArrayList<Tile> tileSorts = new ArrayList<Tile>();
		tileSorts.add(new Tile("_0", 40, 20));
		tileSorts.add(new Tile("_4", 20, 20));
		tileSorts.add(new Tile("_1", 40, 60));
		tileSorts.add(new Tile("_2", 20, 40));
		tileSorts.add(new Tile("_3", 40, 40));
		tileSorts.add(new Tile("_5", 60, 40));
		
		ArrayList<String> surface = new ArrayList<String>();
		
		composite2 = new Composite(rows2, cols2, surface, tileSorts);
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
		assertEquals(c1.getSurface().getRows(), rows1);
		assertEquals(c1.getSurface().getCols(), cols1);
		assertEquals(c2.getSurface().getRows(), rows2);
		assertEquals(c2.getSurface().getCols(), cols2);
	}

	@Test
	public void testTileSortsHeightComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTileSorts(TileUtils.getHeightComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTileSorts().size()-1; i++) {
			Tile currentTile = c.getTileSorts().get(i);
			Tile nextTile = c.getTileSorts().get(i + 1);
			assertTrue(currentTile.getRows() <= nextTile.getRows());
		}
	}

	@Test
	public void testTileSortsWidthComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTileSorts(TileUtils.getWidthComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTileSorts().size() - 1; i++) {
			Tile currentTile = c.getTileSorts().get(i);
			Tile nextTile = c.getTileSorts().get(i + 1);
			assertTrue(currentTile.getCols() <= nextTile.getCols());
		}
	}

	@Test
	public void testTileSortsAreaComparator() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = composite1;

		// Act (on the object or method under test.)
		c.sortTileSorts(TileUtils.getAreaComparator());

		// Assert (that the expected results have occurred.))
		for (int i = 0; i < c.getTileSorts().size() - 1; i++) {
			Tile currentTile = c.getTileSorts().get(i);
			Tile nextTile = c.getTileSorts().get(i + 1);
			assertTrue(currentTile.getNumberOfFields() <= nextTile.getNumberOfFields());
		}
	} 

}
