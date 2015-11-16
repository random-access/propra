package ess.algorithm;

import java.util.List;

import ess.algorithm.RoemischerVerbund.Validation;

/**
 * The Interface IRoemischerVerbund.
 */
public interface IRoemischerVerbund {

	/**
	 * Überprüft die eingegebene Lösung auf Korrektheit.
	 *
	 * @param xmlFile Dokument, das validiert werden soll.
	 * @param maxFugenLaenge maximale Fugenlänge der zu berechnenden Lösung.
	 * @return Liste von Fehlern, die fehlgeschlagen sind.
	 */
	public abstract List<Validation> validateSolution(String xmlFile, int maxFugenLaenge);

	/**
	 * Ermittelt eine Lösung zu den eingegebenen Daten.
	 *
	 * @param xmlFile Eingabedokument, das die Probleminstanzen enthält.
	 * @param maxFugenLaenge maximale Fugenlänge der zu berechnenden Lösung.
	 * @return konnte eine Lösung gefunden werden? true = ja, false = nein.
	 */
	public abstract boolean solve(String xmlFile, int maxFugenLaenge);
}