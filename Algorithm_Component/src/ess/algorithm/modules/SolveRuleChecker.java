package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
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
	public boolean checkExplicitRules(Composite composite, Tile tile, Position pos) {
		for (IRule rule : ruleSet.getExplicitRules()) {
			 if (!rule.check(composite, tile, pos)){
				 return false;
			 }
		}
		return true;
	}

	@Override
	public boolean checkImplicitRules(Composite composite, Tile tile, Position pos) {
		for (IRule rule : ruleSet.getImplicitRules()) {
			 if (!rule.check(composite, tile, pos)){
				 return false;
			 }
		}
		return true;
	}
}
