package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;

/**
 * This interface should be implemented by all classes that are responsible 
 * for checking a <code>Composite</code> against an <code>IRuleSet</code>.
 * 
 * @author Monika Schrenk
 */
public interface IRuleChecker {
	
    /**
     * Performs a check against the explicit rules of an <code>IRuleSet</code>.
     * @param composite The Composite that holds the Surface where the next Tile should be placed.
     * @param tile The Tile that will be placed into composite.
     * @param pos The Position where the tile will be placed (left upper corner).
     * @return <code>true</code>, if no explicit rule was broken, else <code>false</code>.
     */
	boolean checkExplicitRules(Composite composite, Tile tile, Position pos);
	
	/**
     * Performs a check against the implicit rules of an <code>IRuleSet</code>.
     * @param c The composite that holds the surface where the next tile should be placed.
     * @param tile The tile that will be placed into the composite.
     * @param pos The position where tile will be placed (left upper corner)
     * @return <code>true</code>, if no implicit rule was broken, else <code>false</code>.
     */
	boolean checkImplicitRules(Composite c, Tile tile, Position pos);
	
	/**
     * Performs a check against the end conditions of an <code>IRuleSet</code>.
     * @param c The composite that holds the surface where the next tile should be placed.
     * @param tile The tile that will be placed into the composite 
     * (not used in the check anyway)
     * @param pos The next free position
     * @return <code>true</code>, if no end condition was broken, else <code>false</code>.
     */
	boolean checkEndConditions(Composite c, Tile tile, Position pos);
}
