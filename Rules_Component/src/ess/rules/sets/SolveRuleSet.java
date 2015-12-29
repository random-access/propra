package ess.rules.sets;

import ess.exc.PropertyException;
import ess.utils.ProPraProperties;

public class SolveRuleSet extends ValidationRuleSet {
    
    private static final int BASIC_RULE_QUANTITY = 4;

    public SolveRuleSet() throws PropertyException {
        super();
        addAdditionalRules();
    }

    private void addAdditionalRules() throws PropertyException {
        if (getExplicitRules().size() == BASIC_RULE_QUANTITY) {
            addRules(ProPraProperties.getInstance().getAdditionalRuleNames());
        }
    }

}
