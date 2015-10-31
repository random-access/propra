package rulesets;

import java.util.LinkedList;

import ess.data.Composite;
import ess.data.SurfaceEntry;
import ess.rules.IRule;

public interface IRuleSet {
	
	public LinkedList<IRule> getRuleSet();

	public boolean checkRules(Composite c, SurfaceEntry e);

}
