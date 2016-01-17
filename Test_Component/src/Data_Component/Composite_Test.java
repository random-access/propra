package Data_Component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ess.data.Composite;
import ess.data.Tile;
import ess.data.TileComparator;

public class Composite_Test {

    private int rows1 = 5, cols1 = 10;
    private int rows2 = 2, cols2 = 5;
    private Composite composite1, composite2;

    private Tile t1;
    private Tile t2;
    private Tile t3;
    private Tile t4;
    private Tile t5;
    private Tile t6;
    private Tile t7;
    private Tile t8;
    private Composite c;

    @Before
    public void buildTestComposite1() {
        ArrayList<Tile> tileSorts = new ArrayList<>();
        tileSorts.add(new Tile("_0", 2, 1));
        tileSorts.add(new Tile("_1", 1, 1));
        tileSorts.add(new Tile("_2", 2, 3));
        tileSorts.add(new Tile("_3", 1, 2));
        tileSorts.add(new Tile("_4", 2, 2));
        tileSorts.add(new Tile("_5", 3, 2));

        ArrayList<String> surface = new ArrayList<>();

        composite1 = new Composite(rows1, cols1, surface, tileSorts);
    }

    @Before
    public void buildTestComposite2() {
        ArrayList<Tile> tileSorts = new ArrayList<>();
        tileSorts.add(new Tile("_0", 2, 1));
        tileSorts.add(new Tile("_4", 1, 1));
        tileSorts.add(new Tile("_1", 2, 3));
        tileSorts.add(new Tile("_2", 1, 2));
        tileSorts.add(new Tile("_3", 2, 2));
        tileSorts.add(new Tile("_5", 3, 2));

        ArrayList<String> surface = new ArrayList<>();

        composite2 = new Composite(rows2, cols2, surface, tileSorts);
    }

    @Before
    public void buildTestComposite3() {
        t1 = new Tile("_5", 1, 1);
        t2 = new Tile("_0", 2, 3);
        t3 = new Tile("_7", 1, 2);
        t4 = new Tile("_3", 2, 2);
        t5 = new Tile("_1", 2, 2);
        t6 = new Tile("_4", 2, 1);
        t7 = new Tile("_9", 3, 1);
        t8 = new Tile("_8", 1, 5);

        ArrayList<Tile> tileSorts = new ArrayList<Tile>();
        tileSorts.add(t1);
        tileSorts.add(t2);
        tileSorts.add(t3);
        tileSorts.add(t4);
        tileSorts.add(t5);
        tileSorts.add(t6);
        tileSorts.add(t7);
        tileSorts.add(t8);

        c = new Composite(0, 0, new ArrayList<String>(), tileSorts);
    }

    @Test
    public void testSurfaceMeasurements() {
        // Arrange (set all necessary preconditions and inputs.)
        Composite c1 = composite1;
        Composite c2 = composite2;

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
        Composite c1 = composite1;

        // Act (on the object or method under test.)
        c1.sortTileSorts(TileComparator.ROWS_ASC);

        // Assert (that the expected results have occurred.))
        for (int i = 0; i < c1.getTileSorts().size() - 1; i++) {
            Tile currentTile = c1.getTileSorts().get(i);
            Tile nextTile = c1.getTileSorts().get(i + 1);
            assertTrue(currentTile.getRows() <= nextTile.getRows());
        }
    }

    @Test
    public void testTileSortsWidthComparator() {
        // Arrange (set all necessary preconditions and inputs.)
        Composite c1 = composite1;

        // Act (on the object or method under test.)
        c1.sortTileSorts(TileComparator.COLS_ASC);

        // Assert (that the expected results have occurred.))
        for (int i = 0; i < c1.getTileSorts().size() - 1; i++) {
            Tile currentTile = c1.getTileSorts().get(i);
            Tile nextTile = c1.getTileSorts().get(i + 1);
            assertTrue(currentTile.getCols() <= nextTile.getCols());
        }
    }

    @Test
    public void testTileSortsAreaComparator() {
        // Arrange (set all necessary preconditions and inputs.)
        Composite c1 = composite1;

        // Act (on the object or method under test.)
        c1.sortTileSorts(TileComparator.FIELDS_ASC);

        // Assert (that the expected results have occurred.))
        for (int i = 0; i < c1.getTileSorts().size() - 1; i++) {
            Tile currentTile = c1.getTileSorts().get(i);
            Tile nextTile = c1.getTileSorts().get(i + 1);
            assertTrue(currentTile.getNumberOfFields() <= nextTile.getNumberOfFields());
        }
    }

    @Test
    public void findExistingTileById() {
        assertEquals("The correct tile must be returned, if searched by id.", t4, c.findTileById("_3"));
        assertSame("Same tile object that was added before must be returned.", t2, c.findTileById("_0"));
    }

    @Test
    public void findNonExistingTileById() {
        assertNull("If no tile with the given id is existing, null should be returned.", c.findTileById("0"));
    }

    @Test
    public void getSingleLargerTile() {
        ArrayList<Tile> result = c.getTilesLargerThan(t4.getRows(), t4.getCols(), t4.getNumberOfFields());

        assertEquals("List of larger tiles must have the correct size.", 1, result.size());
        assertTrue("Resulting list must contain tiles with more columns and same number of rows.", result.contains(t2));
        assertFalse("Resulting list cannot contain tiles with more fields and more columns, but less rows", result.contains(t8));
        assertSame("Resulting list must return the same object that was added before.", t2, result.get(0));
    }

    @Test
    public void getNoLargerTiles() {
        ArrayList<Tile> result = c.getTilesLargerThan(t2.getRows(), t2.getCols(), t2.getNumberOfFields());

        assertNotNull("List of larger tiles cannot be null.", result);
        assertTrue("List of larger tiles must be empty.", result.isEmpty());
    }

    @Test
    public void getLargerTiles() {
        ArrayList<Tile> result = c.getTilesLargerThan(t3.getRows(), t3.getCols(), t3.getNumberOfFields());

        assertEquals("List of larger tiles must have the correct size.", 4, result.size());
        assertFalse("Resulting list cannot contain tiles with more columns, but less rows.", result.contains(t6));
        assertFalse("Resulting list cannot contain tiles with more fields and more rows, but less columns", result.contains(t7));
        assertTrue("Resulting list must contain tiles with more rows, columns and fields.", result.contains(t2));
        assertTrue("Resulting list must contain tiles with more rows and same number of columns.", result.contains(t4));
    }

}
