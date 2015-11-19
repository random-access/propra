package ess.strings;

// TODO Javadoc
public class CustomErrorMessages {
	
	
	public static final String ERROR_INVALID_KEY_LOG_CONSOLE = "Ungültiger Parameter in der Property-Datei für den Schlüssel \"log_console\", \n"
			+ "bitte entweder \"true\" oder \"false\" eingeben.";
	public static final String ERROR_INVALID_KEY_LOG_LEVEL = "Ungültiger Parameter in der Property-Datei für den Schlüssel \"log_level\", \n"
			+ "bitte ein valides Loglevel eingeben";
	public static final String ERROR_LOG_CREATE = "Logdateien können nicht angelegt werden. Bitte überprüfen Sie, ob der Pfad, den Sie in \n"
			+ "der Property-Datei angegeben haben ein gültiger Verzeichnisname ist und ob Sie Schreibrechte haben.";
	public static final String ERROR_PROPERTY_READ = "Property-Datei existiert nicht oder kann nicht gelesen werden.";
	public static final String ERROR_PATH_NOT_FOUND = "%s existiert nicht oder ist ein Verzeichnis.";
	public static final String ERROR_INVALID_CONTENT = "Die Datei %s kann nicht eingelesen werden, da die Struktur keinem Römischen Verbund entspricht.";
	public static final String ERROR_DTD_NOT_FOUND = "DTD Validierungs-Datei %s existiert nicht oder kann nicht gelesen werden.";
	public static final String ERROR_VALIDATING_XML = "Ein interner Fehler ist aufgetreten, die DTD-Validierung konnte nicht initialisiert werden.";
	
	public static final String ERROR_INVALID_ENUM = "Nicht berücksichtigter Enum-Wert: %s";
	
	// input parsing
	public static final String ERROR_INVALID_EXEC_MODE = "Unbekannter Ausführungsmodus.\n\n" + CustomInfoMessages.INFO_PARAM_EXECMODE;
	public static final String ERROR_INVALID_LENGTH = "Ungültiger Wert für die maximale Fugenlänge.\n\n" + CustomInfoMessages.INFO_PARAM_LENGTH;
	
	public static final String ERROR_INVALID_PARAM = "Ungültiger Parameter oder falsche Parameterreihenfolge.\n\n" + CustomInfoMessages.INFO_EXEC_CALL;
	public static final String ERRO_INVALID_PARAM_COUNT = "Ungültige Parameteranzahl. \n\n" + CustomInfoMessages.INFO_EXEC_CALL;

}
