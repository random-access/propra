package Data_Component;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;

public class Surface_Test {
    
    private Surface surface;
    private Tile t1;
    private Tile t2;
    private Tile t3;
    private Tile t4;
    private Position p1;
    private Position p2;
    private Position p3;
    private Position p4;
    
    @Before
    public void createSurfaceAndContent() {
        surface = new Surface(4, 5);
        t1 = new Tile("_1", 1, 1);
        t2 = new Tile("_2", 2, 1);
        t3 = new Tile("_3", 1, 2);
        t4 = new Tile("_4", 3, 2);
        p1 = new Position(0, 0);
        p2 = new Position(0, 1);
        p3 = new Position(0, 2);
        p4 = new Position(1, 2);
        
        
        surface.insertEntry(t1, p1);
        surface.insertEntry(t2, p2);
        surface.insertEntry(t3, p3);
    }

    @Test
    public void testGetEntryAt() {
        Position pos = new Position(1, 1);
        Position emptyPos = new Position(0, 4);
        Position posOutsideSurface = new Position(10, 10);
        assertEquals("Wrong entry or null was returned.", surface.getEntryAt(p1), t1);
        assertEquals("Wrong entry or null was returned.", surface.getEntryAt(p2), t2);
        assertEquals("Wrong entry or null was returned.", surface.getEntryAt(p3), t3);
        assertEquals("Wrong entry or null was returned.", surface.getEntryAt(pos), t2);
        assertNull("For empty positions, null must be returned.", surface.getEntryAt(emptyPos));
        assertNull("For positions outside the surface, null must be returned.", surface.getEntryAt(posOutsideSurface));
    }
    
    @Test
    public void testInsertingTiles() {
        Position emptyPosition = new Position(0, 4);
        surface.insertEntry(t1, emptyPosition);
        
        assertNotNull("Entry was not inserted correctly.", surface.getEntryAt(emptyPosition));
    }
    
    @Test
    public void testRemovingTiles() {
        Position p2b = new Position(1, 1);
        surface.removeEntry(t2, p2);
        
        assertNull("Entry was not removed correctly", surface.getEntryAt(p2));
        assertNull("Entry was not removed correctly", surface.getEntryAt(p2b));
    }
   
    @Test
    public void testIsBorderPosition() {
        Position innerPos = new Position(1, 1);
        Position leftColPos = new Position(2, 0);
        assertTrue("Corner positions must be border positions.", surface.isBorderPosition(p1));
        assertTrue("Positions in border rows must be border positions.", surface.isBorderPosition(p2));
        assertFalse("Positions in the middle of the surfaces are not border positions.", surface.isBorderPosition(innerPos));
        assertTrue("Positions in border columns must be border positions.", surface.isBorderPosition(leftColPos));
    }
    
    @Test
    public void testIsBorderRow() {
        Position bottomLeftCornerPos = new Position(3, 0);
        Position innerPos = new Position(1, 1);
        Position leftColPos = new Position(2, 0);
        
        assertTrue("Corner positions must be border rows.", surface.isBorderRow(p1));
        assertTrue("Positions in top row must be border rows.", surface.isBorderRow(p2));
        assertTrue("Positions in corners must be border rows.", surface.isBorderRow(bottomLeftCornerPos));
        assertFalse("Positions in the middle of the surfaces are not border rows.", surface.isBorderRow(innerPos));
        assertFalse("Positions in border columns are no border rows.", surface.isBorderRow(leftColPos));
    }
    
    @Test
    public void testIsBorderCol() {
        Position innerPos = new Position(1, 1);
        Position leftColPos = new Position(2, 0);
        Position bottomRightCornerPos = new Position(3, 4);
        
        assertTrue("Corner positions must be border columns.", surface.isBorderCol(p1));
        assertFalse("Positions in border rows are no border columns.", surface.isBorderCol(p2));
        assertFalse("Positions in the middle of the surfaces are not border columns.", surface.isBorderCol(innerPos));
        assertTrue("Positions in left column must be border columns.", surface.isBorderCol(leftColPos));
        assertTrue("Positions in corners must be border columns.", surface.isBorderCol(bottomRightCornerPos));
    }
    
