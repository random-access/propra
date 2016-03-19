package ess.rules.implicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of <code>IRule</code> checks if a Tile that is about to be placed at the given
 * Position exceeds the Surface of a Composite. It does so by checking if the top left edge and the 
 * bottom right edge of the Tile are both inside the Surface, when placed at the given Position. 
 * If this is the case, the rule is not broken.
 * 
 * @author Monika Schrenk
 */
public class TileExceedsSurfaceRule implements IRule {
    
    private Composite composite;
    
    /**
     * Initializes an instance of <code>TileExceedsSurfaceRule</code>.
     *
     * @param composite the Composite that will be checked against this set of rules.
     */
    public TileExceedsSurfaceRule(Composite composite) {
        this.composite = composite;
    }

	/* (non-Javadoc)
	 * @see ess.rules.IRule#check(ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean check(Tile tile, Position pos) {
	    Surface s = composite.getSurface();
	    Position bottomRight = s.getCornerPos(tile, pos, Corner.BOTTOM_RIGHT);
	    return (s.isInsideSurface(pos) && s.isInsideSurface(bottomRight));
	}

	/* (non-Javadoc)
	 * @see ess.rules.IRule#getErrorType()
	 */
	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}
	
    @Override
    public String getAdditionalErrorMessage() {
        return "";
    }
}

