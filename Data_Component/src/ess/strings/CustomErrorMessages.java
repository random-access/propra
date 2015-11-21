package ess.strings;

// TODO Javadoc
public class CustomErrorMessages {
	
	
	public static final String ERROR_INVALID_KEY_LOG_CONSOLE = "Ung\u00fcltiger Parameter in der Property-Datei f\u00fcr den Schl\u00fcssel \"log_console\", \n"
			+ "bitte entweder \"true\" oder \"false\" eingeben.";
	public static final String ERROR_INVALID_KEY_LOG_LEVEL = "Ung\u00fcltiger Parameter in der Property-Datei f\u00fcr den Schl\u00fcssel \"log_level\", \n"
			+ "bitte ein valides Loglevel eingeben";
	public static final String ERROR_LOG_CREATE = "Logdateien k\u00f6nnen nicht angelegt werden. Bitte \u00fcberpr\u00fcfen Sie, ob der Pfad, den Sie in \n"
			+ "der Property-Datei angegeben haben ein g\u00fcltiger Verzeichnisname ist und ob Sie Schreibrechte haben.";
	public static final String ERROR_PROPERTY_READ = "Property-Datei existiert nicht oder kann nicht gelesen werden.";
	public static final String ERROR_PATH_NOT_FOUND = "%s existiert nicht oder ist ein Verzeichnis.";
	public static final String ERROR_INVALID_CONTENT = "Die Datei %s kann nicht eingelesen werden, da die Struktur keinem R\u00f6mischen Verbund entspricht.";
	public static final String ERROR_DTD_NOT_FOUND = "DTD Validierungs-Datei %s existiert nicht oder kann nicht gelesen werden.";
	public static final String ERROR_VALIDATING_XML = "Ein interner Fehler ist aufgetreten, die DTD-Validierung konnte nicht initialisiert werden.";
	
	public static final String ERROR_INVALID_ENUM = "Nicht ber\u00fccksichtigter Enum-Wert: %s";
	
	// input parsing
	public static final String ERROR_INVALID_EXEC_MODE = "Unbekannter Ausf\u00fchrungsmodus.\n\n" + CustomInfoMessages.INFO_PARAM_EXECMODE;
	public static final String ERROR_INVALID_LENGTH = "Ung\u00fcltiger Wert f\u00fcr die maximale Fugenl\u00e4nge.\n\n" + CustomInfoMessages.INFO_PARAM_LENGTH;
	
	public static final String ERROR_INVALID_PARAM = "Ung\u00fcltiger Parameter oder falsche Parameterreihenfolge.\n\n" + CustomInfoMessages.INFO_EXEC_CALL;
	public static final String ERRO_INVALID_PARAM_COUNT = "Ung\u00fcltige Parameteranzahl. \n\n" + CustomInfoMessages.INFO_EXEC_CALL;

}
