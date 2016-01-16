package Algorithm_Component;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ess.algorithm.modules.DiagonalPosFinder;
import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.LeftToRightPosFinder;
import ess.algorithm.modules.TopToBottomPosFinder;
import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;

public class Position_Finder_Test {
    
    private Composite composite; 
    
    @Before
    public void constructComposite() {
        ArrayList<Tile> tileSorts = new ArrayList<>();
        tileSorts.add(new Tile("_1", 1, 1));
        composite = new Composite(3, 2, new ArrayList<String>(), tileSorts);
    }

    @Test
    public void testTopToBottomPosFinderFindsEveryPos() {
        Surface s = fillSurface(new TopToBottomPosFinder());
        
        for (int i = 0; i < s.getRows(); i++) {
            for (int j = 0; j < s.getCols(); j++) {
                assertNotNull("Every position should contain a tile.", s.getEntryAt(i, j));
            }
        }
    }
    
    @Test
    public void testTopToBottomPosFinderFindsNullWhenFilled() {
        IPositionFinder posFinder = new TopToBottomPosFinder();
        fillSurface(posFinder);
       
        Position nextPos = posFinder.findNextFreePosition(composite, null);
        assertNull("If surface is filled completely, next position must be null.", nextPos);
    }
    
    @Test
    public void testLeftToRightPosFinderFindsNullWhenFilled() {
        IPositionFinder posFinder = new LeftToRightPosFinder();
        fillSurface(posFinder);
       
        Position nextPos = posFinder.findNextFreePosition(composite, null);
        assertNull("If surface is filled completely, next position must be null.", nextPos);
    }
    
    @Test
    public void testDiagonalPosFinderFindsNullWhenFilled() {
        IPositionFinder posFinder = new DiagonalPosFinder();
        fillSurface(posFinder);
       
        Position nextPos = posFinder.findNextFreePosition(composite, null);
        assertNull("If surface is filled completely, next position must be null.", nextPos);
    }
    
    @Test
    public void testLeftToRightPosFinderFindsEveryPos() {
        Surface s = fillSurface(new LeftToRightPosFinder());
        
        for (int i = 0; i < s.getRows(); i++) {
            for (int j = 0; j < s.getCols(); j++) {
                assertNotNull("Every position should contain a tile.", s.getEntryAt(i, j));
            }
        }
    }
    
    @Test
    public void testDiagonalPosFinderFindsEveryPos() {
        Surface s = fillSurface(new DiagonalPosFinder());
        
        for (int i = 0; i < s.getRows(); i++) {
            for (int j = 0; j < s.getCols(); j++) {
                assertNotNull("Every position should contain a tile.", s.getEntryAt(i, j));
            }
        }
    }
    
    @Test
    public void testTopToBottomPosFinderFindsFirstEmptyPos() {
        Position currentPos = null;
        Position nextPos = new TopToBottomPosFinder().findNextFreePosition(composite, currentPos);
        assertEquals("PosFinder should return the top left corner.", nextPos, new Position(0, 0));
    }
    
    @Test
    public void testTopToBottomPosFinderFindsNextPos() {
        Surface s = composite.getSurface();
        IPositionFinder posFinder = new TopToBottomPosFinder();
        Position pos = posFinder.findNextFreePosition(composite, null);
        s.insertEntry(composite.findTileById("_1"), pos);
        pos = posFinder.findNextFreePosition(composite, pos);
        
        assertNull("Position should not contain a tile yet.", s.getEntryAt(pos));
        assertEquals("PosFinder should return the expected position.", pos, new Position(0, 1));
    }
    
    @Test
    public void testLeftToRightPosFinderFindsFirstEmptyPos() {
        Position currentPos = null;
        Position nextPos = new LeftToRightPosFinder().findNextFreePosition(composite, currentPos);
        assertEquals("PosFinder should return the top left corner.", nextPos, new Position(0, 0));
    }
    
    @Test
    public void testLeftToRightPosFinderFindsNextPos() {
        Surface s = composite.getSurface();
        IPositionFinder posFinder = new LeftToRightPosFinder(); 
        Position pos = posFinder.findNextFreePosition(composite, null);
        s.insertEntry(composite.findTileById("_1"), pos);
        pos = posFinder.findNextFreePosition(composite, pos);
        
        assertNull("Position should not contain a tile yet.", s.getEntryAt(pos));
        assertEquals("PosFinder should return the expected position.", pos, new Position(1, 0));
    }
    
    @Test
    public void testDiagonalPosFinderFindsFirstEmptyPos() {
        Position currentPos = null;
        Position nextPos = new TopToBottomPosFinder().findNextFreePosition(composite, currentPos);
        assertEquals("PosFinder should return the top left corner.", nextPos, new Position(0, 0));
    }
    
    @Test
    public void testDiagonalPosFinderFindsNextPos() {
        Surface s = composite.getSurface();
        TopToBottomPosFinder posFinder = new TopToBottomPosFinder();
        Position pos = null;
        
        for (int i = 0; i < s.getCols(); i++) {
            pos = posFinder.findNextFreePosition(composite, pos);
            s.insertEntry(composite.findTileById("_1"), pos);
        }

        pos = posFinder.findNextFreePosition(composite, pos);
        
        assertNull("Position should not contain a tile yet.", s.getEntryAt(pos));
        assertEquals("PosFinder should return the expected position.", pos, new Position(1, 0));
    }
    
    public Surface fillSurface(IPositionFinder posFinder) {
        Surface s = composite.getSurface();
        Position currentPos = null; 
        Tile t = composite.findTileById("_1");
        for (int i = 0; i < s.getRows(); i++) {
            for (int j = 0; j < s.getCols(); j++) {
                currentPos = posFinder.findNextFreePosition(composite, currentPos);
                s.insertEntry(t, currentPos);
            }
        }
        return s;
    }

}
