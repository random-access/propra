package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.exc.PropertyException;
import ess.rules.IRule;
import ess.rules.sets.IRuleSet;
import ess.rules.sets.RuleSet;

/**
 * This class is an implementation of IRuleChecker that is optimized for
 * checking rules during a solve algorithm. If any rule gets broken, the check 
 * exits immediately to improve performance.
 * 
 * @author Monika Schrenk
 */
public class SolveRuleChecker implements IRuleChecker {

private IRuleSet ruleSet;
	
    /**
     * Instantiates a SolveRuleChecker.
     * @throws PropertyException if the config.properties file cannot be read or if it contains
     * invalid parameters
     */
	public SolveRuleChecker() throws PropertyException {
		ruleSet = new RuleSet();
	}

	/* (non-Javadoc)
	 * @see ess.algorithm.modules.IRuleChecker#checkExplicitRules(ess.data.Composite, ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean checkExplicitRules(Composite composite, Tile tile, Position pos) {
		for (IRule rule : ruleSet.getExplicitRules()) {
			 if (!rule.check(composite, tile, pos)) {
				 return false;
			 }
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see ess.algorithm.modules.IRuleChecker#checkImplicitRules(ess.data.Composite, ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean checkImplicitRules(Composite composite, Tile tile, Position pos) {
		for (IRule rule : ruleSet.getImplicitRules()) {
			 if (!rule.check(composite, tile, pos)) {
				 return false;
			 }
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see ess.algorithm.modules.IRuleChecker#checkEndConditions(ess.data.Composite, ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean checkEndConditions(Composite composite, Tile tile, Position pos) {
		for (IRule rule : ruleSet.getEndConditions()) {
			 if (!rule.check(composite, tile, pos)) {
				 return false;
			 }
		}
		return true;
	}
}
