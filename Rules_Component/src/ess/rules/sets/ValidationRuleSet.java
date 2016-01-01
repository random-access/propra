package ess.rules.sets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

import ess.exc.PropertyException;
import ess.rules.ErrorType;
import ess.rules.IRule;
import ess.rules.endconditions.SurfaceIsFilledCompletelyRule;
import ess.rules.implicit.TileCoversOtherTileRule;
import ess.rules.implicit.TileExceedsSurfaceRule;
import ess.utils.ProPraProperties;

/**
 * This class is an implementation of IRuleSet that loads the explicit
 * rules that were activated in config.properties. <br>
 * Additionally, it activates all implicit rules and all end conditions.
 * 
 * @author Monika Schrenk
 *
 */
public class ValidationRuleSet implements IRuleSet {
	
	private static final Logger LOG = Logger.getGlobal();

	private LinkedList<ErrorType> errorList;
	private LinkedList<IRule> explicitRuleSet;
	private LinkedList<IRule> implicitRuleSet;
	private LinkedList<IRule> endConditionSet;

	/**
	 * Instantiates a ValidationRuleSet.
	 * @throws PropertyException if config.properties could not be read
	 * or if it contains invalid parameters.
	 */
    public ValidationRuleSet() throws PropertyException {
		errorList = new LinkedList<>();
		explicitRuleSet = new LinkedList<>();
		implicitRuleSet = new LinkedList<>();
		endConditionSet = new LinkedList<>();

		addExplicitRules();
		addImplicitRules();
		addEndConditions();
	}

	@Override
	public LinkedList<IRule> getExplicitRules() {
		return explicitRuleSet;
	}

	@Override
	public LinkedList<IRule> getImplicitRules() {
		return implicitRuleSet;
	}
	
	@Override
	public LinkedList<IRule> getEndConditions() {
		return endConditionSet;
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

	private void addExplicitRules() throws PropertyException {
	    addRules(ProPraProperties.getInstance().getExplicitRuleNames());
	}
	
	protected void addRules(ArrayList<String> ruleNames) throws PropertyException {
	    try {
            for (String ruleName : ruleNames) {
                IRule rule = (IRule) Class.forName(ruleName).newInstance();
                explicitRuleSet.add(rule);
                // LOG.info("Activated " + rule.getClass().getSimpleName() + " ...");
                System.out.println("Activated " + rule.getClass().getSimpleName() + " ...");
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SecurityException
                | IllegalArgumentException e) {
            throw new PropertyException(
                    "Invalid parameter in properties file in heuristics. Please check if your properties file is valid.");
        }
	}
	
	private void addImplicitRules() {
		implicitRuleSet.add(new TileExceedsSurfaceRule());
		LOG.info("Activated EntryExceedsSurfaceRule ...");
		implicitRuleSet.add(new TileCoversOtherTileRule());
		LOG.info("Activated EntryCoversOtherTileRule ...");
	}
	
	private void addEndConditions() {
		endConditionSet.add(new SurfaceIsFilledCompletelyRule());
		LOG.info("Activated SurfaceIsFilledCompletelyRule ...");
	}
}
