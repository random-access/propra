package ess.algorithm.components;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.IRule;

public class ValidationRuleChecker extends AbstractRuleChecker {

	@Override
	public void checkRule(IRule rule, Validation validation, String[][] surface, Tile tile, Position pos) {
		if (!rule.checkWholeSurface(surface)) {
			addError(validation);
		}
	}

	@Override
	public void loadRules() {
		
	}
}
