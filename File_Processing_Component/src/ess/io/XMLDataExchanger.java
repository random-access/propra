package ess.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import ess.data.Composite;
import ess.data.Tile;
import ess.exc.PropertyException;
import ess.io.exc.DataExchangeException;
import ess.io.exc.InvalidSizeValueException;
import ess.strings.CustomErrorMessages;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLDataExchanger.
 */
public class XMLDataExchanger implements IDataExchanger {

	private Document doc;
	
	// TODO check same tile ID

	/*
	 * (non-Javadoc)
	 * 
	 * @see ess.io.IDataExchanger#readFromSource(java.lang.String)
	 */
	@Override
	public Composite readFromSource(String pathToSource)
			throws DataExchangeException {
		try (InputStream is = transform(pathToSource, XMLValues.PATH_TO_DTD)) {
			SAXBuilder sb = new SAXBuilder(XMLReaders.DTDVALIDATING);
			doc = sb.build(is);
			Element rootElement = doc.getRootElement();
			int cols = convertSize(rootElement
					.getAttributeValue(XMLValues.LENGTH_1));
			int rows = convertSize(rootElement
					.getAttributeValue(XMLValues.LENGTH_2));
			ArrayList<Tile> tileSorts = readTileSorts(rootElement);
			ArrayList<String> surfaceTiles = readSurfaceTiles(rootElement);
			return new Composite(rows, cols, surfaceTiles, tileSorts);
		} catch (Exception e) {
			throw new DataExchangeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ess.io.IDataExchanger#writeToTarget(ess.data.Composite,
	 * java.lang.String)
	 */
	@Override
	public void writeToTarget(Composite composite, String pathToTarget)
			throws DataExchangeException {
		try (OutputStream os = new FileOutputStream(pathToTarget)) {
			// Add / modify composite part of XML file that was imported earlier
			Element rootElem = doc.getRootElement();
			Element compositeParentElem = setupCompositeParentElem(rootElem);
			addCompositeChildElems(composite, compositeParentElem);
			
			// remove DTD validation that was added while importing
			doc.getDocType().detach();
			
			// Output formatted XML (with indents & line breaks)
			XMLOutputter outputter = new XMLOutputter();
			outputter.setFormat(Format.getPrettyFormat());
			outputter.output(doc, os);
		} catch (IOException e) {
			throw new DataExchangeException(e);
		}
	}
	
	// Sets up a parent element for holding the composite, cleans up existing content
	// or inserts new parent element for composite into document
	private Element setupCompositeParentElem(Element rootElem) {
		Element compositeParentElem = rootElem.getChild(XMLValues.VERLEGUNGSPLAN);

		if (compositeParentElem == null) { // XML doesn't contain a solution yet
			compositeParentElem = new Element(XMLValues.VERLEGUNGSPLAN);
			rootElem.addContent(compositeParentElem);
		} else { // XML contains an old solution
			compositeParentElem.removeContent();
		}
		return compositeParentElem;
	}
	
	// Gets the tile list from internal data model, convert it to XML nodes and insert 
	// them into document hierarchy
	private void addCompositeChildElems(Composite composite, Element compositeElem) {
		int currentNum = 0;
		for (String s : composite.getSurfaceTileList()) {
			Element tileElem = new Element(XMLValues.PLATTE);
			tileElem.setAttribute(XMLValues.FLIESEN_ID, s);
			Element numberElem = new Element(XMLValues.NR);
			numberElem.setText(Integer.toString(currentNum++));
			tileElem.addContent(numberElem);
			compositeElem.addContent(tileElem);
		}
	}
	
	// Checks XML content against DTD by (temporarily) adding a DOCTYPE line
	// into the XML file. Throws a PropertyException on any violation
	// of the DTD definitions.
	private InputStream transform(String xmlSrc, String pathToDTD)
			throws PropertyException {
		try {
			String dtdLocation = this.getClass().getResource(pathToDTD)
					.toString();
			locateFile(xmlSrc);
			// TODO check paths elsewhere && output appropriate exceptions
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer tr = tf.newTransformer();
			tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdLocation);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Result res = new StreamResult(baos);
			tr.transform(new StreamSource(xmlSrc), res);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (TransformerConfigurationException e) {
			throw new PropertyException(
					CustomErrorMessages.ERROR_VALIDATING_XML);
		} catch (TransformerException e) {
			throw new PropertyException(String.format(
					CustomErrorMessages.ERROR_INVALID_CONTENT, xmlSrc));
		}
	}
	
	// Checks if XML file exists and is readable, otherwise a
	// PropertyException gets thrown.
	private void locateFile(String xmlSrc) throws PropertyException {
		if (!Files.isRegularFile(Paths.get(xmlSrc))) {
			throw new PropertyException(String.format(
					CustomErrorMessages.ERROR_PATH_NOT_FOUND, "\"" + xmlSrc
							+ "\""));
		}
	}

	private ArrayList<String> readSurfaceTiles(Element rootElement) {
		Element verlegungsplan = rootElement.getChild(XMLValues.VERLEGUNGSPLAN);
		ArrayList<String> tiles = new ArrayList<>();
		if (verlegungsplan == null) {
			return tiles;
		}
		List<Element> fliesen = verlegungsplan.getChildren();
		for (Element elem : fliesen) {
			tiles.add(elem.getAttributeValue(XMLValues.FLIESEN_ID));
		}
		return tiles;
	}

	// Converts tile sorts from XML into internal data model.
	// Throws an InvalidSizeValueException if a length element holds an invalid value
	private ArrayList<Tile> readTileSorts(Element rootElement) throws InvalidSizeValueException {
		Element fliesentypen = rootElement.getChild(XMLValues.FLIESENTYPEN);
		List<Element> fliesen = fliesentypen.getChildren();
		ArrayList<Tile> tiles = new ArrayList<>();
		for (Element elem : fliesen) {
			String id = elem.getAttributeValue(XMLValues.IDENT);
			int cols = convertSize(elem.getChildText(XMLValues.LENGTH_1));
			int rows = convertSize(elem.getChildText(XMLValues.LENGTH_2));
			tiles.add(new Tile(id, rows, cols));
		}
		return tiles;
	}
	
	// Converts a length value into the internal data model
	// Throws an InvalidSizeValueException if valueToConvert is not a positive integer 
	// or if valueToConvert can not be exactly divided by 20.
	private int convertSize(String valueToConvert)
			throws InvalidSizeValueException {
		try {
			int externalSize = Integer.parseInt(valueToConvert);
			if (externalSize <= 0 || externalSize % XMLValues.CONVERSION_UNIT != 0) {
				throw new InvalidSizeValueException(CustomErrorMessages.ERROR_INVALID_DATATYPE_TILE_LENGTH);
			}
			return externalSize / XMLValues.CONVERSION_UNIT;
		} catch (NumberFormatException e) {
			throw new InvalidSizeValueException(CustomErrorMessages.ERROR_INVALID_DATATYPE_TILE_LENGTH);
		}
		
	}

}
