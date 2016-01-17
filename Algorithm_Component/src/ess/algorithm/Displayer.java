package ess.algorithm;

import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.TopToBottomPosFinder;
import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.exc.PropertyException;

/**
 * This class is an implementation of IDisplayer, which fills the 
 * surface of a composite with the given tiles from top left to bottom
 * right, line by line. 
 * @author monika
 *
 */
public class Displayer implements IDisplayer {

    private Composite composite;
    private IPositionFinder posFinder;

    /**
     * Instantiates a <code>Displayer</code>.
     * @param composite the <code>Composite</code> whose <code>Surface</code> gets filled with
     * tiles.
     * @throws PropertyException if any parameter was not defined properly in the configuration 
     * file or the configuration file cannot be read
     */
    public Displayer(Composite composite) throws PropertyException {
        this.composite = composite;
        posFinder = new TopToBottomPosFinder();
    }

    @Override
    public void constructOutput() {
        fillSurface();
    }

    // fills the surface with tiles in tileList, checking all rules before
    // placing a tile.
    private void fillSurface() {
        Position pos = null;
        Tile tile = null;

        // try to place all tiles in the composite's tile list in the surface
        for (String ident : composite.getSurfaceTileList()) {
            pos = posFinder.findNextFreePosition(composite, pos);
            tile = composite.findTileById(ident);
            if (pos != null) {
                composite.getSurface().insertEntryWherePossible(tile, pos);
            }
        }
    }
}
