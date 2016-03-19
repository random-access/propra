package ess.rules.endconditions;

import java.util.ArrayList;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
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
                    composite.getSurface().setUsedTiles(usedTiles);
                    return true;
                }
            }
        }
        composite.getSurface().setUsedTiles(usedTiles);
        return false;
    }
    
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