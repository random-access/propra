package com.ess.main;

import java.util.logging.Logger;

import ess.algorithm.RoemischerVerbund;
import ess.strings.CustomErrorMessages;
import ess.utils.ProPraLogger;
import ess.utils.PropertyException;

/*
 * Haupteinstiegspunkt der Anwendung.
 * 
 * In der Main-Komponente müssen unter anderem die Eingabeparameter verarbeitet werden
 * 
 *	Für den Ablaufparameter r wird folgende Festlegung getroffen:
 *		"s" (solve): für die durch die XML-Datei beschriebene Probleminstanz wird ein Verlegungsplan ermittelt, 
 *                  falls ein solcher existiert, ansonsten wird das Programm mit einer Fehlermeldung beendet. 
 *                  Falls ein Verlegungsplan ermittelt werden kann, wird dieser in der angegebenen 
 *                  XML-Datei gespeichert. Falls in der XML-Datei bereits ein Verlegungsplan vorhanden ist, so 
 *                  ist dieser zu überschreiben.
 *		"sd" (solve & display): wie "s", nur dass der ermittelte Verlegungsplan nach der Lösung der Probleminstanz 
 *                  zusätzlich in der graphischen Oberfläche angezeigt wird. 
 *		"v" (validate): durch diese Option wird der in der angegebenen XML-Datei enthaltene Verlegungsplan auf die 
 *                  Einhaltung der Bedingungen B1 - B4 hin überprüft. Anschließend sind diejenigen Bedingungen in 
 *                  der Kommandozeile auszugeben, die vom Verlegungsplan verletzt werden. Falls die angegebene 
 *                  XML-Datei keinen Verlegungsplan enthält, wird eine Fehlermeldung ausgegeben. 
 *		"vd" (validate & display): wie "v", nur dass der ermittelte Verlegungsplan nach der Lösung der Probleminstanz 
 *                  zusätzlich in der graphischen Oberfläche angezeigt wird. 
 *		"d" (display): der in der XML-Datei enthaltene Verlegungsplan wird in der graphischen Oberfläche angezeigt. 
 *                  Falls die angegebene XML-Datei keinen Verlegungsplan enthält, wird eine Fehlermeldung ausgegeben. 
 *	Der Eingabedateiparameter if (Input File) ist ein String, der den Pfad der Eingabedatei beinhaltet. Der Parameter 
 *                  für die maximale Fugenlänge l ist eine positive natürliche Zahl, welche die Fugenlänge in cm angibt. 
 *                  Ein Beispielparameteraufruf kann demnach wie folgt aussehen: r=s if="bin\\verbund1.xml" l=1200.
 */
public final class Main {

    private static Logger log = Logger.getGlobal();

    // prevents instantiation
    private Main() {
    }

    /*
     * Haupteinstiegsfunktion
     */
    public static void main(String[] args) {
        try {
            ProPraLogger.setup();
            InputParser inputParser = new InputParser(args);
            RoemischerVerbund v = new RoemischerVerbund();
            log.info("Setting path to " + inputParser.getPath() + "...");
            log.info("Setting max. tile length to " + inputParser.getMaxTileLength() + "...");
            log.info("Setting mode to " + inputParser.getMode() + "...");
            switch(inputParser.getMode()) {
                case SOLVE:
                    new HeadlessObserver().observe(v);
                    v.solve(inputParser.getPath(), inputParser.getMaxTileLength());
                    break;
                case SOLVE_DISPLAY:
                    new DisplayObserver().observe(v);
                    v.solve(inputParser.getPath(), inputParser.getMaxTileLength());
                    break;
                case VALIDATE:
                    new HeadlessObserver().observe(v);
                    v.validateSolution(inputParser.getPath(), inputParser.getMaxTileLength());
                    break;
                case VALIDATE_DISPLAY:
                case DISPLAY:
                    // TODO distinguish between DISPLAY & VALIDATE_DISPLAY
                    new DisplayObserver().observe(v);
                    v.validateSolution(inputParser.getPath(), inputParser.getMaxTileLength());
                    break;
                default:
                    System.out.println(CustomErrorMessages.UNSUPPORTED_MODE);
            }
        } catch (InvalidInputException | PropertyException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

}
