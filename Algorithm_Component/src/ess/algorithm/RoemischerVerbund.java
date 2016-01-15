package ess.algorithm;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import ess.algorithm.modules.ErrorMapper;
import ess.data.Composite;
import ess.exc.InvalidLengthValueException;
import ess.exc.PropertyException;
import ess.io.IDataExchanger;
import ess.io.XMLDataExchanger;
import ess.io.XMLValues;
import ess.io.exc.DataExchangeException;
import ess.strings.CustomErrorMessages;

/**
 * Diese Klasse wird als API (Application Programming Interface) verwendet. Das
 * bedeutet, dass diese Klasse als Bibliothek fuer andere Applikationen verwendet
 * werden kann.
 * 
 * Bitte achten Sie darauf, am bereits implementierten Rahmen (Klassenname,
 * Package, Methodensignaturen) !!KEINE!! Veraenderungen vorzunehmen.
 * Selbstverstaendlich koennen und muessen Sie innerhalb einer Methode Aenderungen
 * vornehmen.
 */
public class RoemischerVerbund extends AbstractOutputObservable implements IRoemischerVerbund, DisplayableWithoutCheck {

    private static final Logger LOG = Logger.getGlobal();
    
    private List<Validation> errorList;
    private boolean hasValidSolution;
    private Composite composite;
    private String pathToXml;

    /**
     * Fehlertypen, die bei der Validierung auftreten koennen.
     */
    public enum Validation {
        /**
         * Fliesenkombination kann durch eine groessere Fliese ersetzt werden.
         */
        FLIESEN_AUSTAUSCHBAR,

        /**
         * Zwei gleiche Fliesen liegen nebeneinander.
         */
        GLEICHE_FLIESEN,

        /**
         * Die maximale Fugenlaenge wurde ueberschritten.
         */
        MAX_FUGENLAENGE,

        /**
         * Es bilden sich Fugenkreuze.
         */
        FUGENKREUZE,

        /**
         * Sonstige Fehler sind aufgetreten.
         */
        FLIESE_UNPASSEND
    }

    /**
     * Ueberprueft die eingegebene Loesung auf Korrektheit.
     *
     * @param xmlFile Dokument, das validiert werden soll.
     * @param maxFugenlaenge Die maximale Fugenlaenge.
     * @return Liste von Fehlern, die fehlgeschlagen sind.
     */
    @Override
    public List<Validation> validateSolution(String xmlFile, int maxFugenlaenge) {
        this.pathToXml = xmlFile;
        try {
            IDataExchanger dataExchanger = new XMLDataExchanger();
            composite = dataExchanger.readFromSource(xmlFile);
            convertGapLength(maxFugenlaenge, composite);
            Validator validator = new Validator(composite);
            errorList = validator.validateSolution();
            hasValidSolution = errorList.size() == 0;
            sendNotificationToOutputObservers();
        } catch (DataExchangeException | PropertyException | InvalidLengthValueException e) {
            System.out.println(e.getMessage());
            errorList = addAllErrorsToErrorList();
        }
        return errorList;
    }

    /**
     * Ermittelt eine Loesung zu den eingegebenen Daten.
     *
     * @param xmlFile
     *            Eingabedokument, das die Probleminstanzen enthaelt.
     * @param maxLineLength
     *            maximale Fugenlaenge der zu berechnenden Loesung.
     * @return konnte eine Loesung gefunden werden? true = ja, false = nein.
     */
    @Override
    public boolean solve(String xmlFile, int maxLineLength) {
        this.pathToXml = xmlFile;
        try {
            IDataExchanger dataExchanger = new XMLDataExchanger();
            composite = dataExchanger.readFromSource(xmlFile);
            convertGapLength(maxLineLength, composite);
            ISolver solver = new Solver(composite);
            hasValidSolution = solver.solve();
            if (hasValidSolution) {
                dataExchanger.writeToTarget(composite, xmlFile);                
            } 
            sendNotificationToOutputObservers();
        } catch (DataExchangeException | PropertyException | InvalidLengthValueException e) {
            System.out.println(e.getMessage());
            hasValidSolution = false;
        }
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
            sendNotificationToOutputObservers();
        } catch (DataExchangeException | PropertyException e) {
            System.out.println(e.getMessage());
        }
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
    public List<String> getErrors() {
        if (errorList == null) {
            return new LinkedList<>();
        }
        return ErrorMapper.mapErrorsForUi(errorList);
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
        LOG.info("Sending output request...");
        setChanged();
        notifyObservers();
    }
    
    // converts the gap lenght into internal measurments
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
