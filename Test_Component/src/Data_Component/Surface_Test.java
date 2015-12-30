package Data_Component;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;

public class Surface_Test {
    
    private Surface surface;
    private Tile t1;
    private Tile t2;
    private Tile t3;
    private Position p1;
    private Position p2a;
    private Position p2b;
    private Position p3;
    private Position p4;
    private Position p5;
    private Position p6;
    private Position posWithoutEntry;
    private Position posOutsideSurface;
    
    @Before
    public void createSurfaceAndContent() {
        surface = new Surface(4, 5);
        t1 = new Tile ("_1", 1, 1);
        t2 = new Tile ("_2", 2, 1);
        t3 = new Tile ("_3", 1, 2);
        p1 = new Position (0,0);
        p2a = new Position (0,1);
        p2b = new Position (1,1);
        p3 = new Position (0,2);
        p4 = new Position (2,0);
        p5 = new Position (3,0);
        p6 = new Position (3,4);
        posWithoutEntry = new Position (0,4);
        posOutsideSurface = new Position (10,10);
        
        surface.insertEntry(t1, p1);
        surface.insertEntry(t2, p2a);
        surface.insertEntry(t3, p3);
    }

    @Test
    public void testGetEntryAt() {
        assertEquals("Wrong entry or null was returned.", surface.getEntryAt(p1), t1);
        assertEquals("Wrong entry or null was returned.", surface.getEntryAt(p2a), t2);
        assertEquals("Wrong entry or null was returned.", surface.getEntryAt(p3), t3);
        assertEquals("Wrong entry or null was returned.", surface.getEntryAt(p2b), t2);
        assertNull("For empty positions, null must be returned.", surface.getEntryAt(posWithoutEntry));
        assertNull("For positions outside the surface, null must be returned.", surface.getEntryAt(posOutsideSurface));
    }
    
    @Test
    public void testInsertingTiles() {
        surface.insertEntry(t1, posWithoutEntry);
        assertNotNull("Entry was not inserted correctly.", surface.getEntryAt(posWithoutEntry));
    }
    
    @Test
    public void testRemovingTiles() {
        surface.removeEntry(t2, p2a);
        assertNull("Entry was not removed correctly", surface.getEntryAt(p2a));
        assertNull("Entry was not removed correctly", surface.getEntryAt(p2b));
    }
   
    @Test
    public void testIsBorderPosition() {
        assertTrue("Corner positions must be border positions.", surface.isBorderPosition(p1));
        assertTrue("Positions in border rows must be border positions.", surface.isBorderPosition(p2a));
        assertFalse("Positions in the middle of the surfaces are not border positions.", surface.isBorderPosition(p2b));
        assertTrue("Positions in border columns must be border positions.", surface.isBorderPosition(p4));
    }
    
    @Test
    public void testIsBorderRow() {
        assertTrue("Corner positions must be border rows.", surface.isBorderRow(p1));
        assertTrue("Positions in top row must be border rows.", surface.isBorderRow(p2a));
        assertTrue("Positions in bottom row must be border rows.", surface.isBorderRow(p5));
        assertFalse("Positions in the middle of the surfaces are not border rows.", surface.isBorderRow(p2b));
        assertFalse("Positions in border columns are no border rows.", surface.isBorderRow(p4));
    }
    
    @Test
    public void testIsBorderCol() {
        assertTrue("Corner positions must be border columns.", surface.isBorderCol(p1));
        assertFalse("Positions in border rows are no border columns.", surface.isBorderCol(p2a));
        assertFalse("Positions in the middle of the surfaces are not border columns.", surface.isBorderCol(p2b));
        assertTrue("Positions in left column must be border columns.", surface.isBorderCol(p4));
        assertTrue("Positions in right column must be border columns.", surface.isBorderCol(p6));
    }
    
    @Test
    public void testIsInsideSurface() {
        assertTrue("Didn't recognize that position is inside the surface.", surface.isInsideSurface(p1));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(posOutsideSurface));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(new Position(-1, 0)));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(new Position(0, -1)));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(new Position(4, 4)));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(new Position(3, 5)));
    }
    
    // TODO: test getHorizontalNeighbourTile (tile, pos, corner)
    
    // TODO: test getVerticalNeighbourTile (tile, pos, corner)
    
    // TODO: test getDiagonalNeighbourTile (tile, pos, corner)
    
    // TODO: test getCornerPos (tile, pos, corner)
    
    // TODO: test getTopLeft (tile, pos, corner)

}
