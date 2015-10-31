package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;

public interface IPositionFinder {
	
	public Position findNextFreePosition(Composite composite, AbstractRuleChecker ruleChecker);
}
