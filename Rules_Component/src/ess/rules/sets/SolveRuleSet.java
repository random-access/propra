package ess.rules.sets;

import ess.data.Composite;
import ess.exc.PropertyException;
import ess.utils.ProPraProperties;

/**
 * This class is an extension of ValidationRuleSet that loads additional rules to 
 * optimize the runtime of a solver by eliminating tile constellations which will 
 * lead to future problems.
 * 
 * @author Monika Schrenk
 *
 */
public class SolveRuleSet extends ValidationRuleSet {
    
    private static final int BASIC_RULE_QUANTITY = 4;

    /**
     * Instantiates a SolveRuleSet.
     * @param composite the composite
     * @throws PropertyException if config.properties could not be read
     * or if it contains invalid parameters.
     */
    public SolveRuleSet(Composite composite) throws PropertyException {
        super(composite);
        addAdditionalRules(composite);
    }

    // This method adds all additional rules activated via config.properties to the the
    // RuleSet. Additional rules are only used for solving.
    private void addAdditionalRules(Composite composite) throws PropertyException {
        if (getExplicitRules().size() == BASIC_RULE_QUANTITY) {
            addRules(composite, ProPraProperties.getInstance().getAdditionalRuleNames());
        }
    }

}
