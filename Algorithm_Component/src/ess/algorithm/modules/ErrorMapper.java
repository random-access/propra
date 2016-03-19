package ess.algorithm.modules;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.rules.ErrorType;
import ess.strings.CustomInfoMessages;

/**
 * This class converts different representations of error messages.
 * 
 * @author Monika Schrenk
 *
 */
public final class ErrorMapper {

    // prevents instantiation
    private ErrorMapper() {
    }

    private static HashMap<ErrorType, Validation> rulesComponentMap = new HashMap<>();

    static {
        rulesComponentMap.put(ErrorType.MAX_LINE_LENGTH, Validation.MAX_FUGENLAENGE);
        rulesComponentMap.put(ErrorType.CROSSINGS, Validation.FUGENKREUZE);
        rulesComponentMap.put(ErrorType.REPLACEABLE_TILE, Validation.FLIESEN_AUSTAUSCHBAR);
        rulesComponentMap.put(ErrorType.SAME_TILE, Validation.GLEICHE_FLIESEN);
        rulesComponentMap.put(ErrorType.NOT_ALL_TILES_USED, Validation.NICHT_GENUTZTE_FLIESE);
        rulesComponentMap.put(ErrorType.OTHER, Validation.FLIESE_UNPASSEND);
    }

//    private static HashMap<Validation, String> uiMap = new HashMap<>();
//
//    static {
//        uiMap.put(Validation.MAX_FUGENLAENGE, CustomInfoMessages.INFO_MAX_LINELENGTH);
//        uiMap.put(Validation.FLIESE_UNPASSEND, CustomInfoMessages.INFO_OTHER);
//        uiMap.put(Validation.FLIESEN_AUSTAUSCHBAR, CustomInfoMessages.INFO_REPLACEABLE_TILE);
//        uiMap.put(Validation.FUGENKREUZE, CustomInfoMessages.INFO_CROSSING);
//        uiMap.put(Validation.NICHT_GENUTZTE_FLIESE, CustomInfoMessages.INFO_UNUSED_TILES);
//        uiMap.put(Validation.GLEICHE_FLIESEN, CustomInfoMessages.INFO_SAME_TILE);
//    }

    private static HashMap<ErrorType, String> uiMapV2 = new HashMap<>();

    static {
        uiMapV2.put(ErrorType.MAX_LINE_LENGTH, CustomInfoMessages.INFO_MAX_LINELENGTH);
        uiMapV2.put(ErrorType.OTHER, CustomInfoMessages.INFO_OTHER);
        uiMapV2.put(ErrorType.REPLACEABLE_TILE, CustomInfoMessages.INFO_REPLACEABLE_TILE);
        uiMapV2.put(ErrorType.CROSSINGS, CustomInfoMessages.INFO_CROSSING);
        uiMapV2.put(ErrorType.NOT_ALL_TILES_USED, CustomInfoMessages.INFO_UNUSED_TILES);
        uiMapV2.put(ErrorType.SAME_TILE, CustomInfoMessages.INFO_SAME_TILE);
    }

    /**
     * Converts the error types of an <code>IRuleSet</code> after validation to
     * a List with validation entries, which are needed as return type of
     * {@link ess.algorithm.RoemischerVerbund#validateSolution(String xmlFile, int maxFugenlaenge)}
     * 
     * @param ruleSet
     *            a set of IRules
     * @return a list of Validation entries
     */
    public static LinkedList<Validation> mapErrorsForAlgoComponent(HashMap<ErrorType, String> errorList) {
        LinkedList<Validation> validationList = new LinkedList<>();
        for (ErrorType errorType : errorList.keySet()) {
            validationList.add(rulesComponentMap.get(errorType));
        }
        return validationList;
    }

    /**
     * Maps user info messages to the Validation entries of errorList and
     * returns them as a list. The info messages can be used for output via UI
     * or command line.
     * 
     * @param errorList
     *            a list of Validation entries
     * @return a list of Strings with user info messages
     */
//    public static List<String> mapErrorsForUi(List<Validation> errorList) {
//        LinkedList<String> errorMsgList = new LinkedList<>();
//        for (Validation errorType : errorList) {
//            errorMsgList.add(uiMap.get(errorType));
//        }
//        return errorMsgList;
//    }

    /**
     * Maps user info messages to error list entries, adds any additional
     * information and returns result as a list of Strings. The info messages
     * can be used for output via UI or command line.
     * 
     * @param errorList
     *            a list of Validation entries
     * @return a list of Strings with user info messages
     */
    public static List<String> mapErrorsForUiV2(HashMap<ErrorType, String> errorList) {
        LinkedList<String> errorMsgList = new LinkedList<>();
        for (ErrorType errorType : errorList.keySet()) {
            errorMsgList.add(uiMapV2.get(errorType) + " " + errorList.get(errorType));
        }
        return errorMsgList;
    }
}
