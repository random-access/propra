package com.ess.main;

import ess.exc.InvalidInputException;
import ess.strings.CustomErrorMessages;
import ess.strings.CustomInfoMessages;

/**
 * This class is responsible for parsing all parameters the user 
 * entered when starting the application via terminal.
 * 
 * @author monika
 *
 */
public class InputParser {
	
	private ExecMode mode;
	private String path;
	private int maxTileLength;
	
	// number of parameters
	private static final int NO_OF_PARAMS = 3;

	/**
	 * Constructs an <code>InputParser</code>, passing in the parameters.
	 * @param args Parameters the user entered.
	 * @throws InvalidInputException if a parameter is considered invalid.
	 */
	public InputParser(String[] args) throws InvalidInputException {
		checkParamSize(args);
		mode = parseMode(args[0]);
		path = parsePath(args[1]);
		maxTileLength = parseLineLength(args[2]);
	}
	
	/**
	 * Returns the <code>ExecMode<code> entered by the user.
	 * @return the execution mode.
	 */
	public ExecMode getMode() {
		return mode;
	}
	
	/**
	 * Returns the path to a source holding infos about a Composite.
	 * @return path to a source of a composite.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Returns the maximum allowed length of a straight line in the composite
	 * entered by the user. This length is still in external measurements.
	 * @return Maximum tile length.
	 */
	public int getMaxTileLength() {
		return maxTileLength;
	}

	// removes the execution key and converts the remaining String to an int.
	private int parseLineLength(String lineArg) throws InvalidInputException {
		if (lineArg.startsWith("l=")) {
			try {
				return Integer.parseInt(lineArg.replace("l=", ""));
			} catch (NumberFormatException e) {
				throw new InvalidInputException(CustomErrorMessages.ERROR_INVALID_LENGTH);
			}
		} else {
			throw new InvalidInputException(CustomErrorMessages.ERROR_INVALID_PARAM);
		}
	}

	// removes the path key and returns the remaining String.
	private String parsePath(String pathArg) throws InvalidInputException {
		if (pathArg.startsWith("if=")) {
			return pathArg.replace("if=", "");
		} else {
			throw new InvalidInputException(CustomErrorMessages.ERROR_INVALID_PARAM);
		}
	}
	
	// removes the execution key and maps the remaining String to an ExecMode
	private ExecMode parseMode(String modeArg) throws InvalidInputException {
		if (modeArg.startsWith("r=")) {
			String m = modeArg.replace("r=", "");
			return ExecMode.getExecModeByShortcut(m);
		} else {
			throw new InvalidInputException(CustomErrorMessages.ERROR_INVALID_PARAM);
		}
	}
	
	// Checks if the user entered exactly 3 parameters.
	// Assumes that if no parameters were entered, the user needs more detailed information
	// about usage than in other cases.
	private void checkParamSize(String[] args) throws InvalidInputException {
		if (args.length == 0) {
			throw new InvalidInputException(CustomInfoMessages.INFO_USAGE);
		}
		if (args.length != NO_OF_PARAMS) {
			throw new InvalidInputException(CustomErrorMessages.ERROR_INVALID_PARAM_COUNT);
		}
	}
	
}
