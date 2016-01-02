package ess.rules.implicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of IRule checks if a tile that is about to be placed at pos 
 * exceeds the surface of composite. It does so by checking if the top left edge and the bottom
 * right edge of the tile are both inside the surface, when placed at pos. 
 * If this is the case, the rule is not broken.
 * 
 * @author Monika Schrenk
 */
public class TileExceedsSurfaceRule implements IRule {
    
    private Composite composite;
    
    /**
     * Initializes an instance of TileExceedsSurfaceRule
     * @param composite the composite
     */
    public TileExceedsSurfaceRule(Composite composite) {
        this.composite = composite;
    }

	@Override
	public boolean check(Tile tile, Position pos) {
	    Surface s = composite.getSurface();
	    Position bottomRight = s.getCornerPos(tile, pos, Corner.BOTTOM_RIGHT);
	    return (s.isInsideSurface(pos) && s.isInsideSurface(bottomRight));
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}

}

