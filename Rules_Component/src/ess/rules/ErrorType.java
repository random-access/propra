package ess.rules;

/**
 * This Enum holds values for each error type that can occur during
 * checking of rules.
 * 
 * @author Monika Schrenk
 *
 */
public enum ErrorType {
	/**
	 * ErrorType of MaxLineLengthRule. 
	 */
	MAX_LINE_LENGTH, 
	
	/**
	 * ErrorType of CrossingsRule.
	 */
	CROSSINGS, 
	
	/**
	 * ErrorType of ReplaceableTileRule.
	 */
	REPLACEABLE_TILE, 
	
	/**
	 * ErrorType of SameTileRule.
	 */
	SAME_TILE, 
	
	/**
	 * ErrorType of UsedAllTilesRule
	 */
	NOT_ALL_TILES_USED,
	
	/**
	 * ErrorType of all other rules.
	 */
	OTHER
}
