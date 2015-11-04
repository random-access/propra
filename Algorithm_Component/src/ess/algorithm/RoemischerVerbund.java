package ess.algorithm;

import java.util.List;

import ess.data.Composite;
import ess.io.IDataExchanger;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;

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
 */
public class RoemischerVerbund implements IRoemischerVerbund {

	/**
	 * Fehlertypen, die bei der Validierung auftreten können.
	 */
	public enum Validation {
		FLIESEN_AUSTAUSCHBAR, GLEICHE_FLIESEN, MAX_FUGENLAENGE, FUGENKREUZE, FLIESE_UNPASSEND;
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
	public List<Validation> validateSolution(String xmlFile, int maxFugenLaenge) {

		IDataExchanger xmlExchanger = new XMLDataExchanger();
		Validator validator = new Validator();
		Composite composite = null;
		try {
			composite = xmlExchanger.readFromSource(xmlFile);
			validator.validateSolution(composite, maxFugenLaenge);
		} catch (DataExchangeException e) {
			// TODO handle exception
			System.out.println(e.getMessage());
		}
		List<Validation> errorList = validator.getErrorList();
		if (errorList.isEmpty()) {
			// TODO notify main component that solution can be displayed
		}
		return errorList;
	}

	/**
	 * Ermittelt eine Lösung zu den eingegebenen Daten.
	 *
	 * @param xmlFile
	 *            Eingabedokument, das die Probleminstanzen enthält.
	 * @param maxFugenLaenge
	 *            maximale Fugenlänge der zu berechnenden Lösung.
	 * @return konnte eine Lösung gefunden werden? true = ja, false = nein.
	 */
	@Override
	public boolean solve(String xmlFile, int maxFugenLaenge) {
		return false;
	}
}
