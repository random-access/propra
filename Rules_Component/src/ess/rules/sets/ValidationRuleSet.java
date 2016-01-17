package ess.rules.sets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

import ess.data.Composite;
import ess.exc.PropertyException;
import ess.rules.ErrorType;
import ess.rules.IRule;
import ess.rules.endconditions.SurfaceIsFilledCompletelyRule;
import ess.rules.implicit.TileCoversOtherTileRule;
import ess.rules.implicit.TileExceedsSurfaceRule;
import ess.utils.CustomLogger;
import ess.utils.ProPraProperties;

/**
 * This class is an implementation of <code>IRuleSet</code> that loads the explicit
 * rules that were activated in config.properties. <br>
 * Additionally, it activates all implicit rules and all end conditions.
 * 
 * @author Monika Schrenk
 *
 */
public class ValidationRuleSet implements IRuleSet {
	
	private final Logger logger = CustomLogger.getLogger();

	private LinkedList<ErrorType> errorList;
	private LinkedList<IRule> explicitRuleSet;
	private LinkedList<IRule> implicitRuleSet;
	private LinkedList<IRule> endConditionSet;

	/**
	 * Instantiates a <code>ValidationRuleSet</code>.
	 * @param composite the Composite that will be checked against this rule set
	 * @throws PropertyException if config.properties could not be read
	 * or if it contains invalid parameters.
	 */
    public ValidationRuleSet(Composite composite) throws PropertyException {
		errorList = new LinkedList<>();
		explicitRuleSet = new LinkedList<>();
		implicitRuleSet = new LinkedList<>();
		endConditionSet = new LinkedList<>();

		addExplicitRules(composite);
		addImplicitRules(composite);
		addEndConditions(composite);
	}

	/* (non-Javadoc)
	 * @see ess.rules.sets.IRuleSet#getExplicitRules()
	 */
	@Override
	public LinkedList<IRule> getExplicitRules() {
		return explicitRuleSet;
	}

	/* (non-Javadoc)
	 * @see ess.rules.sets.IRuleSet#getImplicitRules()
	 */
	@Override
	public LinkedList<IRule> getImplicitRules() {
		return implicitRuleSet;
	}
	
	/* (non-Javadoc)
	 * @see ess.rules.sets.IRuleSet#getEndConditions()
	 */
	@Override
	public LinkedList<IRule> getEndConditions() {
		return endConditionSet;
	}
	

	/* (non-Javadoc)
	 * @see ess.rules.sets.IRuleSet#addError(ess.rules.ErrorType)
	 */
	@Override
	public void addError(ErrorType errorType) {
		if (!errorList.contains(errorType)) {
			errorList.add(errorType);
		}
	}

	/* (non-Javadoc)
	 * @see ess.rules.sets.IRuleSet#getErrorList()
	 */
	@Override
	public LinkedList<ErrorType> getErrorList() {
		return errorList;
	}

	// Used for adding all explicit rules that were activated via config.properties to this RuleSet.
	private void addExplicitRules(Composite composite) throws PropertyException {
	    addRules(composite, ProPraProperties.getInstance().getExplicitRuleNames());
	}
	
	// Method for adding rules specified by their type name in config.properties
	protected void addRules(Composite composite, ArrayList<String> ruleNames) throws PropertyException {
	    try {
            for (String ruleName : ruleNames) {
                Constructor<?> constructor = Class.forName(ruleName).getConstructor(Composite.class);
                IRule rule = (IRule) constructor.newInstance(composite);
                explicitRuleSet.add(rule);
                logger.info("Activated " + rule.getClass().getSimpleName() + " ...");
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SecurityException
                | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            throw new PropertyException(
                    "Invalid parameter in properties file in heuristics. Please check if your properties file is valid.");
        }
	}
	
	// Used for adding all implicit rules to this RuleSet.
	private void addImplicitRules(Composite composite) {
		implicitRuleSet.add(new TileExceedsSurfaceRule(composite));
		logger.info("Activated EntryExceedsSurfaceRule ...");
		implicitRuleSet.add(new TileCoversOtherTileRule(composite));
		logger.info("Activated EntryCoversOtherTileRule ...");
	}
	
	private void addEndConditions(Composite composite) {
		endConditionSet.add(new SurfaceIsFilledCompletelyRule(composite));
		logger.info("Activated SurfaceIsFilledCompletelyRule ...");
	}
}
