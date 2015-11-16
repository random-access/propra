package com.ess.main;

import java.util.logging.Logger;

import ess.algorithm.RoemischerVerbund;
import ess.strings.CustomInfoMessages;
import ess.utils.ProPraLogger;
import ess.utils.PropertyException;

/*
 * Haupteinstiegspunkt der Anwendung.
 * 
 * In der Main-Komponente m�ssen unter anderem die Eingabeparameter verarbeitet werden
 * 
 *	F�r den Ablaufparameter r wird folgende Festlegung getroffen:
 *	�	�s� (solve): f�r die durch die XML-Datei beschriebene Probleminstanz wird ein Verlegungsplan ermittelt, falls ein solcher existiert, ansonsten wird das Programm mit einer Fehlermeldung beendet. Falls ein Verlegungsplan ermittelt werden kann, wird dieser in der angegebenen XML-Datei gespeichert. Falls in der XML-Datei bereits ein Verlegungsplan vorhanden ist, so ist dieser zu �berschreiben.
 *	�	�sd� (solve & display): wie �s�, nur dass der ermittelte Verlegungsplan nach der L�sung der Probleminstanz zus�tzlich in der graphischen Oberfl�che angezeigt wird. 
 *	�	�v� (validate): durch diese Option wird der in der angegebenen XML-Datei enthaltene Verlegungsplan auf die Einhaltung der Bedingungen B1 � B4 hin �berpr�ft. Anschlie�end sind diejenigen Bedingungen in der Kommandozeile auszugeben, die vom Verlegungsplan verletzt werden. Falls die angegebene XML-Datei keinen Verlegungsplan enth�lt, wird eine Fehlermeldung ausgegeben. 
 *	�	�vd� (validate & display): wie �v�, nur dass der ermittelte Verlegungsplan nach der L�sung der Probleminstanz zus�tzlich in der graphischen Oberfl�che angezeigt wird. 
 *	�	�d� (display): der in der XML-Datei enthaltene Verlegungsplan wird in der graphischen Oberfl�che angezeigt. Falls die angegebene XML-Datei keinen Verlegungsplan enth�lt, wird eine Fehlermeldung ausgegeben. 
 *	Der Eingabedateiparameter if (Input File) ist ein String, der den Pfad der Eingabedatei beinhaltet. Der Parameter f�r die maximale Fugenl�nge l ist eine positive nat�rliche Zahl, welche die Fugenl�nge in cm angibt. Ein Beispielparameteraufruf kann demnach wie folgt aussehen: r=s if="bin\\verbund1.xml" l=1200.
 */
public class Main {
	
	static Logger log = Logger.getGlobal();

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
			switch (inputParser.getMode()) {
			case SOLVE:
				v.solve(inputParser.getPath(), inputParser.getMaxTileLength());
				break;
			case SOLVE_DISPLAY:
				new DisplayObserver().observe(v);
				v.solve(inputParser.getPath(), inputParser.getMaxTileLength());
				break;
			case VALIDATE:
				v.validateSolution(inputParser.getPath(),
						inputParser.getMaxTileLength());
				break;
			case VALIDATE_DISPLAY:
			case DISPLAY:
				new DisplayObserver().observe(v);
				v.validateSolution(inputParser.getPath(),
						inputParser.getMaxTileLength());
				break;
			}
		} catch (InvalidInputException | PropertyException e) {
			System.out.println(e.getMessage());
			System.out.println(CustomInfoMessages.INFO_USAGE);
			System.exit(0);
		}
	}

}
