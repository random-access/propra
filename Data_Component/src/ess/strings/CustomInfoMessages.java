package ess.strings;

/**
* This class holds various info messages with user-friendly output for all errors that can occur in the application.
* I collected all error messages here to avoid duplicate String constants and to have an overview about what gets displayed
* to the user.<br><br>
* 
* Some info messages take parameters, they should be used with String.format(message, parameter).
* 
* @author Monika Schrenk
*
*/
public final class CustomInfoMessages {
    
    // prevents instantiation
    private CustomInfoMessages() { }
    
	/******************************************* Info about broken rules *****************************************/
    
    /**
     * Info about occurrence of crossings in composite.
     */
	public static final String INFO_CROSSING = "L\u00f6sung enth\u00e4lt mindestens eine Stelle mit Fugenkreuzen.";
	
	/**
     * Info that a couple of smaller tiles can be replaced with a single larger tile in composite.
     */
	public static final String INFO_REPLACEABLE_TILE = "L\u00f6sung enth\u00e4lt mindestens eine durch eine "
	        + "gr\u00f6\u00dfere Fliese ersetzbare Kombination von Fliesen.";
	
	/**
     * Info that two same tiles are placed exactly next to each other in composite.
     */
	public static final String INFO_SAME_TILE = "L\u00f6sung enth\u00e4lt mindestens zwei gleiche "
	        + "nebeneinanderliegende Fliesen.";
	
	/**
	 * Info that the maximum line length is exceeded in composite.
	 */
	public static final String INFO_MAX_LINELENGTH = "Die maximale Fugenl\u00e4nge wurde an mindestens einer "
	        + "Stelle \u00fcberschritten.";
	
	/**
	 * Info that not all tiles were used for building the composite.
	 */
	public static final String INFO_UNUSED_TILES = "F\u00fcr diese L\u00f6sung wurden nicht alle Fliesen genutzt.";
	
	/**
	 * Info that composite is not valid because of other reasons.
	 */
	public static final String INFO_OTHER = "Diese L\u00f6sung enth\u00e4lt sonstige Fehler.";
	
	/**
	 * Info that the composite is valid.
	 */
	public static final String INFO_VALIDATION_SUCCESS = "Dies ist ein g\u00fcltiger R\u00f6mischer Verbund.";
	
	/**
     * Info that the composite is invalid.
     */
    public static final String INFO_VALIDATION_FAILURE = "Dies ist kein g\u00fcltiger R\u00f6mischer Verbund.";
	
	/******************************************* Info about execution mode ********************************************/
	
	/**
	 * Info that composite was solved successfully
	 */
	public static final String INFO_SOLVE_SUCCESS = "Aus den gegebenen Daten konnte ein R\u00f6mischer Verbund erzeugt werden.";
	
	/**
	 * Info that there is no solution for composite
	 */
	public static final String INFO_SOLVE_FAILURE = "Aus den gegebenen Daten konnte kein R\u00f6mischer Verbund erzeugt werden.";
	
	/**
	 * Info about results of validation
	 */
	public static final String INFO_VALIDATE  = "Ergebnis der Validierung des R\u00f6mischen Verbunds:";
	
	/**
	 * Info about display-only mode
	 */
	public static final String INFO_DISPLAY = "Der R\u00f6mische Verbund wurde im Anzeigemodus ge\u00f6ffnet.";
	
	
	/******************************************* Info about application usage *****************************************/
	
	/**
	 * Title and short description of the application.
	 */
	public static final String INFO_PROGRAM = "ProPra - validiert und verlegt einen R\u00f6mischen Verbund aus "
	        + "Angaben in einer XML-Datei";
	
	/**
	 * How to call the application.
	 */
	public static final String INFO_EXEC_CALL = "Aufruf: java -jar ProPra.jar r=<Option> if=<Pfad> l=<Wert>";
	
	/**
	 * Info about the path parameter ("if")
	 */
	public static final String INFO_PARAM_PATH = "Pfad: g\u00fcltiger Pfad zur XML-Datei eines zu validierenden bzw. "
	        + "zu verlegenden R\u00f6mischen Verbunds. \n"
			+ "Bei Leerzeichen im Pfad bitte Semikolons verwenden.";
	
	/**
	 * Info about the maximum gap parameter ("l")
	 */
	public static final String INFO_PARAM_LENGTH = "Wert: maximale Fugenl\u00e4nge, bitte positive Ganzzahl eingeben.";
	
	/**
	 * Info about the different execution modes ("r")
	 */
	public static final String INFO_PARAM_EXECMODE = "Option: Bitte eine der folgenden Optionen angeben.\n"
			+ "\t s \t F\u00fcr die durch die XML-Datei beschriebene Probleminstanz wird ein Verlegungsplan ermittelt, "
			+ "falls ein solcher existiert. \n"
			+ "\t \t Falls ein Verlegungsplan ermittelt werden kann, wird dieser in der angegebenen XML-Datei gespeichert. \n"
			+ "\t \t Falls in der XML-Datei bereits ein Verlegungsplan vorhanden ist, wird dieser \u00fcberschrieben. \n"
			+ "\t sd \t Wie \"s\", nur dass der ermittelte Verlegungsplan nach der L\u00f6sung der Probleminstanz "
			+ "zus\u00e4tzlich \n"
			+ "\t \t in der grafischen Oberfl\u00e4che angezeigt wird. \n"
			+ "\t v \t Durch diese Option wird der in der angegebenen XML-Datei enthaltene Verlegungsplan auf die Einhaltung "
			+ "der in\n"
			+ "\t \t der Config-Datei aktivierten Regeln \u00fcberpr\u00fcft. Werden Bedingungen verletzt, so erfolgt eine "
			+ "Ausgabe im Terminal. \n"
			+ "\t vd \t Wie \"v\", nur dass der Verlegungsplan nach erfolgreicher Validierung in der grafischen Oberfl\u00e4che "
			+ "angezeigt wird.\n"
			+ "\t d \t Der in der XML-Datei enthaltene Verlegungsplan wird in einer grafischen Oberfl\u00e4che angezeigt, \n"
			+ "\t \t sofern er prinzipiell g\u00fcltige Werte enth\u00e4lt.";
	
	/**
	 * Info about the application parameters.
	 */
	public static final String INFO_PARAMS = INFO_PARAM_PATH + "\n" + INFO_PARAM_LENGTH + "\n" + INFO_PARAM_EXECMODE;
	
	/**
	 * Usage hints.
	 */
	public static final String INFO_HINTS = "Wichtig: \n"
			+ "\t Es m\u00fcssen *alle* Parameter mit angegeben werden, auch bei der Option d! Die Fugenlänge wird bei "
			+ "r=d ignoriert.\n"
			+ "\t Bitte *keine Leerzeichen* zwischen Parameter und Wert einf\u00fcgen!";
	
	/**
	 * Usage examples.
	 */
	public static final String INFO_EXAMPLES = "Beispiele: \n "
			+ "\t java -jar ProPra.jar r=vd if=\"/Pfad/zur/XML Datei.xml\" l=120\t Validieren und anzeigen\n"
			+ "\t java -jar ProPra.jar r=s if=/Pfad/zur/xml_datei.xml l=110\t Nur verlegen";
	
	/**
	 * Application reference.
	 */
	public static final String INFO_USAGE = INFO_PROGRAM + "\n\n" + INFO_EXEC_CALL + "\n\n" 
			+ INFO_PARAMS + "\n\n" + INFO_HINTS + "\n\n" + INFO_EXAMPLES + "\n";

    
}
