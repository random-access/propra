package ess.rules.sets;

import java.util.LinkedList;

import ess.rules.ErrorType;
import ess.rules.IRule;

/**
 * This interface should be implemented by classes putting together a collection of rules.
 * 
 * A RuleSet can return 3 sorts of rules that are stored in different lists:
 * <ol>
 *  <li>{@link #getExplicitRules()}, a list of explicit rules for checking the conditions B1-B4</li>
 *  <li>{@link #getImplicitRules()}, a list of implicit rules for checking if a tile can be placed at a certain position 
 *  without invalidating the whole composite</li>
 *  <li>{@link #getEndConditions()}, a list of end conditions for checking if the surface is filled completely with tiles</li>
 * </ol>
 * @author monika
 *
 */
public interface IRuleSet {
	
    /**
     * Returns a set of rules to check the conditions B1-B4
     * @return A list of explicit rules to be checked.
     */
	LinkedList<IRule> getExplicitRules();
	
    /**
     * Returns a set of rules to check if a tile can be placed at a certain position without invalidating the whole composite.
     * @return A list of implicit rules to be checked.
     */
	LinkedList<IRule> getImplicitRules();
	
    /**
     * Returns a set rules to check if the surface is filled completely with tiles.
     * @return A list of end conditions to be checked.
     */
	LinkedList<IRule> getEndConditions();
	
	/**
	 * Returns a list of errors that occurred during validation.
	 * @return List of rules that were broken.
	 */
	LinkedList<ErrorType> getErrorList();

	/**
	 * Add an error to this RuleSet's errorList
	 * @param errorType The ErrorType belonging to the rule that was broken.
	 */
	void addError(ErrorType errorType);

}
