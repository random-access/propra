package Algorithm_Component;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ess.algorithm.Displayer;
import ess.data.Composite;
import ess.data.Tile;
import ess.exc.PropertyException;

public class Displayer_Test {
    
    @Test
    public void testDisplayValidComposite() throws PropertyException {
        ArrayList<String> tileList = new ArrayList<>();
        ArrayList<Tile> tileSorts = new ArrayList<>();
        Composite c = new Composite(2, 2, tileList, tileSorts);
        Tile tile = new Tile("_1", 2, 2);
        tileList.add(tile.getId());
        tileSorts.add(tile);

        new Displayer(c).constructOutput();
        
        for (int i = 0; i < c.getSurface().getRows(); i++) {
            for (int j = 0; j < c.getSurface().getRows(); j++) {
                assertNotNull(c.getSurface().getEntryAt(i, j));
            } 
        }
    }

    @Test
    public void testDisplayOverlappingTiles() throws PropertyException {
        ArrayList<String> tileList = new ArrayList<>();
        ArrayList<Tile> tileSorts = new ArrayList<>();
        Composite c = new Composite(2, 3, tileList, tileSorts);
        Tile tile = new Tile("_1", 1, 2);
        tileList.add(tile.getId());
        tileSorts.add(tile);
        
        Tile tile2 = new Tile("_2", 2, 1);
        tileList.add(tile2.getId());
        tileSorts.add(tile2);
        
        Tile tile3 = new Tile("_3", 1, 3);
        tileList.add(tile3.getId());
        tileSorts.add(tile3);
        
        new Displayer(c).constructOutput();
        
        for (int i = 0; i < c.getSurface().getRows(); i++) {
            for (int j = 0; j < c.getSurface().getRows(); j++) {
                assertNotNull(c.getSurface().getEntryAt(i, j));
            } 
        }
        assertEquals(c.getSurface().getEntryAt(1, 2), tile3);
    }
    
    @Test
    public void testDisplayTilesExceedSurface() throws PropertyException {
        ArrayList<String> tileList = new ArrayList<>();
        ArrayList<Tile> tileSorts = new ArrayList<>();
        Composite c = new Composite(2, 2, tileList, tileSorts);
        Tile tile = new Tile("_1", 3, 2);
        tileList.add(tile.getId());
        tileSorts.add(tile);
        
        new Displayer(c).constructOutput();
        
        for (int i = 0; i < c.getSurface().getRows(); i++) {
            for (int j = 0; j < c.getSurface().getRows(); j++) {
                assertNotNull(c.getSurface().getEntryAt(i, j));
            } 
        }
    }
   
}
