package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;

/**
 * This interface should be implemented by all classes that are responsible 
 * for checking the composite against a RuleSet.
 * 
 * @author Monika Schrenk
 */
public interface IRuleChecker {
	
    /**
     * Performs a check against the explicit rules of a RuleSet.
     * @param c The composite that holds the surface where the next tile should be placed.
     * @param tile The tile that will be placed into the composite.
     * @param pos The position where tile will be placed (left upper corner)
     * @return True, if no explicit rule was broken, else false.
     */
	public boolean checkExplicitRules(Composite c, Tile tile, Position pos);
	
	/**
     * Performs a check against the implicit rules of a RuleSet.
     * @param c The composite that holds the surface where the next tile should be placed.
     * @param tile The tile that will be placed into the composite.
     * @param pos The position where tile will be placed (left upper corner)
     * @return True, if no implicit rule was broken, else false.
     */
	public boolean checkImplicitRules(Composite c, Tile tile, Position pos);
	
	/**
     * Performs a check against the end conditions of a RuleSet.
     * @param c The composite that holds the surface where the next tile should be placed.
     * @param tile The tile that will be placed into the composite 
     * (not used in the check anyway)
     * @param pos The next free position
     * @return True, if no end condition was broken, else false.
     */
	public boolean checkEndConditions(Composite c, Tile tile, Position pos);
}
