package ess.rules.endconditions;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of <code>IRule</code> checks if a surface is filled completely with Tiles 
 * by searching if there is any free spot in a Composite's Surface left.
 * 
 * @author Monika Schrenk
 *
 */
public class SurfaceIsFilledCompletelyRule implements IRule {
    
    /**
     * Initializes an instance of <code>SurfaceIsFilledCompletelyRule</code>.
     *
     * @param composite the Composite that will be checked against this set of rules.
     */
    public SurfaceIsFilledCompletelyRule(Composite composite) {
        // nothing needs to be done
    }

	/* (non-Javadoc)
	 * @see ess.rules.IRule#getErrorType()
	 */
	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}

	/* (non-Javadoc)
	 * @see ess.rules.IRule#check(ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean check(Tile t, Position pos) {
		return pos == null;
	}
}
