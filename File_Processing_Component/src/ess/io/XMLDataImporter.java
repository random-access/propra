package ess.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

import ess.data.Composite;
import ess.data.Tile;
import ess.io.exc.DataExchangeException;
import ess.io.exc.InvalidSizeValueException;
import ess.utils.PropertyException;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLDataImporter.
 */
public class XMLDataImporter {

	/**
	 * Import composite.
	 *
	 * @param cols
	 *            the width
	 * @param rows
	 *            the height
	 * @param tileSorts
	 *            the tile sorts
	 * @param surfaceTiles
	 *            the surface tiles
	 * @return the composite
	 * @throws InvalidSizeValueException
	 *             the invalid tile size exception
	 * @throws InvalidTilePosException
	 *             the invalid tile pos exception
	 */
	public Composite importComposite(String xmlSrc)
			throws DataExchangeException {
		try (InputStream is = transform(xmlSrc, XMLValues.PATH_TO_DTD)) {
			SAXBuilder sb = new SAXBuilder(XMLReaders.DTDVALIDATING);
			Document doc = sb.build(is);
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

	private InputStream transform(String xmlSrc, String pathToDTD) throws PropertyException {
		try {
			String dtdLocation = this.getClass().getResource(pathToDTD)
					.toString();
			// locateFiles(xmlSrc, dtdLocation);
			// TODO check paths elsewhere && output appropriate exceptions
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer tr = tf.newTransformer();
			tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdLocation);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Result res = new StreamResult(baos);
			tr.transform(new StreamSource(xmlSrc), res);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (TransformerConfigurationException e) {
			//TODO change message to something more meaningful
			throw new PropertyException("TransformerConfigurationException"); 
		} catch (TransformerException e) {
			//TODO change message to something more meaningful
			throw new PropertyException("TransformerException");
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

	private ArrayList<Tile> readTileSorts(Element rootElement)
			throws InvalidSizeValueException {
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

	private int convertSize(String valueToConvert)
			throws InvalidSizeValueException {
		int externalSize = Integer.parseInt(valueToConvert);
		if (externalSize <= 0 || externalSize % XMLValues.CONVERSION_UNIT != 0) {
			throw new InvalidSizeValueException();
		}
		return externalSize / XMLValues.CONVERSION_UNIT;
	}
}
