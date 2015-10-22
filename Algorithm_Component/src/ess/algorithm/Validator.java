package ess.algorithm;

import java.util.LinkedList;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.algorithm.components.IPositionFinder;
import ess.algorithm.components.AbstractRuleChecker;
import ess.algorithm.components.TopToBottomPosFinder;
import ess.algorithm.components.ValidationRuleChecker;
import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceUtils;
import ess.data.Tile;
import ess.data.TileUtils;
import ess.rules.IRule;
import ess.rules.SurfaceTooLargeRule;
import ess.rules.SurfaceTooSmallRule;
import ess.rules.TileExceedsSurfaceRule;

public class Validator {
	
	private boolean isValidated;
	private String[][] surface;
	private IPositionFinder posFinder;
	private AbstractRuleChecker ruleChecker;
	private IRule surfaceTooSmallRule, tileExceedsSurfaceRule, surfaceTooLargeRule;
	
	public Validator() {
		posFinder = new TopToBottomPosFinder();
		ruleChecker = new ValidationRuleChecker();
		surfaceTooSmallRule = new SurfaceTooSmallRule();
		tileExceedsSurfaceRule = new TileExceedsSurfaceRule();
		surfaceTooLargeRule = new SurfaceTooLargeRule();
	}

	public void validateSolution(Composite c) {
		surface = SurfaceUtils.initSurface(c.getRows(), c.getCols());
		fillSurface(c);
		isValidated = true;
	}
	
	/**
	 * This method returns the surface, a 2 dimensional String array
	 * @return
	 */
	public String[][] getSurface() {
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
			pos = posFinder.findNextFreePosition(surface, ruleChecker);
			tile = TileUtils.findTileByIdent(c.getTileSorts(), ident);
			
			// check if there is still space in the surface to place a tile
			ruleChecker.checkRule(surfaceTooSmallRule, Validation.FLIESE_UNPASSEND, surface, tile, pos);
			// check if tile can be placed without exceeding the surface borders
			ruleChecker.checkRule(tileExceedsSurfaceRule, Validation.FLIESE_UNPASSEND, surface, tile, pos);
			
			
			SurfaceUtils.insertTile(surface, tile, pos);
			
			
		}
		// check if there are empty positions left
		ruleChecker.checkRule(surfaceTooLargeRule, Validation.FLIESE_UNPASSEND, surface, tile, pos);
	}
}
