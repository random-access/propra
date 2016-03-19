package ess.rules.endconditions;

import java.util.ArrayList;
import java.util.Collections;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.data.TileComparator;
import ess.rules.ErrorType;
import ess.rules.IRule;

public class UsedAllTilesRule implements IRule{
    
    private Composite composite;
    private ArrayList<Tile> usedTiles;

    public UsedAllTilesRule(Composite composite) {
        this.composite = composite;
        usedTiles = new ArrayList<>();
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.NOT_ALL_TILES_USED;
    }

    @Override
    public boolean check(Tile tile, Position pos) {
        for (int i = 0; i < composite.getSurface().getRows(); i++) {
            for (int j = 0; j < composite.getSurface().getCols(); j++) {
                Tile currentTile = composite.getSurface().getEntryAt(i, j);
                if (!usedTiles.contains(currentTile)) {
                    usedTiles.add(currentTile);
                }
                if (usedTiles.size() == composite.getTileSorts().size()) {
                	setSmallestTile();
                	// setUnusedTiles();
                    return true;
                }
            }
        }
        setSmallestTile();
        // setUnusedTiles();
        return false;
    }
    
    private void setSmallestTile() {
    	if (usedTiles.size() > 1) {
        	Collections.sort(usedTiles, TileComparator.FIELDS_ASC);
        	int noOfFields = usedTiles.get(0).getNumberOfFields();
        	int currentNoOfFields = noOfFields;
        	int index = 0;
        	while (index < usedTiles.size() && currentNoOfFields == noOfFields) {
        		composite.getSurface().addToSmallestTileList(usedTiles.get(index));
        		index++;
        		currentNoOfFields = usedTiles.get(index).getNumberOfFields();
        	}
        }
    }
    
//    private ArrayList<Tile> setUnusedTiles() {
//        ArrayList<Tile> unusedTiles = new ArrayList<>();
//        for (Tile t : composite.getTileSorts()) {
//            if (!usedTiles.contains(t)) {
//                composite.getSurface().addToUnusedTiles(t);
//            }
//        }
//        return unusedTiles;
//    }
    
    @Override
    public String getAdditionalErrorMessage() {
        StringBuilder sb = new StringBuilder("Unbenutzte Fliesen: ");
        for (Tile t : composite.getTileSorts()) {
            if (!usedTiles.contains(t)) {
                sb.append("Fliese " + t.getId()).append(", ");
            }
        }
        if (sb.length() >= 2) {
            sb.replace(sb.length() -2, sb.length(), "");
        }
        return sb.toString();
    }

}