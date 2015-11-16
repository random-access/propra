package ess.strings;

public class CustomErrorMessages {
	
	
	public static final String ERROR_INVALID_KEY_LOG_CONSOLE = "Ungültiger Parameter in der Property-Datei für den Schlüssel \"log_console\", "
			+ "bitte entweder \"true\" oder \"false\" eingeben.";
	public static final String ERROR_INVALID_KEY_LOG_LEVEL = "Ungültiger Parameter in der Property-Datei für den Schlüssel \"log_level\", "
			+ "bitte ein valides Loglevel eingeben";
	public static final String ERROR_LOG_CREATE = "Logdateien können nicht angelegt werden. Bitte überprüfen Sie, ob der Pfad, den Sie in"
			+ "der Property-Datei angegeben haben ein gültiger Verzeichnisname ist und ob Sie Schreibrechte haben.";
	public static final String ERROR_PROPERTY_READ = "Property-Datei existiert nicht oder kann nicht gelesen werden.";
	public static final String ERROR_PATH_NOT_FOUND = "%s existiert nicht oder ist ein Verzeichnis.";
	public static final String ERROR_DTD_NOT_FOUND = "DTD Validierungs-Datei %s existiert nicht oder kann nicht gelesen werden.";
	
	public static final String ERROR_INVALID_ENUM = "Nicht berücksichtigter Enum-Wert: %s";

}
