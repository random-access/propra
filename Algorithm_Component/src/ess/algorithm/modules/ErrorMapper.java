package ess.algorithm.modules;

import java.util.HashMap;
import java.util.LinkedList;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.rules.ErrorType;
import ess.rules.sets.IRuleSet;

public class ErrorMapper {

	// prevents instantiation
	private ErrorMapper() {}
	
	private static HashMap<ErrorType, Validation> errorMap = new HashMap<>();
	
	static {
		errorMap.put(ErrorType.MAX_LINE_LENGTH, Validation.MAX_FUGENLAENGE);
		errorMap.put(ErrorType.CROSSINGS, Validation.FUGENKREUZE);
		errorMap.put(ErrorType.REPLACEABLE_TILE, Validation.FLIESEN_AUSTAUSCHBAR);
		errorMap.put(ErrorType.SAME_TILE, Validation.GLEICHE_FLIESEN);
		errorMap.put(ErrorType.OTHER, Validation.FLIESE_UNPASSEND);
	}
	
	public static LinkedList<Validation> mapErrors(IRuleSet ruleSet) {
		LinkedList<Validation> errorList = new LinkedList<>();
		for (ErrorType errorType : ruleSet.getErrorList()) {
			errorList.add(errorMap.get(errorType));
		}
		return errorList;
	}

}
