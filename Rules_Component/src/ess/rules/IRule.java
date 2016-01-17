package ess.rules;

import ess.data.Position;
import ess.data.Tile;

/**
 * This interface must be implemented by all rules that get checked during solving
 * or validating a composite.
 * It provides methods for checking a composite against an instance of IRule 
 * and for getting the error type if the rule got broken.
 * 
 * @author Monika Schrenk
 *
 */
public interface IRule {
	
    /**
     * Returns this rule's <code>ErrorType</code>, should be called to store which rule got broken during
     * validation.
     * @return this rule's ErrorType.
     */
	ErrorType getErrorType();
	
	/**
	 * Checks if this rule is broken when tile will be placed into the Composite's surface.
	 * Rules are checked before the tile is placed, so implementations must deal with 
	 * the tile not being placed yet. 
	 * @param tile The tile that is considered to be placed next.
	 * @param pos The left upper corner where tile should be placed.
	 * @return True, if the this rule doesn't get broken when placing tile at pos, else false.
	 */
	boolean check(Tile tile, Position pos);
}
