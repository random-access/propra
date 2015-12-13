package ess.algorithm.modules;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;

/**
 * This class is an implementation of ITileChooser that chooses the next tile
 * randomly. 
 * 
 * @author Monika Schrenk
 */
public class RandomTileChooser implements ITileChooser {

    // tile sorts available for solving the composite
    private ArrayList<Tile> tileSorts;

    // stores infos about which tiles were tried out
    // set indices of tiles that were tried out to true
    private LinkedList<boolean[]> testedTiles;

    // random number generator
    private Random random;

    /**
     * Instantiates a new sorted tile chooser.
     *
     * @param composite The composite that stores the surface where the next tile should be inserted.
     */
    public RandomTileChooser(Composite composite) {
        tileSorts = new ArrayList<Tile>(composite.getTileSorts());
        testedTiles = new LinkedList<>();
        random = new Random();
    }

    /* (non-Javadoc)
     * @see ess.algorithm.modules.ITileChooser#getNextTile(ess.data.Position, ess.data.Tile)
     */
    @Override
    public Tile getNextTile(Position pos, Tile tile) {
        
        // if trying to fetch a tile at pos for the first time, create a new array to store informations
        if (tile == null) {
            testedTiles.add(new boolean[tileSorts.size()]);
        } 
        
        // get an index and informations about which tiles were tried out before
        int randomIndex = getRandomTileIndex();
        boolean[] currentTestedTiles = testedTiles.peekLast();
        
        // first try to fetch tile at a random index, then iterate through all tiles, trying to find 
        // a tile that wasn't tried out yet
        for (int i = 0; i < tileSorts.size(); i++) {
            int curr = (randomIndex + i) % tileSorts.size();
            
            // return first tile that wasn't tried out yet & store that this tile gets tried out now
            if (!currentTestedTiles[curr]) {
                currentTestedTiles[curr] = true;
                return tileSorts.get(curr);
            }
        }
        
        // when arriving here, no tile is fitting at this position
        testedTiles.removeLast();
        return null;
    }

    // generates a random number between 0 and number of tile sorts - 1
    private int getRandomTileIndex() {
        return random.nextInt(tileSorts.size());
    }

}
