package ess.algorithm;

import java.util.LinkedList;
import java.util.logging.Logger;

import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.IRuleChecker;
import ess.algorithm.modules.ITileChooser;
import ess.algorithm.modules.LargeToSmallTileChooser;
import ess.algorithm.modules.SolveRuleChecker;
import ess.algorithm.modules.TopToBottomPosFinder;
import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.data.Tile;

public class Solver {
	
	private static final Logger log = Logger.getGlobal();

	private IPositionFinder posFinder;
	private IRuleChecker ruleChecker;
	private ITileChooser tileChooser;

	private LinkedList<Position> posList;

	private Composite c;

	public Solver(Composite c, int maxLineLength) {
		this.c = c;
		c.setMaxLineLength(maxLineLength / 20); // TODO check
		posList = new LinkedList<>();

		// TODO read from config file
		posFinder = new TopToBottomPosFinder();
		ruleChecker = new SolveRuleChecker();
		tileChooser = new LargeToSmallTileChooser(c);
	}
	
	
	public boolean solve() {
		Position pos = null;
		Tile tile= null;
		do {
			if (pos == null) {
				pos = posFinder.findNextFreePosition(c, ruleChecker);
				if (pos == null) {
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
			tile = tileChooser.getNextTile(tile);
			boolean foundTileThatFits = false;
			while (tile != null && !foundTileThatFits) {
				log.info("Trying tile " + tile.getIdent() + " at " + pos + "...");
				if (placeNextTile(tile, pos)) {
					posList.add(pos);
					log.info(c.toString());
					foundTileThatFits = true;
					pos = null;
				} else {
					tile = tileChooser.getNextTile(tile);
				}
			}
			if (!foundTileThatFits) {
				if (!posList.isEmpty()) {
					pos = posList.pollLast();
					log.info("Return to pos " + pos + "...");
				} 
			}
		} while (!posList.isEmpty());
		return false;
	}

	private boolean placeNextTile(Tile tile, Position pos) {
		SurfaceEntry entry = new SurfaceEntry(tile, pos);
		if (ruleChecker.checkImplicitRules(c, entry)) {
			c.getSurface().insertEntry(entry);
			if (ruleChecker.checkExplicitRules(c, entry)) {
				return true;
			}
			c.getSurface().removeEntry(entry);
		}
		return false;
	}

}
