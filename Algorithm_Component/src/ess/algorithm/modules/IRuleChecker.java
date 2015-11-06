package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.SurfaceEntry;

public interface IRuleChecker {
	
	public boolean checkExplicitRules(Composite c, SurfaceEntry e);
	
	public boolean checkImplicitRules(Composite c, SurfaceEntry e);
}
