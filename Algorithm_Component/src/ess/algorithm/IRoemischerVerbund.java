package ess.algorithm;

import java.util.List;

import ess.algorithm.RoemischerVerbund.Validation;

public interface IRoemischerVerbund {

	/**
	 * �berpr�ft die eingegebene L�sung auf Korrektheit
	 * 
	 * @param xmlFile Dokument, das validiert werden soll.
	 * @param maxFugenLaenge maximale Fugenl�nge der zu berechnenden L�sung.
	 * @return Liste von Fehlern, die fehlgeschlagen sind.
	 */
	public abstract List<Validation> validateSolution(String xmlFile, int maxFugenLaenge);

	/**
	 * Ermittelt eine L�sung zu den eingegebenen Daten
	 * 
	 * @param xmlFile Eingabedokument, das die Probleminstanzen enth�lt.
	 * @param maxFugenLaenge maximale Fugenl�nge der zu berechnenden L�sung.
	 * @return konnte eine L�sung gefunden werden? true = ja, false = nein.
	 */
	public abstract boolean solve(String xmlFile, int maxFugenLaenge);
}