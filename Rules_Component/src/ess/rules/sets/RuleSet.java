package ess.rules.sets;

import java.util.LinkedList;

import ess.data.Composite;
import ess.data.SurfaceEntry;
import ess.rules.IRule;
import ess.rules.explicit.CrossingsRule;
import ess.rules.explicit.ExplicitRule;
import ess.rules.explicit.MaxLineLengthRule;
import ess.rules.explicit.ReplacableTileRule;
import ess.rules.explicit.SameTileRule;
import ess.rules.implicit.EntryCoversOtherTileRule;
import ess.rules.implicit.EntryExceedsSurfaceRule;
import ess.rules.implicit.ImplicitRule;

public class RuleSet implements IRuleSet {
	
	private LinkedList<ErrorType> errorList;
	private LinkedList<ExplicitRule> explicitRuleSet;
	private LinkedList<ImplicitRule> implicitRuleSet;
	
	public RuleSet() {
		errorList = new LinkedList<>();
		explicitRuleSet = new LinkedList<>();
		implicitRuleSet = new LinkedList<>();
		
		// TODO read from config file
		explicitRuleSet.add(new CrossingsRule());
		explicitRuleSet.add(new SameTileRule());
		explicitRuleSet.add(new ReplacableTileRule());
		explicitRuleSet.add(new MaxLineLengthRule());
		
		implicitRuleSet.add(new EntryExceedsSurfaceRule());
		implicitRuleSet.add(new EntryCoversOtherTileRule());
	}
	
	@Override
	public boolean checkExplicitRules(Composite c, SurfaceEntry e) {
		boolean validMove = true;
		for (IRule rule : explicitRuleSet) {
			boolean ok = rule.check(c, e);
			if (!ok) {
				addError(rule.getErrorType());
			}
			validMove &= ok;
		}
		return validMove;
	}

	@Override
	public boolean checkImplicitRules(Composite c, SurfaceEntry e) {
		boolean validMove = true;
		for (IRule rule : implicitRuleSet) {
			boolean ok = rule.check(c, e);
			if (!ok) {
				addError(rule.getErrorType());
			}
			validMove &= ok;
		}
		return validMove;
	}
	
	private void addError(ErrorType errorType) {
		if (!errorList.contains(errorType)) {
			errorList.add(errorType);
		}
	}

	@Override
	public LinkedList<ErrorType> getErrorList() {
		return errorList;
	}

}
