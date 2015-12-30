package Data_Component;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ess.data.Composite;
import ess.data.Tile;

public class Composite_Test {

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
    public void createTilesAndComposite() {
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
