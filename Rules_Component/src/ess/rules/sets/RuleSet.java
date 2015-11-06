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
	private LinkedList<IRule> explicitRuleSet;
	private LinkedList<IRule> implicitRuleSet;
	
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
	public LinkedList<IRule>  getExplicitRules() {
		return explicitRuleSet;
	}

	@Override
	public LinkedList<IRule> getImplicitRules() {
		return implicitRuleSet;
	}
	
	@Override
	public void addError(ErrorType errorType) {
		if (!errorList.contains(errorType)) {
			errorList.add(errorType);
		}
	}

	@Override
	public LinkedList<ErrorType> getErrorList() {
		return errorList;
	}

}
