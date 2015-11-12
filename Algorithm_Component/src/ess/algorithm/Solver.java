package ess.algorithm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.logging.Logger;

import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.IRuleChecker;
import ess.algorithm.modules.ITileChooser;
import ess.algorithm.modules.SolveRuleChecker;
import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.data.Tile;
import ess.utils.PropertyException;
import ess.utils.ProPraProperties;

public class Solver {
	
	private static final Logger log = Logger.getGlobal();

	private IPositionFinder posFinder;
	private IRuleChecker ruleChecker;
	private ITileChooser tileChooser;

	private LinkedList<Position> posList;
	private long counter;
	private Composite c;

	public Solver(Composite c, int maxLineLength) throws PropertyException {
		this.c = c;
		c.setMaxLineLength(maxLineLength / 20); // TODO check
		posList = new LinkedList<>();
		loadModules();
	}
	
	private void loadModules() throws PropertyException {
		try {
			// load properties from file
			ProPraProperties properties = ProPraProperties.getInstance();
			
			// initialize selected implementation of IPositionFinder
			String posFinderName = ProPraProperties.HEURISTICS_PACKAGE + properties.getValue(ProPraProperties.KEY_POSITION_FINDER);
			posFinder = (IPositionFinder)Class.forName(posFinderName).newInstance();
			
			
			// initialize RuleChecker
			ruleChecker = new SolveRuleChecker();
			
			// initialize selected implementation of ITileChooser
			String tileChooserName = ProPraProperties.HEURISTICS_PACKAGE + properties.getValue(ProPraProperties.KEY_TILE_CHOOSER);
			Constructor<?> constructor = Class.forName(tileChooserName).getConstructor(Composite.class);
			tileChooser = (ITileChooser)constructor.newInstance(c);
		
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException 
			| NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			throw new PropertyException("Invalid parameter in properties file in heuristics. Please check if your properties file is valid.", e);
		}
	}
	
	
	public boolean solve() {
		Position pos = null;
		Tile tile= null;
		do {
			if (pos == null) {
				pos = posFinder.findNextFreePosition(c, pos);
				if (pos == null) {
					log.info("Iterations: " + counter);
					log.info("Found a solution :) \n" + c);
					return true;
				}
				tile = null;
			} else {
				SurfaceEntry entry = c.getSurface().getEntryAt(pos);
				if (entry != null) {
					tile = entry.getTile();
					c.getSurface().removeEntry(entry);
				}
			}
			tile = tileChooser.getNextTile(pos, tile);
			boolean foundTileThatFits = false;
			while (tile != null && !foundTileThatFits) {
				log.fine("Trying tile " + tile.getIdent() + " at " + pos + "...");
				if (placeNextTile(tile, pos)) {
					posList.add(pos);
					log.fine(c.toString());
					foundTileThatFits = true;
					pos = null;
				} else {
					tile = tileChooser.getNextTile(pos, tile);
				}
			}
			if (!foundTileThatFits) {
				if (!posList.isEmpty()) {
					pos = posList.pollLast();
					log.fine("Return to pos " + pos + "...");
				} 
			}
		} while (!posList.isEmpty());
		log.info("Iterations: " + counter);
		log.info("Found no solution :(.");
		return false;
	}

	private boolean placeNextTile(Tile tile, Position pos) {
		SurfaceEntry entry = new SurfaceEntry(tile, pos);
		if (ruleChecker.checkImplicitRules(c, entry)) {
			c.getSurface().insertEntry(entry);
			if (ruleChecker.checkExplicitRules(c, entry)) {
				counter++;
				return true;
			}
			c.getSurface().removeEntry(entry);
		}
		return false;
	}

}
