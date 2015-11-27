package com.ess.main;

import ess.strings.CustomErrorMessages;
import ess.strings.CustomInfoMessages;

public class InputParser {
	
	private ExecMode mode;
	private String path;
	private int maxTileLength;

	public InputParser(String[] args) throws InvalidInputException {
		checkParamSize(args);
		mode = parseMode(args[0]);
		path = parsePath(args[1]);
		maxTileLength = parseLineLength(args[2]);
	}
	
	public ExecMode getMode() {
		return mode;
	}
	
	public String getPath() {
		return path;
	}

	public int getMaxTileLength() {
		return maxTileLength;
	}

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

	private String parsePath(String pathArg) throws InvalidInputException {
		if (pathArg.startsWith("if=")) {
			return pathArg.replace("if=", "");
		} else {
			throw new InvalidInputException(CustomErrorMessages.ERROR_INVALID_PARAM);
		}
	}
	
	private ExecMode parseMode(String modeArg) throws InvalidInputException {
		if (modeArg.startsWith("r=")) {
			String m = modeArg.replace("r=", "");
			return ExecMode.getExecModeByShortcut(m);
		} else {
			throw new InvalidInputException(CustomErrorMessages.ERROR_INVALID_PARAM);
		}
	}
	
	private void checkParamSize(String[] args) throws InvalidInputException {
		if (args.length == 0) {
			throw new InvalidInputException(CustomInfoMessages.INFO_USAGE);
		}
		if (args.length != 3) {
			throw new InvalidInputException(CustomErrorMessages.ERRO_INVALID_PARAM_COUNT);
		}
	}
	
}