    @Test
    public void testIsInsideSurface() {
        Position outsideSurfacePos = new Position(10, 10);
        Position aboveSurfaceRow = new Position(-1, 0);
        Position aboveSurfaceCol = new Position(0, -1);
        Position belowSurfaceRow = new Position(4, 4);
        Position belowSurfaceCol = new Position(3, 5);
        
        assertTrue("Didn't recognize that position is inside the surface.", surface.isInsideSurface(p1));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(outsideSurfacePos));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(aboveSurfaceRow));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(aboveSurfaceCol));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(belowSurfaceRow));
        assertFalse("Didn't recognize that position is not inside the surface.", surface.isInsideSurface(belowSurfaceCol));
    }
    
    @Test
    public void testGetHorizontalNeighbourTile() {
        Position bottomRightCornerPos = new Position(3, 4);
        surface.insertEntry(t4, p4);
        surface.insertEntry(t1, bottomRightCornerPos);
        
        assertEquals(surface.getHorizontalNeighbourTile(t4, p4, Corner.TOP_LEFT), t2);
        assertEquals(surface.getHorizontalNeighbourTile(t4, p4, Corner.TOP_RIGHT), null);
        assertEquals(surface.getHorizontalNeighbourTile(t4, p4, Corner.BOTTOM_LEFT), null);
        assertEquals(surface.getHorizontalNeighbourTile(t4, p4, Corner.BOTTOM_RIGHT), t1);
    }
    
    @Test
    public void testGetVerticalNeighbourTile() {
        surface.insertEntry(t4, p4);
        
        assertEquals(surface.getVerticalNeighbourTile(t4, p4, Corner.TOP_LEFT), t3);
        assertEquals(surface.getVerticalNeighbourTile(t4, p4, Corner.TOP_RIGHT), t3);
        assertEquals(surface.getVerticalNeighbourTile(t4, p4, Corner.BOTTOM_LEFT), null);
        assertEquals(surface.getVerticalNeighbourTile(t4, p4, Corner.BOTTOM_RIGHT), null);
    }
    
    @Test
    public void testGetDiagonalNeighbourTile() {
        surface.insertEntry(t4, p4);
        
        assertEquals(surface.getDiagonalNeighbourTile(t4, p4, Corner.TOP_LEFT), t2);
        assertEquals(surface.getDiagonalNeighbourTile(t4, p4, Corner.TOP_RIGHT), null);
        assertEquals(surface.getDiagonalNeighbourTile(t4, p4, Corner.BOTTOM_LEFT), null);
        assertEquals(surface.getDiagonalNeighbourTile(t4, p4, Corner.BOTTOM_RIGHT), null);
    }
   
    @Test
    public void testGetCornerPos() {
        Position topRightCorner = new Position(1, 3);
        Position bottomLeftCorner = new Position(3, 2);
        Position bottomRightCorner = new Position(3, 3);
        surface.insertEntry(t4, p4);
        
        assertEquals(surface.getCornerPos(t4, p4, Corner.TOP_LEFT), p4);
        assertEquals(surface.getCornerPos(t4, p4, Corner.TOP_RIGHT), topRightCorner);
        assertEquals(surface.getCornerPos(t4, p4, Corner.BOTTOM_LEFT), bottomLeftCorner);
        assertEquals(surface.getCornerPos(t4, p4, Corner.BOTTOM_RIGHT), bottomRightCorner);
    }
    
    @Test
    public void testGetCornerPosAllEnumsCovered() {
        for (Corner c : Corner.values()) {
            assertNotNull(surface.getCornerPos(t2, p2, c));
        }
    }

    @Test
    public void testGetTopLeft() {
        Position topRightCorner = new Position(1, 3);
        Position bottomLeftCorner = new Position(3, 2);
        Position bottomRightCorner = new Position(3, 3);
        surface.insertEntry(t4, p4);
        
        assertEquals(surface.getTopLeft(t4, p4, Corner.TOP_LEFT), p4);
        assertEquals(surface.getTopLeft(t4, topRightCorner, Corner.TOP_RIGHT), p4);
        assertEquals(surface.getTopLeft(t4, bottomLeftCorner, Corner.BOTTOM_LEFT), p4);
        assertEquals(surface.getTopLeft(t4, bottomRightCorner, Corner.BOTTOM_RIGHT), p4);
    }
    
    @Test
    public void testGetTopLeftAllEnumsCovered() {
        for (Corner c : Corner.values()) {
            assertNotNull(surface.getTopLeft(t2, p2, c));
        }
    }
}
