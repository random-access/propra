package com.ess.main;

import ess.exc.InvalidInputException;
import ess.strings.CustomErrorMessages;

/**
 * This enum provides different values for the execution modes entered by the user via command line.
 * 
 * @author Monika Schrenk
 */
public enum ExecMode {
	/**
	 * Solve mode
	 */
	SOLVE, 
	
	/**
	 * Solve & display mode
	 */
	SOLVE_DISPLAY, 
	
	/**
	 * Validation mode
	 */
	VALIDATE, 
	
	/**
	 * Validation & display mode
	 */
	VALIDATE_DISPLAY, 
	
	/**
	 * Display mode
	 */
	DISPLAY;
	
	/**
	 * Returns the corresponding execution mode value when called with a string that
	 * stores the user input. <br>
	 * If the user input isn't valid, this method throws an InvalidInputException holding
	 * an info message that can be displayed.
	 * @param shortcut User input for parameter r=x ("r=" must be already removed).
	 * @return The corresponding ExecMode.
	 * @throws InvalidInputException if user input is invalid.
	 */
	public static ExecMode getExecModeByShortcut(String shortcut) throws InvalidInputException {
		switch (shortcut) {
		case "s":
			return SOLVE;
		case "sd": 
			return SOLVE_DISPLAY;
		case "v":
			return VALIDATE;
		case "vd":
			return VALIDATE_DISPLAY;
		case "d":
			return DISPLAY;
			default:
				throw new InvalidInputException(CustomErrorMessages.ERROR_INVALID_EXEC_MODE);
		}
	}
}