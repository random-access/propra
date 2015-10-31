package rulesets;

import java.util.LinkedList;

import ess.data.Composite;
import ess.data.SurfaceEntry;
import ess.rules.IRule;
import ess.rules.NoCrossingsRule;
import ess.rules.TileExceedsSurfaceRule;

public class ValidatorRuleSet implements IRuleSet {
	
	private LinkedList<IRule> ruleSet;
	
	public ValidatorRuleSet() {
		// TODO read from config file
		ruleSet.add(new TileExceedsSurfaceRule());
		ruleSet.add(new NoCrossingsRule());
	}

	@Override
	public boolean checkRules(Composite c, SurfaceEntry e) {
		boolean validMove = true;
		for (IRule rule : ruleSet) {
			validMove &= rule.check(c, e);
		}
		return validMove;
	}

	@Override
	public LinkedList<IRule> getRuleSet() {
		return ruleSet;
	}

}
