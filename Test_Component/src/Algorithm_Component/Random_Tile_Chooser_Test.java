package Algorithm_Component;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ess.algorithm.modules.ITileChooser;
import ess.algorithm.modules.RandomTileChooser;
import ess.data.Composite;
import ess.data.Tile;

public class Random_Tile_Chooser_Test {

    private Composite composite;

    @Before
    public void constructComposite() {
        ArrayList<Tile> tileSorts = new ArrayList<>();
        tileSorts.add(new Tile("_1", 1, 1));
        tileSorts.add(new Tile("_2", 2, 1));
        tileSorts.add(new Tile("_3", 1, 2));
        tileSorts.add(new Tile("_4", 2, 2));
        composite = new Composite(3, 2, new ArrayList<String>(), tileSorts);
    }

    @Test
    public void testRandomTileChooserChoosesEveryTile() {
        ITileChooser tileChooser = new RandomTileChooser(composite);
        Tile currentTile = null;
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < composite.getTileSorts().size(); i++) {
            currentTile = tileChooser.getNextTile(currentTile);
            tiles.add(currentTile);
        } 

        assertTrue(tiles.contains(composite.findTileById("_1")));
        assertTrue(tiles.contains(composite.findTileById("_2")));
        assertTrue(tiles.contains(composite.findTileById("_3")));
        assertTrue(tiles.contains(composite.findTileById("_4")));
        assertNull(tileChooser.getNextTile(currentTile));
    }

}
