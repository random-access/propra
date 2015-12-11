package ess.algorithm.modules;

import java.util.ArrayList;
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
        if (!tileSorts.isEmpty()) {
            if (tile == null) {
                System.out.println("New boolean[] for pos " + pos);
                testedTiles.add(new boolean[tileSorts.size()]);
            }
            int nextIndex = getNextTileIndex();
            System.out.println("Next index: " + nextIndex);
            boolean[] currentTestedTiles = testedTiles.getLast();
            
            for (int i = 0; i < currentTestedTiles.length; i++) {
                int curr = (nextIndex + i) % currentTestedTiles.length;
                System.out.println("Curr: " + curr);
                if (!currentTestedTiles[curr]) {
                    System.out.println("Get tile at " + curr);
                    currentTestedTiles[curr] = true;
                    return tileSorts.get(curr);
                }
            }
            //System.out.println("No tile anymore for pos " + pos + testedTiles.getLast());
            testedTiles.removeLast(); 
        }
        return null;
    }

    private int getNextTileIndex() {
        return random.nextInt(tileSorts.size());
    }
    
//    public static void main(String[] args) {
//        int a = 5;
//        int b = new Random().nextInt(5);
//        for (int i = 0; i < 5; i++) {
//            System.out.println((b+i) % a);
//        }
//    }

}
