package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.SurfaceEntry;
import ess.rules.IRule;
import ess.rules.sets.IRuleSet;
import ess.rules.sets.RuleSet;
import ess.utils.PropertyException;

public class SolveRuleChecker implements IRuleChecker {

private IRuleSet ruleSet;
	
	public SolveRuleChecker() throws PropertyException {
		ruleSet = new RuleSet();
	}

	@Override
	public boolean checkExplicitRules(Composite composite, SurfaceEntry entry) {
		for (IRule rule : ruleSet.getExplicitRules()) {
			 if (!rule.check(composite, entry)){
				 return false;
			 }
		}
		return true;
	}

	@Override
	public boolean checkImplicitRules(Composite composite, SurfaceEntry entry) {
		for (IRule rule : ruleSet.getImplicitRules()) {
			 if (!rule.check(composite, entry)){
				 return false;
			 }
		}
		return true;
	}
}
