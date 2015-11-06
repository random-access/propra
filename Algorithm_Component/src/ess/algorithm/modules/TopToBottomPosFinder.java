package ess.algorithm.modules;

import java.util.LinkedList;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;

public class TopToBottomPosFinder implements IPositionFinder {

	@Override
	public Position findNextFreePosition(Composite composite, RuleChecker ruleChecker) {
		Surface surface = composite.getSurface();
		for (int i = 0; i < surface.getRows(); i++) {
			for (int j = 0; j < surface.getCols(); j++) {
				if (surface.getEntryAt(i, j) == null) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}	
	
}
