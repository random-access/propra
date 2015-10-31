package ess.algorithm;

import java.util.LinkedList;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.algorithm.modules.AbstractRuleChecker;
import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.TopToBottomPosFinder;
import ess.algorithm.modules.ValidationRuleChecker;
import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.data.Tile;
import ess.rules.IRule;
import ess.rules.TileExceedsSurfaceRule;

public class Validator {
	
	private boolean isValidated;
	private SurfaceEntry[][] surface;
	private IPositionFinder posFinder;
	private AbstractRuleChecker ruleChecker;
	private IRule tileExceedsSurfaceRule;
	
	public Validator() {
		posFinder = new TopToBottomPosFinder();
		ruleChecker = new ValidationRuleChecker();
		tileExceedsSurfaceRule = new TileExceedsSurfaceRule();
	}

	public void validateSolution(Composite c) {
		fillSurface(c);
		isValidated = true;
	}
	
	/**
	 * This method returns the surface, a 2 dimensional String array
	 * @return
	 */
	public SurfaceEntry[][] getSurface() {
		return surface;
	}
	
	/**
	 * This method returns the list of errors found while validating. 
	 * If validation never got executed, a list with all errors gets returned.
	 * @return a list of errors found while validating or a list with all errors if validation was not executed
	 */
	public LinkedList<Validation> getErrorList() {
		return isValidated? ruleChecker.getErrorList() : ruleChecker.getListWithAllErrors();
	}

	private void fillSurface(Composite c) {
		Position pos = null;
		Tile tile = null;
		for (String ident : c.getSurfaceTileList()) {
			pos = posFinder.findNextFreePosition(c, ruleChecker);
			tile = c.findTileById(ident);
			
			// TODO check if there is still space in the surface to place a tile
			
			// check if tile can be placed without exceeding the surface borders
			ruleChecker.checkRule(tileExceedsSurfaceRule, Validation.FLIESE_UNPASSEND, c, tile, pos);
			
			SurfaceEntry e = new SurfaceEntry(tile, pos);
			
			c.getSurface().insertEntry(e);
			
			
		}
		// TODO check if there are any empty positions left
	}
}
