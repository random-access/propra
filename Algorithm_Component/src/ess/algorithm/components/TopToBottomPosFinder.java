package ess.algorithm.components;

import ess.data.Position;
import ess.data.SurfaceUtils;

public class TopToBottomPosFinder implements IPositionFinder {

	@Override
	public Position findNextFreePosition(String[][] surface, AbstractRuleChecker ruleChecker) {
		for (int i = 0; i < surface.length; i++) {
			for (int j = 0; j < surface[0].length; j++) {
				if (surface[i][j].equals(SurfaceUtils.INIT_VALUE)) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}
}
