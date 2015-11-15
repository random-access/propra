package ess.algorithm;

import java.util.EnumSet;
import java.util.LinkedList;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.ValidationRuleChecker;
import ess.algorithm.modules.TopToBottomPosFinder;
import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.data.Tile;
import ess.utils.PropertyException;

public class Validator {
	
	private SurfaceEntry[][] surface;
	private IPositionFinder posFinder;
	private ValidationRuleChecker ruleChecker;
	private LinkedList<Validation> errorList = new LinkedList<>();
	
	public Validator() throws PropertyException {
		errorList.addAll(EnumSet.allOf(Validation.class));
		posFinder = new TopToBottomPosFinder();
		ruleChecker = new ValidationRuleChecker();
	}

	public void validateSolution(Composite c, int maxLineLenght) {
		c.setMaxLineLength(maxLineLenght / 20); // TODO check if valid
		fillSurface(c);
		errorList = ruleChecker.getErrorList();
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
		return errorList;
	}

	private void fillSurface(Composite c) {
		Position pos = null;
		Tile tile = null;
		for (String ident : c.getSurfaceTileList()) {
			pos = posFinder.findNextFreePosition(c, pos);
			tile = c.findTileById(ident);
			
			if (ruleChecker.checkImplicitRules(c, tile, pos)) {
				c.getSurface().insertEntry(tile, pos);
				ruleChecker.checkExplicitRules(c, tile, pos);
			} else {
				return;
			}
		}
	}
}
