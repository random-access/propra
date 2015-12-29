package ess.algorithm.modules;

import java.util.LinkedList;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.exc.PropertyException;
import ess.rules.IRule;
import ess.rules.sets.IRuleSet;
import ess.rules.sets.ValidationRuleSet;

/**
 * This class is an implementation of IRuleChecker for checking rules during a validation 
 * algorithm. If any explicit rule gets broken, the check continues because the validator must 
 * return a list of all explicit rules that were broken.<br>
 * If an implicit rule gets broken, the validator returns immediately because validation 
 * cannot be continued in this case. <br>
 * This class provides an additional method for retrieving the list of broken rules.
 * 
 * @author Monika Schrenk
 */
public class ValidationRuleChecker implements IRuleChecker {

	private IRuleSet ruleSet;
	
	/**
     * Instantiates a ValidationRuleChecker.
     * @throws PropertyException if the config.properties file cannot be read or if it contains
     * invalid parameters
     */
	public ValidationRuleChecker() throws PropertyException {
		ruleSet = new ValidationRuleSet();
	}
	
	/* (non-Javadoc)
	 * @see ess.algorithm.modules.IRuleChecker#checkImplicitRules(ess.data.Composite, ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean checkImplicitRules(Composite composite, Tile tile, Position pos) {
		boolean validMove = true;
		for (IRule rule : ruleSet.getEndConditions()) {
		    boolean isEnd = rule.check(composite, tile, pos);
            if (isEnd) {
                ruleSet.addError(rule.getErrorType());
            }
            validMove &= !isEnd;
        }
		// TODO stop checking if any implicit rule gets broken
		for (IRule rule : ruleSet.getImplicitRules()) {
			boolean ok = rule.check(composite, tile, pos);
			if (!ok) {
				ruleSet.addError(rule.getErrorType());
			}
			validMove &= ok;
		}
		return validMove;
	}
	
	/* (non-Javadoc)
	 * @see ess.algorithm.modules.IRuleChecker#checkExplicitRules(ess.data.Composite, ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean checkExplicitRules(Composite composite, Tile tile, Position pos) {
		boolean validMove = true;
		for (IRule rule : ruleSet.getExplicitRules()) {
			boolean ok = rule.check(composite, tile, pos);
			if (!ok) {
				ruleSet.addError(rule.getErrorType());
			}
			validMove &= ok;
		}
		return validMove;
	}
	
	/* (non-Javadoc)
	 * @see ess.algorithm.modules.IRuleChecker#checkEndConditions(ess.data.Composite, ess.data.Tile, ess.data.Position)
	 */
	@Override
	public boolean checkEndConditions(Composite composite, Tile tile, Position pos) {
		boolean completed = true;
		for (IRule rule : ruleSet.getEndConditions()) {
			boolean isEnd = rule.check(composite, tile, pos);
			if (!isEnd) {
				ruleSet.addError(rule.getErrorType());
			}
			completed &= isEnd;
		}
		return completed;
	}
	
	/**
	 * Returns a list of all explicit rules that were broken during validation and additionally
	 * Validation.OTHER if any implicit rule or end condition got broken.
	 * 
	 * @return A list with broken rules.
	 */
	public LinkedList<Validation> getErrorList() {
		return ErrorMapper.mapErrorsForAlgoComponent(ruleSet);
	}
	
}
