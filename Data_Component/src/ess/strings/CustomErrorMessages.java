package ess.strings;

//TODO maybe access info messages via ResourceBundle.
/**
 * This class holds various error messages with user-friendly output for all errors that can occur in the application.
 * I collected all error messages here to avoid duplicate String constants and to have an overview about what gets displayed
 * to the user.<br><br>
 * 
 * Some error messages take parameters (for example the path which cannot be found), they should be used with 
 * String.format(message, parameter).
 * 
 * @author Monika Schrenk
 *
 */
public final class CustomErrorMessages {
    
    // prevents instantiation
    private CustomErrorMessages() { }
    
    /******************************************** Property errors **************************************************/

    /**
     * Error message for invalid log_console value.
     */
    public static final String ERROR_INVALID_VALUE_LOG_CONSOLE = "Ung\u00fcltiger Parameter in der Property-Datei "
            + "f\u00fcr den Schl\u00fcssel \"log_console\", \n" + "bitte entweder \"true\" oder \"false\" eingeben.";
    
    /**
     * Error message for invalid log_level value.
     */
    public static final String ERROR_INVALID_VALUE_LOG_LEVEL = "Ung\u00fcltiger Parameter in der Property-Datei "
            + "f\u00fcr den Schl\u00fcssel \"log_level\", \n"
            + "bitte ein valides Loglevel eingeben";
    
    /**
     * Error message for not being able to create log file.
     */
    public static final String ERROR_LOG_CREATE = "Logdateien k\u00f6nnen nicht angelegt werden. Bitte "
            + "\u00fcberpr\u00fcfen Sie, ob der Pfad, den Sie in \n"
            + "der Property-Datei angegeben haben ein g\u00fcltiger Verzeichnisname ist und ob Sie Schreibrechte haben.";
    
    /**
     * Error message for problems reading properties file.
     */
    public static final String ERROR_PROPERTY_READ = "Property-Datei existiert nicht oder kann nicht gelesen werden.";
    

    /********************************************* XML I / O *************************************************************/
    
    /**
     * Error message for invalid value for tile length.
     */
    public static final String ERROR_INVALID_DATATYPE_TILE_LENGTH = "Ung\u00fcltiger Wert für Fliesenl\u00e4nge! Alle "
            + "Fliesenl\u00e4ngen m\u00fcssen positive Ganzzahlen und ein Vielfaches von 20 sein.";
    
    
    /**
     * Error message for invalid value for surface length.
     */
    public static final String ERROR_INVALID_DATATYPE_SURFACE_LENGTH = "Ung\u00fcltiger Wert für Ma\u00dfe des Verlegungsplans! "
            + "H\u00f6he und Breite m\u00fcssen positive Ganzzahlen und ein Vielfaches von 20 sein.";
    
    /**
     * Error message for file with invalid XML content
     */
    public static final String ERROR_INVALID_CONTENT = "Die Datei %s kann nicht eingelesen werden, da die Struktur "
            + "keinem R\u00f6mischen Verbund "
            + "entspricht.";
    
    /**
     * Error message for problems reading XML from source.
     */
    public static final String ERROR_READING_XML = "Fehler beim Lesen der Datei %s";
    
    /**
     * Error message for invalid XML content.
     */
    public static final String ERROR_XML_CONTENT = "Die Datei %s enthält ungültige XML-Inhalte: \n--> %s";
    
    /**
     * Error message for problems writing XML to target.
     */
    public static final String ERROR_WRITING_XML = "Fehler beim Schreiben in Datei %s";
    
    /**
     * Error message for missing or inaccessible DTD
     */
    public static final String ERROR_DTD_NOT_FOUND = "DTD Validierungs-Datei %s existiert nicht oder kann nicht gelesen "
            + "werden.";
    
    /**
     * Error message for problems with DTD validation
     */
    public static final String ERROR_VALIDATING_XML = "Ein interner Fehler ist aufgetreten, die DTD-Validierung konnte "
            + "nicht initialisiert werden.";

    

    /********************************************* INPUT PARSING *******************************************************/
    
    /**
     * Error message for invalid value for parameter execution mode
     */
    public static final String ERROR_INVALID_EXEC_MODE = "Unbekannter Ausf\u00fchrungsmodus.\n\n"
            + CustomInfoMessages.INFO_PARAM_EXECMODE;
    
    /**
     * Error message for invalid value for parameter gap length
     */
    public static final String ERROR_INVALID_LENGTH = "Ung\u00fcltiger Wert f\u00fcr die maximale Fugenl\u00e4nge.\n\n"
            + CustomInfoMessages.INFO_PARAM_LENGTH;

    /**
     * Error message for invalid input parameter or wrong parameter order.
     */
    public static final String ERROR_INVALID_PARAM = "Ung\u00fcltiger Parameter oder falsche Parameterreihenfolge.\n\n"
            + CustomInfoMessages.INFO_EXEC_CALL;
    
    /**
     * Error message for invalid number of parameter
     */
    public static final String ERRO_INVALID_PARAM_COUNT = "Ung\u00fcltige Parameteranzahl. \n\n"
            + CustomInfoMessages.INFO_EXEC_CALL;
    
    /**
     * Error message for invalid mode // TODO duplicate of ERROR_INVALID_PARAM?
     */
    public static final String UNSUPPORTED_MODE = "Dieser Modus wurde noch nicht vollständig implementiert.";

    
    /**************************************************** GENERAL ERRORS **************************************************/

    /**
     * Error message for enum value not handled in switch statement
     */
    public static final String ERROR_INVALID_ENUM = "Nicht ber\u00fccksichtigter Wert: %s";
    
    /**
     * Error message for invalid paths in file system.
     */
    public static final String ERROR_PATH_NOT_FOUND = "%s existiert nicht oder ist ein Verzeichnis.";
    
    
    /***************************************************** UI ERRORS ********************************************************/
    public static final String ERROR_APP_ICON = "Anwendungs-Icon konnte nicht geladen werden.";
}
