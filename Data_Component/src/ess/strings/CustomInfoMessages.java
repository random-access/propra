package ess.strings;

public class CustomInfoMessages {
	
	public static final String INFO_CROSSING = "Lösung enthält mindestens eine Stelle mit Fugenkreuzen.";
	public static final String INFO_REPLACEABLE_TILE = "Lösung enthält mindestens eine durch eine größere Fliese "
			+ "ersetzbare Kombination von Fliesen.";
	public static final String INFO_SAME_TILE = "Lösung enthält mindestens zwei gleiche nebeneinanderliegende Fliesen.";
	public static final String INFO_MAX_LINELENGTH = "Die maximale Fugenlänge wurde an mindestens einer Stelle überschritten.";
	public static final String INFO_OTHER = "Diese Lösung enthält sonstige Fehler.";
	
	public static final String INFO_USAGE = "ProPra - validiert und verlegt einen Römischen Verbund aus Angaben in einer XML-Datei\n\n"
			+ "Aufruf: java -jar ProPra.jar r=<Option> if=<Pfad> l=<Wert>\n\n"
			+ "Pfad: gültiger Pfad zur XML-Datei eines zu validierenden bzw. zu verlegenden Römischen Verbunds. Bei Leerzeichen im Pfad bitte Semikolons verwenden.\n"
			+ "Wert: maximale Fugenlänge, bitte positive Ganzzahl eingeben.\n"
			+ "Option: Bitte eine der folgenden Optionen angeben.\n"
			+ "\t s \t Für die durch die XML-Datei beschriebene Probleminstanz wird ein Verlegungsplan ermittelt, falls ein solcher existiert. "
			+ "Falls ein Verlegungsplan ermittelt werden kann, wird dieser in der angegebenen XML-Datei gespeichert. "
			+ "Falls in der XML-Datei bereits ein Verlegungsplan vorhanden ist, wird dieser überschrieben. \n"
			+ "\t sd \t Wie \"s\", nur dass der ermittelte Verlegungsplan nach der Lösung der Probleminstanz zusätzlich "
			+ "in der grafischen Oberfläche angezeigt wird. \n"
			+ "\t v \t Durch diese Option wird der in der angegebenen XML-Datei enthaltene Verlegungsplan auf die Einhaltung der in der Config-Datei aktivierten"
			+ " Regeln überprüft. Werden Bedingungen verletzt, so erfolgt eine Ausgabe im Terminal. \n"
			+ "\t vd \t Wie \"v\", nur dass der Verlegungsplan nach erfolgreicher Validierung in der grafischen Oberfläche angezeigt wird.\n"
			+ "\t d \t Der in der XML-Datei enthaltene Verlegungsplan wird in einer grafischen Oberfläche angezeigt, sofern er prinzipiell gültige Werte enthält.\n\n"
			+ "Wichtig: \n"
			+ "\t Es müssen *alle* Parameter mit angegeben werden, auch bei der Option d!\n"
			+ "\t Bitte *keine Leerzeichen* zwischen Parameter und Wert einfügen!\n\n"
			+ "Beispiele: \n "
			+ "\t java -jar ProPra.jar r=vd if=\"/Pfad/zur/XML Datei.xml\" l=120\t Validieren und anzeigen\n"
			+ "\t java -jar ProPra.jar r=s if=/Pfad/zur/xml_datei.xml l=110\t Nur verlegen \n";
}
