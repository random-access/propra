package ess.algorithm;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import ess.data.Composite;
import ess.exc.InvalidLengthValueException;
import ess.exc.PropertyException;
import ess.io.IDataExchanger;
import ess.io.XMLDataExchanger;
import ess.io.XMLValues;
import ess.io.exc.DataExchangeException;
import ess.strings.CustomErrorMessages;
import ess.utils.CustomLogger;

/**
 * This class is used as API (Application Programming Interface). This means
 * that it can be used as a library for other applications. 
 */
public class RoemischerVerbund extends AbstractOutputObservable implements IRoemischerVerbund, DisplayableWithoutCheck {
    
    private final Logger logger = CustomLogger.getLogger();
    
    private String errorMessage;
    private List<Validation> errorList;
    private List<String> errorMessages;
    private boolean hasValidSolution;
    private Composite composite;
    private String pathToXml;

    /**
     * Error types found during validation.
     */
    public enum Validation {
        /**
         * A combination of tiles can be replaced with a larger tile.
         */
        FLIESEN_AUSTAUSCHBAR,

        /**
         * Two equal tiles lie exactly beside each other.
         */
        GLEICHE_FLIESEN,

        /**
         * The maximum gap length has been exceeded.
         */
        MAX_FUGENLAENGE,

        /**
         * Crossings have been found. 
         */
        FUGENKREUZE,
        
        /**
         * Not all tiles are used
         */
        NICHT_GENUTZTE_FLIESE,

        /**
         * Other errors have occurred.
         */
        FLIESE_UNPASSEND
    }

    /**
     * Tests if a given solution is correct.
     *
     * @param xmlFile path to the document that needs to be validated.
     * @param maxGapLength maximum gap length that is allowed
     * @return a list of broken rules
     */
    @Override
    public List<Validation> validateSolution(String xmlFile, int maxGapLength) {
        this.pathToXml = xmlFile;
        try {
            IDataExchanger dataExchanger = new XMLDataExchanger();
            composite = dataExchanger.readFromSource(xmlFile);
            convertGapLength(maxGapLength, composite);
            Validator validator = new Validator(composite);
            errorList = validator.validateSolution();
            errorMessages = validator.getErrorMessages();
            hasValidSolution = errorList.size() == 0;
        } catch (DataExchangeException | PropertyException | InvalidLengthValueException e) {
            errorMessage = e.getMessage();
            errorList = addAllErrorsToErrorList();
        }
        sendNotificationToOutputObservers();
        return errorList;
    }

    /**
     * Calculates a solution for the given data.
     *
     * @param xmlFile document holding the input data.
     * @param maxGapLength maximum gap length that is allowed
     * @return <code>true</code>, if a solution could be found, else <code>false</code>. 
     */
    @Override
    public boolean solve(String xmlFile, int maxGapLength) {
        this.pathToXml = xmlFile;
        try {
            IDataExchanger dataExchanger = new XMLDataExchanger();
            composite = dataExchanger.readFromSource(xmlFile);
            convertGapLength(maxGapLength, composite);
            ISolver solver = new Solver(composite);
            hasValidSolution = solver.solve();
            if (hasValidSolution) {
                dataExchanger.writeToTarget(composite, xmlFile);                
            } 
        } catch (DataExchangeException | PropertyException | InvalidLengthValueException e) {
            errorMessage = e.getMessage();
            hasValidSolution = false;
        }
        sendNotificationToOutputObservers();
        return hasValidSolution;
    }
    
    @Override
    public void display(String xmlFile) {
        this.pathToXml = xmlFile;
        try {
            IDataExchanger dataExchanger = new XMLDataExchanger();
            composite = dataExchanger.readFromSource(xmlFile);
            IDisplayer displayer = new Displayer(composite);
            displayer.constructOutput();
            hasValidSolution = true;
        } catch (DataExchangeException | PropertyException e) {
            errorMessage = e.getMessage();
        }
        sendNotificationToOutputObservers();
    }
    
    /* (non-Javadoc)
     * @see ess.algorithm.AbstractOutputObservable#getErrorMessage()
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    /* (non-Javadoc)
     * @see ess.algorithm.AbstractOutputObservable#getComposite()
     */
    @Override
    public Composite getComposite() {
        return composite;
    }

    /* (non-Javadoc)
     * @see ess.algorithm.AbstractOutputObservable#getErrors()
     */
    @Override
    public List<String> getErrorList() {
        return (errorMessages == null)? new LinkedList<String>() : errorMessages;
    }

    /* (non-Javadoc)
     * @see ess.algorithm.AbstractOutputObservable#getPathToSource()
     */
    @Override
    public String getPathToSource() {
        return pathToXml;
    }
    
    // adds all errors to a list and returns that list
    // necessary if validation could not even be started - in this
    // case, all errors must be returned
    private List<Validation> addAllErrorsToErrorList() {
        List<Validation> listWithAllErrors = new LinkedList<>();
        listWithAllErrors.addAll(EnumSet.allOf(Validation.class));
        return listWithAllErrors;
    }

    // notify all registered observers that the algorithm has
    // finished and the output can be displayed
    private void sendNotificationToOutputObservers() {
        logger.info("Sending output request...");
        setChanged();
        notifyObservers();
    }
    
    // converts the gap length into internal measurements
    // throws InvalidLengthValueException if maxGapLength is negative
    private void convertGapLength(int maxGapLength, Composite c) throws InvalidLengthValueException {
        if (maxGapLength < 0) {
            throw new InvalidLengthValueException(CustomErrorMessages.ERROR_INVALID_LENGTH);
        }
        c.setMaxLineLength(maxGapLength / XMLValues.CONVERSION_UNIT);
    }

    @Override
    public boolean hasValidComposite() {
        return hasValidSolution;
    }

}
