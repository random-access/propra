package ess.rules.sets;

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
      * @throws PropertyException if config.properties could not be read
     * or if it contains invalid parameters.
     */
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
