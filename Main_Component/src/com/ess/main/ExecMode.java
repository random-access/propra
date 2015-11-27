package com.ess.main;

import ess.strings.CustomErrorMessages;

public enum ExecMode {
	
	SOLVE, SOLVE_DISPLAY, VALIDATE, VALIDATE_DISPLAY, DISPLAY;
	
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