package ess.rules.endconditions;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This implementation of IRule checks if a surface is filled completely with tiles 
 * by searching if there is any free spot in a composite's surface left.
 * 
 * @author Monika Schrenk
 *
 */
public class SurfaceIsFilledCompletelyRule implements IRule {

	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}

	@Override
	public boolean check(Composite c, Tile t, Position pos) {
		return pos == null;
	}

}
