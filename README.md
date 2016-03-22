# propra
Meine Implementierung der Aufgabenstellung vom Grundpraktikum Programmierung 2015 (Fernuni Hagen)

<p align="center">
<img width="70%" src="https://github.com/random-access/propra/blob/master/Screenshot.png" alt="Screenshot">
</p>

_Validiert und verlegt einen Römischen Verbund aus Angaben in einer XML-Datei._

**Aufruf**: `java -jar ProPra.jar r=<Option> if=<Pfad> l=<Wert>`

- **Pfad**: gültiger Pfad zur XML-Datei eines zu validierenden bzw. zu verlegenden Römischen Verbunds. 
Bei Leerzeichen im Pfad bitte Semikolons verwenden.
- **Wert**: maximale Fugenlänge, bitte positive Ganzzahl eingeben.
- **Option**: Bitte eine der folgenden Optionen angeben:
	 * **s** - Für die durch die XML-Datei beschriebene Probleminstanz wird ein Verlegungsplan ermittelt, falls ein solcher existiert. 
	 	 Falls ein Verlegungsplan ermittelt werden kann, wird dieser in der angegebenen XML-Datei gespeichert. 
	 	 Falls in der XML-Datei bereits ein Verlegungsplan vorhanden ist, wird dieser überschrieben. 
	 * **sd** - Wie "s", nur dass der ermittelte Verlegungsplan nach der Lösung der Probleminstanz zusätzlich 
	 	 in der grafischen Oberfläche angezeigt wird. 
	 * **v** - 	 Durch diese Option wird der in der angegebenen XML-Datei enthaltene Verlegungsplan auf die Einhaltung der in
	 	 der Config-Datei aktivierten Regeln überprüft. Werden Bedingungen verletzt, so erfolgt eine Ausgabe im Terminal. 
	 * **vd** - Wie "v", nur dass der Verlegungsplan nach erfolgreicher Validierung in der grafischen Oberfläche angezeigt wird.
	 * **d** - Der in der XML-Datei enthaltene Verlegungsplan wird in einer grafischen Oberfläche angezeigt, 
	 	 sofern er prinzipiell gültige Werte enthält.

**Wichtig**: <br>
	 Es müssen *alle* Parameter mit angegeben werden, auch bei der Option d! Die Fugenlänge wird bei r=d ignoriert.
	 Bitte *keine Leerzeichen* zwischen Parameter und Wert einfügen!

**Beispiele**: <br>
 `java -jar ProPra.jar r=vd if="/Pfad/zur/XML Datei.xml" l=120` (Validieren und anzeigen) <br>
 `java -jar ProPra.jar r=s if=/Pfad/zur/xml_datei.xml l=110` (Nur verlegen) <br>

**Konfiguration**: <br>
Die Config-Datei befindet sich in der Main_Component im Ordner resources. Folgende Konfigurationsmöglichkeiten sind derzeit verfügbar:
- Regeln aktivieren und deaktivieren
- Loglevel setzen, Konsolenausgaben aktivieren und / oder Logging in Datei aktivieren
- Einfärbung der Fliesen bei der grafischen Ausgabe: Keine Farben, zufällige Farben für jede Fliesensorte, größte Fliese(n) rot markieren,
kleinste Fliese(n) grün markieren

Die Config-Datei wird ins JAR hineinkopiert und kann dort nur durch Entpacken / neu packen verändert werden. 
