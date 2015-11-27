package ess.algorithm;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import ess.data.Composite;
import ess.io.IDataExchanger;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.strings.CustomErrorMessages;
import ess.strings.CustomInfoMessages;
import ess.utils.PropertyException;

// TODO: Auto-generated Javadoc
/**
 * Diese Klasse wird als API (Application Programming Interface) verwendet. Das
 * bedeutet, dass diese Klasse als Bibliothek für andere Applikationen verwendet
 * werden kann.
 * 
 * Bitte achten Sie darauf, am bereits implementierten Rahmen (Klassenname,
 * Package, Methodensignaturen) !!KEINE!! Veränderungen vorzunehmen.
 * Selbstverständlich können und müssen Sie innerhalb einer Methode Änderungen
 * vornehmen.
 * 
 * @author Monika Schrenk
 */
public class RoemischerVerbund extends AbstractOutputObservable implements IRoemischerVerbund {

    private Logger log = Logger.getGlobal();

    private List<Validation> errorList;
    private Composite composite;

    /**
     * Fehlertypen, die bei der Validierung auftreten koennen.
     */
    public enum Validation {
        /**
         * Flieskombination kann durch eine größere Fliese ersetzt werden.
         */
        FLIESEN_AUSTAUSCHBAR, 
        
        /**
         * Zwei gleiche Fliesen liegen nebeneinander.
         */
        GLEICHE_FLIESEN, 
        
        /**
         * Die maximale Fugenlänge wurde überschritten.
         */
        MAX_FUGENLAENGE, 
        
        /**
         * Es bilden sich Fugenkreuze.
         */
        FUGENKREUZE,
        
        /**
         * Sonstige Fehler sind aufgetreten.
         */
        FLIESE_UNPASSEND;
    }

    /**
     * Überprüft die eingegebene Lösung auf Korrektheit.
     *
     * @param xmlFile
     *            Dokument, das validiert werden soll.
     * @param maxFugenLaenge
     *            maximale Fugenlänge der zu berechnenden Lösung.
     * @return Liste von Fehlern, die fehlgeschlagen sind.
     */
    @Override
    public List<Validation> validateSolution(String xmlFile, int maxLineLength) {
        try {
            IDataExchanger dataExchanger = new XMLDataExchanger();
            composite = dataExchanger.readFromSource(xmlFile);
            Validator validator = new Validator(composite, maxLineLength);
            errorList = validator.validateSolution();
            sendNotificationToOutputObservers();
            return errorList;
        } catch (DataExchangeException | PropertyException e) {
            System.out.println(e.getCause().getMessage());
            return addAllErrorsToErrorList();
        }
    }

    /**
     * Ermittelt eine Lösung zu den eingegebenen Daten.
     *
     * @param xmlFile
     *            Eingabedokument, das die Probleminstanzen enthält.
     * @param maxLineLength
     *            maximale Fugenlänge der zu berechnenden Lösung.
     * @return konnte eine Lösung gefunden werden? true = ja, false = nein.
     */
    @Override
    public boolean solve(String xmlFile, int maxLineLength) {
        try {
            IDataExchanger dataExchanger = new XMLDataExchanger();
            composite = dataExchanger.readFromSource(xmlFile);
            ISolver solver = new Solver(composite, maxLineLength);
            boolean solved = solver.solve();
            if (solved) {
                dataExchanger.writeToTarget(composite, xmlFile);
                sendNotificationToOutputObservers();
            }
            return solved;
        } catch (DataExchangeException | PropertyException e) {
            System.out.println(e.getCause().getMessage());
            return false;
        }
    }

    private List<Validation> addAllErrorsToErrorList() {
        List<Validation> listWithAllErrors = new LinkedList<>();
        listWithAllErrors.addAll(EnumSet.allOf(Validation.class));
        return listWithAllErrors;
    }

    private void sendNotificationToOutputObservers() {
        log.info("Sending output request...");
        setChanged();
        notifyObservers();
    }

    @Override
    public Composite getComposite() {
        return composite;
    }

    @Override
    public String[] getErrors() {
        if (errorList == null) {
            return new String[0];
        }
        String[] errors = new String[errorList.size()];
        for (int i = 0; i < errorList.size(); i++) {
            switch (errorList.get(i)) {
                case MAX_FUGENLAENGE:
                    errors[i] = CustomInfoMessages.INFO_MAX_LINELENGTH;
                    break;
                case FLIESE_UNPASSEND:
                    errors[i] = CustomInfoMessages.INFO_OTHER;
                    break;
                case FLIESEN_AUSTAUSCHBAR:
                    errors[i] = CustomInfoMessages.INFO_REPLACEABLE_TILE;
                    break;
                case FUGENKREUZE:
                    errors[i] = CustomInfoMessages.INFO_CROSSING;
                    break;
                case GLEICHE_FLIESEN:
                    errors[i] = CustomInfoMessages.INFO_SAME_TILE;
                    break;
                default:
                    throw new UnsupportedOperationException(String.format
                            (CustomErrorMessages.ERROR_INVALID_ENUM, errorList.get(i)));
            }
        }
        return errors;
    }
}
