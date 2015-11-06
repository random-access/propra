package ess.algorithm;

import java.util.LinkedList;

import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.ITileChooser;
import ess.algorithm.modules.LargeToSmallTileChooser;
import ess.algorithm.modules.RuleChecker;
import ess.algorithm.modules.TopToBottomPosFinder;
import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.data.Tile;

public class Solver {

	private IPositionFinder posFinder;
	private RuleChecker ruleChecker;
	private ITileChooser tileChooser;

	private LinkedList<Position> posList;

	private Composite c;

	public Solver(Composite c, int maxLineLength) {
		this.c = c;
		c.setMaxLineLength(maxLineLength / 20); // TODO check
		posList = new LinkedList<>();

		// TODO read from config file
		posFinder = new TopToBottomPosFinder();
		ruleChecker = new RuleChecker();
		tileChooser = new LargeToSmallTileChooser(c);
	}

	public boolean solve() {
		Position pos = posFinder.findNextFreePosition(c, ruleChecker);
		if (pos == null) {
			return true;
		}
		Tile tile = null;
		do {
			
			// wenn schon ein eintrag vorhanden an Pos -> löschen
			SurfaceEntry entry = c.getSurface().getEntryAt(pos);
			if (entry != null) {
				tile = entry.getTile();
				c.getSurface().removeEntry(entry);
			}
			// nächste zu testende Fliese wählen
			tile = tileChooser.getNextTile(tile);
			while (tile != null) {
				System.out.println("Trying tile " + tile.getIdent() + " at " + pos + "...");
				if (placeNextTile(tile, pos)) {
					posList.add(pos);
					System.out.println(c);
					return solve();
				}
				tile = tileChooser.getNextTile(tile);
			}
			// pos auf letzten Eintrag in Liste setzen
			if (!posList.isEmpty()) {
				pos = posList.pollLast();
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

	private void removeTile(Position pos) {
		SurfaceEntry entry = c.getSurface().getEntryAt(pos);
		if (entry != null) {
			c.getSurface().removeEntry(entry);
		}
		if (posList.getLast().equals(pos)) {
			posList.removeLast();
		}
	}

}
