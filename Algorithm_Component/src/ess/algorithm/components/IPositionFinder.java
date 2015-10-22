package ess.algorithm.components;

import ess.data.Position;

public interface IPositionFinder {
	
	public Position findNextFreePosition(String[][] surface, AbstractRuleChecker ruleChecker);
}
