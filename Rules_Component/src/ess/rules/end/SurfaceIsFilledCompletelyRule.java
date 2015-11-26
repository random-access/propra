package ess.rules.end;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.implicit.ImplicitRule;

public class SurfaceIsFilledCompletelyRule implements ImplicitRule {

	@Override
	public ErrorType getErrorType() {
		return ErrorType.OTHER;
	}

	@Override
	public boolean check(Composite c, Tile t, Position pos) {
		return pos == null;
	}

}
