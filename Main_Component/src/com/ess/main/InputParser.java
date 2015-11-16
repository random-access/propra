package com.ess.main;

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
				throw new InvalidInputException("Line length cannot be parsed");
			}
		} else {
			throw new InvalidInputException();
		}
	}

	private String parsePath(String pathArg) throws InvalidInputException {
		if (pathArg.startsWith("if=")) {
			return pathArg.replace("if=", "");
		} else {
			throw new InvalidInputException("Path cannot be parsed");
		}
	}
	
	private ExecMode parseMode(String modeArg) throws InvalidInputException {
		if (modeArg.startsWith("r=")) {
			String mode = modeArg.replace("r=", "");
			return ExecMode.getExecModeByShortcut(mode);
		} else {
			throw new InvalidInputException("Mode cannot be parsed");
		}
	}
	
	private void checkParamSize(String[] args) throws InvalidInputException {
		if (args.length != 3) {
			throw new InvalidInputException("Wrong param size");
		}
	}
	
}
