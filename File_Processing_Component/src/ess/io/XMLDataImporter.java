package ess.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import ess.data.Composite;
import ess.data.Tile;
import ess.io.exc.DataExchangeException;
import ess.io.exc.InvalidSizeValueException;

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
	public Composite importComposite(String pathToSource) throws DataExchangeException {
		try {
			File xml = getFile(pathToSource);
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(xml);
			Element rootElement = doc.getRootElement();
			int cols = convertSize(rootElement.getAttributeValue(XMLValues.LENGTH_1));
			int rows = convertSize(rootElement.getAttributeValue(XMLValues.LENGTH_2));
			ArrayList<Tile> tileSorts = readTileSorts(rootElement);
			ArrayList<String> surfaceTiles = readSurfaceTiles(rootElement);
			return new Composite(rows, cols, surfaceTiles, tileSorts);
		} catch (Exception e) {
			throw new DataExchangeException(e);
		}
	}

	private File getFile(String pathToSource) throws FileNotFoundException {
		File xml = new File(pathToSource);
		if (xml.exists() && xml.isFile()) {
			return xml;
		} else {
			throw new FileNotFoundException("File \"" + pathToSource + "\" doesn't exist or is a directory!");
		}
	}

	private ArrayList<String> readSurfaceTiles(Element rootElement) {
		Element verlegungsplan = rootElement.getChild(XMLValues.VERLEGUNGSPLAN);
		List<Element> fliesen = verlegungsplan.getChildren();
		ArrayList<String> tiles = new ArrayList<>();
		for (Element elem : fliesen) {
			tiles.add(elem.getAttributeValue(XMLValues.FLIESEN_ID));
		}
		return tiles;
	}

	private ArrayList<Tile> readTileSorts(Element rootElement) throws InvalidSizeValueException {
		Element fliesentypen = rootElement.getChild(XMLValues.FLIESENTYPEN);
		List<Element> fliesen = fliesentypen.getChildren();
		ArrayList<Tile> tiles = new ArrayList<>();
		for (Element elem : fliesen) {
			String id = elem.getAttributeValue(XMLValues.IDENT);
			int length1 = convertSize(elem.getChildText(XMLValues.LENGTH_1));
			int length2 = convertSize(elem.getChildText(XMLValues.LENGTH_2));
			tiles.add(new Tile(id, length1, length2));
		}
		return tiles;
	}

	private int convertSize(String valueToConvert) throws InvalidSizeValueException {
		int externalSize = Integer.parseInt(valueToConvert);
		if (externalSize <= 0 || externalSize % XMLValues.CONVERSION_UNIT != 0) {
			throw new InvalidSizeValueException();
		}
		return externalSize / XMLValues.CONVERSION_UNIT;
	}

}
