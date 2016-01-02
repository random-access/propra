package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

public class DiagonalPosFinder implements IPositionFinder{

    @Override
    public Position findNextFreePosition(Composite composite, Position pos) {
        int min = (pos == null) ? 0 : Math.min(pos.getRow(), pos.getCol());
        Surface s = composite.getSurface();
        for (int i = min; i < Math.min(s.getRows(), s.getCols()); i++) {
            for (int j = i; j < s.getCols(); j++) {
                if (s.getEntryAt(i, j) == null) {
                    return new Position(i, j);
                }
            }
            for (int k = i; k < s.getRows(); k++) {
                if (s.getEntryAt(k, i) == null) {
                    return new Position(k, i);
                }
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        ArrayList<Tile> tileSorts = new ArrayList<>();
//        Tile t = new Tile("_0", 1,1);
//        tileSorts.add(t);
//        Composite c = new Composite(3, 5, new ArrayList<String>(), tileSorts);
//        
//        DiagonalPosFinder f = new DiagonalPosFinder();
//        Position pos = f.findNextFreePosition(c, null);
//        while (pos != null) {
//            c.getSurface().insertEntry(t, pos);
//            System.out.println("Inserted entry at " + pos);
//            System.out.println(c.getSurface());
//            pos = f.findNextFreePosition(c, pos);
//        }
//        
//    }
}
