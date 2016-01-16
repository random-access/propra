package ess.algorithm;

import java.util.List;

import ess.algorithm.RoemischerVerbund.Validation;

/**
 * The Interface <code>IRoemischerVerbund<code>.
 */
public interface IRoemischerVerbund {

    /**
     * Ueberprueft die eingegebene Loesung auf Korrektheit.
     *
     * @param xmlFile
     *            Dokument, das validiert werden soll.
     * @param maxFugenLaenge
     *            maximale Fugenlaenge der zu berechnenden Loesung.
     * @return Liste von Fehlern, die fehlgeschlagen sind.
     */
    List<Validation> validateSolution(String xmlFile, int maxFugenLaenge);

    /**
     * Ermittelt eine Loesung zu den eingegebenen Daten.
     *
     * @param xmlFile
     *            Eingabedokument, das die Probleminstanzen enthält.
     * @param maxFugenLaenge
     *            maximale Fugenlaenge der zu berechnenden Loesung.
     * @return konnte eine Loesung gefunden werden? true = ja, false = nein.
     */
    boolean solve(String xmlFile, int maxFugenLaenge);
}