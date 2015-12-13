package ess.algorithm.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;

public class RandomTileChooser implements ITileChooser {

    private ArrayList<Tile> tileSorts;
    private LinkedList<boolean[]> testedTiles;
    private Random random;

    public RandomTileChooser(Composite composite) {
        tileSorts = new ArrayList<Tile>(composite.getTileSorts());
        testedTiles = new LinkedList<>();
        random = new Random();
    }

    @Override
    public Tile getNextTile(Position pos, Tile tile) {
        if (tile == null) {
           //  System.out.println("New boolean[] for pos " + pos);
            testedTiles.add(new boolean[tileSorts.size()]);
        } else {
           //  System.out.println("Retrieving boolean[] for pos " + pos);
        }
        int nextIndex = getNextTileIndex();
        // System.out.println("Next index: " + nextIndex);
        boolean[] currentTestedTiles = testedTiles.peekLast();
        // System.out.println("Tested tiles at current pos: " + Arrays.toString(currentTestedTiles));
        for (int i = 0; i < tileSorts.size(); i++) {
            int curr = (nextIndex + i) % tileSorts.size();
            if (!currentTestedTiles[curr]) {
                // System.out.println("Get tile at " + curr);
                currentTestedTiles[curr] = true;
                return tileSorts.get(curr);
            }
        }
        // System.out.println("All tiles tried out at pos " + pos);
        testedTiles.removeLast();
        return null;
    }

    private int getNextTileIndex() {
        return random.nextInt(tileSorts.size());
    }

}
