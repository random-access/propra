package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.exc.PropertyException;
import ess.rules.IRule;
import ess.rules.sets.IRuleSet;
import ess.rules.sets.SolveRuleSet;

/**
 * This class is an implementation of <code>IRuleChecker</code> that is optimized for
 * checking IRules during running a solve algorithm. If any rule gets broken, the check 
 * exits immediately to improve performance.
 * 
 * @author Monika Schrenk
 */
public class SolveRuleChecker implements IRuleChecker {

private IRuleSet ruleSet;
	
    /**
     * Instantiates a <code>SolveRuleChecker</code>.
     * @param composite the Composite that gets checked 
     * @throws PropertyException if the config.properties file cannot be read or if it contains
     * invalid parameters
     */
	public SolveRuleChecker(Composite composite) throws PropertyException {
		ruleSet = new SolveRuleSet(composite);
	}

	/**
	 * Checks if placing a <code>Tile</code> breaks any of the explicit rules activated via config.properties.
	 * If a rule is broken the Tile cannot be placed, in this case the check is stopped immediately. 
	 * @see IRuleChecker#checkExplicitRules(ess.data.Composite, ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean checkExplicitRules(Composite composite, Tile tile, Position pos) {
		for (IRule rule : ruleSet.getExplicitRules()) {
			 if (!rule.check(tile, pos)) {
				 return false;
			 }
		}
		return true;
	}

	/**
     * Checks if placing a <code>Tile</code> breaks any of the implicit rules activated via config.properties.
     * If a rule is broken the Tile cannot be placed, in this case the check is stopped immediately. 
     * @see IRuleChecker#checkExplicitRules(ess.data.Composite, ess.data.Tile, ess.data.Position)
     */
	@Override
	public boolean checkImplicitRules(Composite composite, Tile tile, Position pos) {
		for (IRule rule : ruleSet.getImplicitRules()) {
			 if (!rule.check(tile, pos)) {
				 return false;
			 }
		}
		return true;
	}
	
	/**
     * Checks if the <code>Composite</code> is filled completely.
     * 
     * @see IRuleChecker#checkEndConditions(ess.data.Composite, ess.data.Tile,
     *      ess.data.Position)
     */
	@Override
	public boolean checkEndConditions(Composite composite, Tile tile, Position pos) {
		for (IRule rule : ruleSet.getEndConditions()) {
			 if (!rule.check(tile, pos)) {
				 return false;
			 }
		}
		return true;
	}
}
