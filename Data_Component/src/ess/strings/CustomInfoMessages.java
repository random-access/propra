package ess.strings;

// TODO Javadoc
public class CustomInfoMessages {
	/******************************************* Infos about broken rules *****************************************/
	public static final String INFO_CROSSING = "Lösung enthält mindestens eine Stelle mit Fugenkreuzen.";
	public static final String INFO_REPLACEABLE_TILE = "Lösung enthält mindestens eine durch eine größere Fliese "
			+ "ersetzbare Kombination von Fliesen.";
	public static final String INFO_SAME_TILE = "Lösung enthält mindestens zwei gleiche nebeneinanderliegende Fliesen.";
	public static final String INFO_MAX_LINELENGTH = "Die maximale Fugenlänge wurde an mindestens einer Stelle überschritten.";
	public static final String INFO_OTHER = "Diese Lösung enthält sonstige Fehler.";
	
	/******************************************* Infos about program usage *****************************************/
	public static final String INFO_PROGRAM = "ProPra - validiert und verlegt einen Römischen Verbund aus Angaben in einer XML-Datei";
	public static final String INFO_EXEC_CALL = "Aufruf: java -jar ProPra.jar r=<Option> if=<Pfad> l=<Wert>";
	public static final String INFO_PARAM_PATH = "Pfad: gültiger Pfad zur XML-Datei eines zu validierenden bzw. zu verlegenden Römischen Verbunds. \n"
			+ "Bei Leerzeichen im Pfad bitte Semikolons verwenden.";
	public static final String INFO_PARAM_LENGTH = "Wert: maximale Fugenlänge, bitte positive Ganzzahl eingeben.";
	public static final String INFO_PARAM_EXECMODE = "Option: Bitte eine der folgenden Optionen angeben.\n"
			+ "\t s \t Für die durch die XML-Datei beschriebene Probleminstanz wird ein Verlegungsplan ermittelt, falls ein solcher existiert. \n"
			+ "\t \t Falls ein Verlegungsplan ermittelt werden kann, wird dieser in der angegebenen XML-Datei gespeichert. \n"
			+ "\t \t Falls in der XML-Datei bereits ein Verlegungsplan vorhanden ist, wird dieser überschrieben. \n"
			+ "\t sd \t Wie \"s\", nur dass der ermittelte Verlegungsplan nach der Lösung der Probleminstanz zusätzlich \n"
			+ "\t \t in der grafischen Oberfläche angezeigt wird. \n"
			+ "\t v \t Durch diese Option wird der in der angegebenen XML-Datei enthaltene Verlegungsplan auf die Einhaltung der in\n"
			+ "\t \t der Config-Datei aktivierten Regeln überprüft. Werden Bedingungen verletzt, so erfolgt eine Ausgabe im Terminal. \n"
			+ "\t vd \t Wie \"v\", nur dass der Verlegungsplan nach erfolgreicher Validierung in der grafischen Oberfläche angezeigt wird.\n"
			+ "\t d \t Der in der XML-Datei enthaltene Verlegungsplan wird in einer grafischen Oberfläche angezeigt, \n"
			+ "\t \t sofern er prinzipiell gültige Werte enthält.";
	public static final String INFO_PARAMS = INFO_PARAM_PATH + "\n" + INFO_PARAM_LENGTH + "\n" + INFO_PARAM_EXECMODE;
	public static final String INFO_HINTS = "Wichtig: \n"
			+ "\t Es müssen *alle* Parameter mit angegeben werden, auch bei der Option d!\n"
			+ "\t Bitte *keine Leerzeichen* zwischen Parameter und Wert einfügen!";
	public static final String INFO_EXAMPLES = "Beispiele: \n "
			+ "\t java -jar ProPra.jar r=vd if=\"/Pfad/zur/XML Datei.xml\" l=120\t Validieren und anzeigen\n"
			+ "\t java -jar ProPra.jar r=s if=/Pfad/zur/xml_datei.xml l=110\t Nur verlegen";
	public static final String INFO_USAGE = INFO_PROGRAM + "\n\n" + INFO_EXEC_CALL +"\n\n" 
			+ INFO_PARAMS + "\n\n" + INFO_HINTS + "\n\n" + INFO_EXAMPLES + "\n";
}
