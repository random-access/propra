package ess.rules;

import ess.data.Composite;
import ess.data.SurfaceEntry;
import ess.rules.sets.ErrorType;

public interface IRule {
	
	public ErrorType getErrorType();
	
	public boolean check(Composite c, SurfaceEntry e);
	
}
