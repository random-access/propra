package com.ess.main;

import java.util.logging.Logger;

import ess.algorithm.AbstractOutputObservable;
import ess.algorithm.RoemischerVerbund;
import ess.exc.InvalidInputException;
import ess.strings.CustomErrorMessages;
import ess.utils.CustomLogger;

/**
 * Main entry point of the application, parses the user input.
 * 
 * The parameter r can hold any of the following values: 
 * <ul>
 *		<li>"s" (solve): the application tries to construct a Roman Composite for the given data if possible,
 *                  otherwise the application exits with an error message. If a solution could be found, it 
 *                  is saved in the given XML file. If the XML file already contains a solution, it will be
 *                  overwritten.</li>
 *		<li>"sd" (solve & display): like "s". Additionally, if a solution can be found, the Composite gets 
 *                  displayed in a graphical user interface.</li>
 *		<li>"v" (validate): the application validates data from the given source, checking it against the rules 
 *                  B1 - B4 (if activated) and displays every broken rule at the command line. If the input 
 *                  doesn't contain data, an error message gets displayed.</li>
 *		<li>"vd" (validate & display): like "v". Additionally, if the data contains a valid Composite, it gets
 *                  displayed in a graphical user interface.</li>
 *		<li>"d" (display): the application displays the Composite from the given input source in a graphical user
 *                  interface.  
 *                  If the input doesn't contain data, an error message gets displayed.</li>
 * </ul>
 * The input parameter if (Input File) is a String, which contains the path to an input source. The parameter for
 * the maximum gap length l must be a positive integer, which represents the gap length in cm. 
 * <br><br>
 * Example Parameter Call: r=s if="bin\\verbund1.xml" l=1200
 */
public final class Main {

    private static final Logger LOGGER = CustomLogger.getLogger();

    // prevents instantiation
    private Main() {
    }

    /**
     * This is the main method that starts the application.
     * 
     * @param args A String array of length = 3
     */
    public static void main(String[] args) {
        try {
            InputParser inputParser = new InputParser(args);
            RoemischerVerbund v = new RoemischerVerbund();
            
            LOGGER.info("Setting path to " + inputParser.getPath() + "...");
            LOGGER.info("Setting max. tile length to " + inputParser.getMaxTileLength() + "...");
            LOGGER.info("Setting mode to " + inputParser.getMode() + "...");

            initDisplay(inputParser.getMode(), (AbstractOutputObservable) v);
            
            executeAlgorithm(v, inputParser);
            
        // Output all expected Exceptions in a human-readable, textual representation.
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    
    // this method initializes the observers that later get calls to display the output
    private static void initDisplay(ExecMode mode, AbstractOutputObservable obs) {
        switch(mode) {
            case SOLVE:
            case VALIDATE:
                new HeadlessObserver().observe(obs, mode);
                break;
            case SOLVE_DISPLAY:
            case VALIDATE_DISPLAY:
            case DISPLAY:
                new DisplayObserver().observe(obs, mode);
                break;
            default:
                System.out.println(CustomErrorMessages.UNSUPPORTED_MODE);
        }
    }
    
    // this method decides which method call is necessary to execute the requested algorithm
    private static void executeAlgorithm(RoemischerVerbund v, InputParser inputParser) {
        switch(inputParser.getMode()) {
            case SOLVE:
            case SOLVE_DISPLAY:
                v.solve(inputParser.getPath(), inputParser.getMaxTileLength());
                break;
            case VALIDATE:
            case VALIDATE_DISPLAY:
                v.validateSolution(inputParser.getPath(), inputParser.getMaxTileLength());
                break;
            case DISPLAY:
                v.display(inputParser.getPath());
                break;
            default:
                System.out.println(CustomErrorMessages.UNSUPPORTED_MODE);
        }
    }

}
