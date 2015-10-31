package ess.rules;

import ess.data.Composite;
import ess.data.SurfaceEntry;

public interface IRule {
	
	public boolean check(Composite c, SurfaceEntry e);
	
}
