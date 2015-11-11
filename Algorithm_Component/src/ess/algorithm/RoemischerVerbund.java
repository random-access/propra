package ess.algorithm;

import java.util.List;

import ess.data.Composite;
import ess.io.IDataExchanger;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.utils.ProPraLogger;
import ess.utils.PropertyException;

// TODO: Auto-generated Javadoc
/**
 * Diese Klasse wird als API (Application Programming Interface) verwendet. Das
 * bedeutet, dass diese Klasse als Bibliothek f�r andere Applikationen verwendet
 * werden kann.
 * 
 * Bitte achten Sie darauf, am bereits implementierten Rahmen (Klassenname,
 * Package, Methodensignaturen) !!KEINE!! Ver�nderungen vorzunehmen.
 * Selbstverst�ndlich k�nnen und m�ssen Sie innerhalb einer Methode �nderungen
 * vornehmen.
 */
public class RoemischerVerbund implements IRoemischerVerbund {

	/**
	 * Fehlertypen, die bei der Validierung auftreten k�nnen.
	 */
	public enum Validation {
		FLIESEN_AUSTAUSCHBAR, GLEICHE_FLIESEN, MAX_FUGENLAENGE, FUGENKREUZE, FLIESE_UNPASSEND;
	}

	/**
	 * �berpr�ft die eingegebene L�sung auf Korrektheit.
	 *
	 * @param xmlFile
	 *            Dokument, das validiert werden soll.
	 * @param maxFugenLaenge
	 *            maximale Fugenl�nge der zu berechnenden L�sung.
	 * @return Liste von Fehlern, die fehlgeschlagen sind.
	 */
	@Override
	public List<Validation> validateSolution(String xmlFile, int maxFugenLaenge) {
		try {
			ProPraLogger.setup();
		} catch (PropertyException e1) {
			System.out.println(e1.getMessage());
		}
		IDataExchanger xmlExchanger = new XMLDataExchanger();
		Validator validator = null; // TODO
		try {
			validator = new Validator();
			Composite composite = null;
		
			composite = xmlExchanger.readFromSource(xmlFile);
			validator.validateSolution(composite, maxFugenLaenge);
		} catch (DataExchangeException e) {
			// TODO handle exception
			System.out.println(e.getMessage());
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Validation> errorList = validator.getErrorList();
		if (errorList.isEmpty()) {
			// TODO notify main component that solution can be displayed
		}
		return errorList;
	}

	/**
	 * Ermittelt eine L�sung zu den eingegebenen Daten.
	 *
	 * @param xmlFile
	 *            Eingabedokument, das die Probleminstanzen enth�lt.
	 * @param maxFugenLaenge
	 *            maximale Fugenl�nge der zu berechnenden L�sung.
	 * @return konnte eine L�sung gefunden werden? true = ja, false = nein.
	 */
	@Override
	public boolean solve(String xmlFile, int maxFugenLaenge) {
		try {
			ProPraLogger.setup();
		} catch (PropertyException e1) {
			System.out.println(e1.getMessage());
		}
		IDataExchanger xmlExchanger = new XMLDataExchanger();
		Solver solver = null;
		Composite composite = null;
		try {
			composite = xmlExchanger.readFromSource(xmlFile);
			solver = new Solver(composite, maxFugenLaenge);
			boolean solved = solver.solve();
			if (solved) {
				// TODO write data into xml file
				// TODO notify main component that solution can be displayed
			}
			return solved;
		} catch (DataExchangeException e) {
			// TODO handle exception
			System.out.println(e.getMessage());
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
