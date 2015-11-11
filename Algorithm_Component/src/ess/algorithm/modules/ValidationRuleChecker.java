package ess.algorithm.modules;

import java.util.LinkedList;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.data.Composite;
import ess.data.SurfaceEntry;
import ess.rules.IRule;
import ess.rules.sets.IRuleSet;
import ess.rules.sets.RuleSet;
import ess.utils.PropertyException;

public class ValidationRuleChecker implements IRuleChecker{

	private IRuleSet ruleSet;
	
	public ValidationRuleChecker() throws PropertyException {
		ruleSet = new RuleSet();
	}
	
	public boolean checkImplicitRules(Composite composite, SurfaceEntry entry) {
		boolean validMove = true;
		for (IRule rule : ruleSet.getImplicitRules()) {
			boolean ok = rule.check(composite, entry);
			if (!ok) {
				ruleSet.addError(rule.getErrorType());
			}
			validMove &= ok;
		}
		return validMove;
	}
	
	public boolean checkExplicitRules(Composite composite, SurfaceEntry entry) {
		boolean validMove = true;
		for (IRule rule : ruleSet.getExplicitRules()) {
			boolean ok = rule.check(composite, entry);
			if (!ok) {
				ruleSet.addError(rule.getErrorType());
			}
			validMove &= ok;
		}
		return validMove;
	}
	
	public LinkedList<Validation> getErrorList() {
		return ErrorMapper.mapErrors(ruleSet);
	}
	
}
