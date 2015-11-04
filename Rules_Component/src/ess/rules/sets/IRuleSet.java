package ess.rules.sets;

import java.util.LinkedList;

import ess.data.Composite;
import ess.data.SurfaceEntry;

public interface IRuleSet {

	public boolean checkExplicitRules(Composite c, SurfaceEntry e);
	
	public boolean checkImplicitRules(Composite c, SurfaceEntry e);
	
	public LinkedList<ErrorType> getErrorList ();

}
