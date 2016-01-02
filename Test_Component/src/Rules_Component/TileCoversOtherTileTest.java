package Rules_Component;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.IRule;
import ess.rules.implicit.TileCoversOtherTileRule;

public class TileCoversOtherTileTest {
    
   private Composite composite;

   @Before
   public void initialize() {
       ArrayList<Tile> tileSorts = new ArrayList<Tile>();
       Tile tile0 = new Tile("_0", 1, 2);
       tileSorts.add(tile0);
       Tile tile1 = new Tile("_1", 2, 1);
       tileSorts.add(tile1);
       Tile tile2 = new Tile("_2", 2, 2);
       tileSorts.add(tile2);
       Tile tile3 = new Tile("_3", 3, 2);
       tileSorts.add(tile3);
       Tile tile4 = new Tile("_4", 2, 3);
       tileSorts.add(tile4);
       composite = new Composite(4, 5, new ArrayList<String>(), tileSorts);
   }

   @Test
   public void testTileCoversOtherTile() {
       Surface s = composite.getSurface();
       s.insertEntry(composite.findTileById("_0"), new Position(0, 0));
       s.insertEntry(composite.findTileById("_2"), new Position(0, 1));

       IRule rule = new TileCoversOtherTileRule(composite);
       Tile tile = composite.findTileById("_1");
       Position pos = new Position(1, 1);
       boolean validMove = rule.check(tile, pos);
       s.insertEntry(tile, pos);
       
       assertFalse(validMove);
   }
   
   @Test
   public void testTileCoversOtherTile2() {
       Surface s = composite.getSurface();
       s.insertEntry(composite.findTileById("_0"), new Position(0, 0));
       s.insertEntry(composite.findTileById("_2"), new Position(0, 1));

       IRule rule = new TileCoversOtherTileRule(composite);
       Tile tile = composite.findTileById("_1");
       Position pos = new Position(2, 1);
       boolean validMove = rule.check(tile, pos);
       s.insertEntry(tile, pos);
       
       assertTrue(validMove);
   }
}
