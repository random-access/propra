package com.ess.main;

import java.util.logging.Logger;

import ess.algorithm.AbstractOutputObservable;
import ess.algorithm.RoemischerVerbund;
import ess.exc.InvalidInputException;
import ess.exc.PropertyException;
import ess.strings.CustomErrorMessages;
import ess.utils.ProPraLogger;

/**
 * Haupteinstiegspunkt der Anwendung.
 * 
 * In der Main-Komponente muessen unter anderem die Eingabeparameter verarbeitet werden
 * 
 *	Fuer den Ablaufparameter r wird folgende Festlegung getroffen:
 *		"s" (solve): fuer die durch die XML-Datei beschriebene Probleminstanz wird ein Verlegungsplan ermittelt, 
 *                  falls ein solcher existiert, ansonsten wird das Programm mit einer Fehlermeldung beendet. 
 *                  Falls ein Verlegungsplan ermittelt werden kann, wird dieser in der angegebenen 
 *                  XML-Datei gespeichert. Falls in der XML-Datei bereits ein Verlegungsplan vorhanden ist, so 
 *                  ist dieser zu ueberschreiben.
 *		"sd" (solve & display): wie "s", nur dass der ermittelte Verlegungsplan nach der Loesung der Probleminstanz 
 *                  zusaetzlich in der graphischen Oberflaeche angezeigt wird. 
 *		"v" (validate): durch diese Option wird der in der angegebenen XML-Datei enthaltene Verlegungsplan auf die 
 *                  Einhaltung der Bedingungen B1 - B4 hin ueberprueft. Anschliessend sind diejenigen Bedingungen in 
 *                  der Kommandozeile auszugeben, die vom Verlegungsplan verletzt werden. Falls die angegebene 
 *                  XML-Datei keinen Verlegungsplan enthaelt, wird eine Fehlermeldung ausgegeben. 
 *		"vd" (validate & display): wie "v", nur dass der ermittelte Verlegungsplan nach der Loesung der Probleminstanz 
 *                  zusaetzlich in der graphischen Oberflaeche angezeigt wird. 
 *		"d" (display): der in der XML-Datei enthaltene Verlegungsplan wird in der graphischen Oberflaeche angezeigt. 
 *                  Falls die angegebene XML-Datei keinen Verlegungsplan enthaelt, wird eine Fehlermeldung ausgegeben. 
 *	Der Eingabedateiparameter if (Input File) ist ein String, der den Pfad der Eingabedatei beinhaltet. Der Parameter 
 *                  fuer die maximale Fugenlaenge l ist eine positive natuerliche Zahl, welche die Fugenlaenge in cm angibt. 
 *                  Ein Beispielparameteraufruf kann demnach wie folgt aussehen: r=s if="bin\\verbund1.xml" l=1200.
 */
public final class Main {

    private static Logger log = Logger.getGlobal();

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
            ProPraLogger.setup();
            InputParser inputParser = new InputParser(args);
            RoemischerVerbund v = new RoemischerVerbund();
            
            log.info("Setting path to " + inputParser.getPath() + "...");
            log.info("Setting max. tile length to " + inputParser.getMaxTileLength() + "...");
            log.info("Setting mode to " + inputParser.getMode() + "...");

            initDisplay(inputParser.getMode(), (AbstractOutputObservable) v);
            
            executeAlgorithm(v, inputParser);
            
        // Output all expected Exceptions in a human-readable, textual representation.
        } catch (InvalidInputException | PropertyException e) {
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
