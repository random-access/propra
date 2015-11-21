package ess.algorithm;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.TopToBottomPosFinder;
import ess.algorithm.modules.ValidationRuleChecker;
import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.utils.ProPraLogger;
import ess.utils.PropertyException;

public class Validator {
	
	private Composite composite;
	private IPositionFinder posFinder;
	private ValidationRuleChecker ruleChecker;
	private LinkedList<Validation> errorList = new LinkedList<>();
	
	public Validator(Composite composite, int maxLineLength) throws PropertyException {
		ProPraLogger.setup();
		errorList.addAll(EnumSet.allOf(Validation.class));
		posFinder = new TopToBottomPosFinder();
		ruleChecker = new ValidationRuleChecker();
		this.composite = composite;
		composite.setMaxLineLength(maxLineLength / 20);  // TODO check if valid
	}

	public List<Validation> validateSolution() {
		fillSurface(composite);
		return ruleChecker.getErrorList();
	}

	private void fillSurface(Composite c) {
		Position pos = null;
		Tile tile = null;
		for (String ident : c.getSurfaceTileList()) {
			pos = posFinder.findNextFreePosition(c, pos);
			tile = c.findTileById(ident);
			if (ruleChecker.checkImplicitRules(c, tile, pos)) {
				ruleChecker.checkExplicitRules(c, tile, pos);
				c.getSurface().insertEntry(tile, pos);
			} else {
				return;
			}
		}
	}
}
