package ess.algorithm.modules;

import java.util.HashMap;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.exc.PropertyException;
import ess.rules.ErrorType;
import ess.rules.IRule;
import ess.rules.sets.IRuleSet;
import ess.rules.sets.ValidationRuleSet;

/**
 * This class is an implementation of <code>IRuleChecker</code> for checking
 * IRules during validation. If any explicit rule gets broken, the check
 * continues because the validator must return a list of all explicit rules that
 * were broken.<br>
 * If an implicit rule gets broken, the validator returns immediately because
 * validation cannot be continued in this case. <br>
 * This class provides an additional method for retrieving the list of broken
 * rules.
 * 
 * @author Monika Schrenk
 */
public class ValidationRuleChecker implements IRuleChecker {

    private IRuleSet ruleSet;

    /**
     * Instantiates a <code>ValidationRuleChecker</code>.
     * 
     * @param composite
     *            the composite
     * @throws PropertyException
     *             if the config.properties file cannot be read or if it
     *             contains invalid parameters
     */
    public ValidationRuleChecker(Composite composite) throws PropertyException {
        ruleSet = new ValidationRuleSet(composite);
    }

    /**
     * Checks if placing a <code>Tile</code> breaks any of the explicit rules activated via
     * config.properties. The check continues until all rules are checked
     * because all errors must be discovered during validation.
     * 
     * @see IRuleChecker#checkExplicitRules(Composite, Tile, Position)
     */
    @Override
    public boolean checkExplicitRules(Composite composite, Tile tile, Position pos) {
        boolean validMove = true;
        for (IRule rule : ruleSet.getExplicitRules()) {
            boolean ok = rule.check(tile, pos);
            if (!ok) {
                ruleSet.addError(rule.getErrorType(), rule.getAdditionalErrorMessage());
            }
            validMove &= ok;
        }
        return validMove;
    }

    /**
     * Checks if placing a <code>Tile</code> breaks any of the implicit rules activated via
     * config.properties. If a rule is broken the tile cannot be placed, in this
     * case the check is stopped immediately.
     * 
     * @see IRuleChecker#checkImplicitRules(ess.data.Composite, ess.data.Tile,
     *      ess.data.Position)
     */
    @Override
    public boolean checkImplicitRules(Composite composite, Tile tile, Position pos) {
        // not working with additional end condition
        /* for (IRule rule : ruleSet.getEndConditions()) {
            boolean isEnd = rule.check(tile, pos);
            if (isEnd) {
                ruleSet.addError(rule.getErrorType());
                return false;
            }
        } */
        for (IRule rule : ruleSet.getImplicitRules()) {
            boolean ok = rule.check(tile, pos);
            if (!ok) {
                ruleSet.addError(rule.getErrorType(), rule.getAdditionalErrorMessage());
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
        boolean completed = true;
        for (IRule rule : ruleSet.getEndConditions()) {
            boolean isEnd = rule.check(tile, pos);
            if (!isEnd) {
                ruleSet.addError(rule.getErrorType(), rule.getAdditionalErrorMessage());
            }
            completed &= isEnd;
        }
        return completed;
    }

    /**
     * Returns a list of all explicit rules that were broken during validation, including
     * <code>Validation.OTHER</code> if any implicit rule or end condition got broken as key, 
     * additional error messages as value.
     * 
     * @return A list with broken rules.
     */
    public HashMap<ErrorType, String> getErrorList() {
        return ruleSet.getErrorList();
    }
}
