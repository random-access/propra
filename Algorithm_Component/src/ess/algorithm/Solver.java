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
import ess.data.Tile;
import ess.utils.ProPraLogger;
import ess.utils.ProPraProperties;
import ess.utils.PropertyException;

// TODO: Auto-generated Javadoc
/**
 * The Class Solver.
 */
public class Solver implements ISolver{
	
	/** The Constant log. */
	private static final Logger log = Logger.getGlobal();

	/** The pos finder. */
	private IPositionFinder posFinder;
	
	/** The rule checker. */
	private IRuleChecker ruleChecker;
	
	/** The tile chooser. */
	private ITileChooser tileChooser;

	/** The pos list. */
	private LinkedList<Position> posList;
	
	/** The counter. */
	private long counter;
	
	/** The c. */
	private Composite c;
	
	/**
	 * Instantiates a new solver.
	 *
	 * @param c the c
	 * @param maxLineLength the max line length
	 * @throws PropertyException the property exception
	 */
	public Solver(Composite c, int maxLineLength) throws PropertyException {
		ProPraLogger.setup();
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
	
	/* (non-Javadoc)
	 * @see ess.algorithm.ISolver#solve()
	 */
	@Override
	public boolean solve() {
		Position pos = null;
		Tile tile= null;
		
		// try to place tiles using backtracking as long as there are any possibilities
		do {
			if (pos == null) {
				// trying to fill the next free position after successfully placing a tile
				pos = posFinder.findNextFreePosition(c, pos);
				if (pos == null) {
					log.info("Iterations: " + counter);
					log.info("Found a solution :) \n" + c);
					return true;
				}
				tile = null;
			} else {
				// after not finding any possible tile, the last tile has to be removed from surface
				// remember this tile to be able to choose the next one below
				tile = c.getSurface().getEntryAt(pos);
				c.getSurface().removeEntry(tile, pos);
			}
			tile = tileChooser.getNextTile(pos, tile);
			boolean foundTileThatFits = false;
			
			// try out all possible tiles at the current position
			while (tile != null && !foundTileThatFits) {
				log.fine("Trying tile " + tile.getId() + " at " + pos + "...");
				if (placeNextTile(tile, pos)) {
					posList.add(pos);
					foundTileThatFits = true;
					pos = null;
				} else {
					tile = tileChooser.getNextTile(pos, tile);
				}
			}
			
			// retrieve last position from posList, if no possible tile 
			// could be found at the current position
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
	
	// try to insert tile at pos
	// check all rules, return true if tile could be placed, else false
	private boolean placeNextTile(Tile tile, Position pos) {
		if (ruleChecker.checkImplicitRules(c, tile, pos) && ruleChecker.checkExplicitRules(c, tile, pos)) {
			c.getSurface().insertEntry(tile, pos);
			counter++;
			return true;
		}
		return false;
	}

}
