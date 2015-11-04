package ess.algorithm.modules;

import java.util.LinkedList;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.data.Composite;
import ess.data.SurfaceEntry;
import ess.rules.sets.IRuleSet;
import ess.rules.sets.RuleSet;

public class RuleChecker {

	private IRuleSet ruleSet;
	
	public RuleChecker() {
		ruleSet = new RuleSet();
	}
	
	public boolean checkImplicitRules(Composite composite, SurfaceEntry entry) {
		return ruleSet.checkImplicitRules(composite, entry);
	}
	
	public boolean checkExplicitRules(Composite composite, SurfaceEntry entry) {
		return ruleSet.checkExplicitRules(composite, entry);
	}
	
	public LinkedList<Validation> getErrorList() {
		return RuleMapper.mapErrors(ruleSet);
	}
	
}
