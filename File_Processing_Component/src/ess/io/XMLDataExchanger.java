package ess.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import ess.algorithm.exception.InvalidTilePosException;
import ess.data.Composite;
import ess.data.Tile;
import ess.io.exc.DataExchangeException;
import ess.io.exc.InvalidTileSizeException;

public class XMLDataExchanger implements IDataExchanger {
	
	private static final String LENGTH_1 = "length1";
	private static final String LENGTH_2 = "length2";
	
	private static final String FLIESENTYPEN = "Fliesentypen";
	private static final String VERLEGUNGSPLAN = "Verlegungsplan";
	
	private static final String IDENT = "ident";
	private static final String FLIESEN_ID = "fliesenId";

	public XMLDataExchanger() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Composite readFromSource(String pathToSource) throws DataExchangeException {
		try {
			File xml = new File(pathToSource);
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(xml);
			Element rootElement = doc.getRootElement();
			int width = Integer.parseInt(rootElement.getAttributeValue(LENGTH_1));
			int height = Integer.parseInt(rootElement.getAttributeValue(LENGTH_2));
			Tile[] tileSorts = readTileSorts(rootElement);
			String[] surfaceTiles = readSurfaceTiles(rootElement);
			return new XMLDataImporter().importComposite(width, height, tileSorts, surfaceTiles);
			
		} catch (JDOMException | IOException | NumberFormatException | 
				InvalidTileSizeException | InvalidTilePosException e) {
			throw new DataExchangeException(e);
		}
		
	}

	private String[] readSurfaceTiles(Element rootElement) {
		Element verlegungsplan = rootElement.getChild(VERLEGUNGSPLAN);
		List<Element> fliesen = verlegungsplan.getChildren();
		String[] tiles = new String[fliesen.size()];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = fliesen.get(i).getAttributeValue(FLIESEN_ID);
		}
		return tiles;
	}

	private Tile[] readTileSorts(Element rootElement) {
		Element fliesentypen = rootElement.getChild(FLIESENTYPEN);
		List<Element> fliesen = fliesentypen.getChildren();
		Tile[] tiles = new Tile[fliesen.size()];
		for (int i = 0; i < tiles.length; i++) {
			String id = fliesen.get(i).getAttributeValue(IDENT);
			int length1 = Integer.parseInt(fliesen.get(i).getChildText(LENGTH_1));
			int length2 = Integer.parseInt(fliesen.get(i).getChildText(LENGTH_2));
			tiles[i] = new Tile(id, length1, length2);
		}
		return tiles;
	}

	@Override
	public void writeToTarget(Composite composite, String pathToTarget) throws DataExchangeException {
		// TODO Auto-generated method stub

	}

}
