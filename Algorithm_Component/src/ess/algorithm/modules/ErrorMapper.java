package ess.algorithm.modules;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ess.algorithm.RoemischerVerbund;
import ess.algorithm.RoemischerVerbund.Validation;
import ess.rules.ErrorType;
import ess.rules.sets.IRuleSet;
import ess.strings.CustomInfoMessages;

/**
 * This class converts different representations of error messages.
 * 
 * @author Monika Schrenk
 *
 */
public final class ErrorMapper {

	// prevents instantiation
	private ErrorMapper() { }
	
	private static HashMap<ErrorType, Validation> rulesComponentMap = new HashMap<>();
	
	static {
		rulesComponentMap.put(ErrorType.MAX_LINE_LENGTH, Validation.MAX_FUGENLAENGE);
		rulesComponentMap.put(ErrorType.CROSSINGS, Validation.FUGENKREUZE);
		rulesComponentMap.put(ErrorType.REPLACEABLE_TILE, Validation.FLIESEN_AUSTAUSCHBAR);
		rulesComponentMap.put(ErrorType.SAME_TILE, Validation.GLEICHE_FLIESEN);
		rulesComponentMap.put(ErrorType.OTHER, Validation.FLIESE_UNPASSEND);
	}
	
	private static HashMap<Validation, String> uiMap = new HashMap<>();
	
	static {
	    uiMap.put(Validation.MAX_FUGENLAENGE, CustomInfoMessages.INFO_MAX_LINELENGTH);
	    uiMap.put(Validation.FLIESE_UNPASSEND, CustomInfoMessages.INFO_OTHER);
	    uiMap.put(Validation.FLIESEN_AUSTAUSCHBAR, CustomInfoMessages.INFO_REPLACEABLE_TILE);
	    uiMap.put(Validation.FUGENKREUZE, CustomInfoMessages.INFO_CROSSING);
	    uiMap.put(Validation.GLEICHE_FLIESEN, CustomInfoMessages.INFO_SAME_TILE);
	}
	
	/**
	 * Converts the error types of an IRuleSet that are returned from RulesComponent after validation
	 * to a List with validation entries, which are needed as return type of 
	 * {@link ess.algorithm.RoemischerVerbund#validateSolution(String xmlFile, int maxFugenlaenge)}
	 * @param ruleSet a set of IRules
	 * @return a list of Validation entries
	 */
	public static LinkedList<Validation> mapErrorsForAlgoComponent(IRuleSet ruleSet) {
		LinkedList<Validation> errorList = new LinkedList<>();
		for (ErrorType errorType : ruleSet.getErrorList()) {
			errorList.add(rulesComponentMap.get(errorType));
		}
		return errorList;
	}
	
	/**
	 * Maps user info messages to the Validation entries of errorList and returns them
	 * as a list. The info messages can be used for output via UI or command line.
	 * @param errorList a list of Validation entries
	 * @return a list of Strings with user info messages
	 */
	public static List<String> mapErrorsForUi(List<Validation> errorList) {
	    LinkedList<String> errorMsgList = new LinkedList<>();
        for (Validation errorType : errorList) {
            errorMsgList.add(uiMap.get(errorType));
        }
        return errorMsgList;
	}

}
