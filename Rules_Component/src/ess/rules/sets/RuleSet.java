package ess.rules.sets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

import ess.rules.ErrorType;
import ess.rules.IRule;
import ess.rules.end.SurfaceIsFilledCompletelyRule;
import ess.rules.implicit.EntryCoversOtherTileRule;
import ess.rules.implicit.EntryExceedsSurfaceRule;
import ess.utils.ProPraProperties;
import ess.utils.PropertyException;

public class RuleSet implements IRuleSet {
	
	private static final Logger log = Logger.getGlobal();

	private LinkedList<ErrorType> errorList;
	private LinkedList<IRule> explicitRuleSet;
	private LinkedList<IRule> implicitRuleSet;
	private LinkedList<IRule> endRuleSet;

	public RuleSet() throws PropertyException {
		errorList = new LinkedList<>();
		explicitRuleSet = new LinkedList<>();
		implicitRuleSet = new LinkedList<>();
		endRuleSet = new LinkedList<>();

		addExplicitRules();
		addImplicitRules();
		addEndRules();
	}

	@Override
	public LinkedList<IRule> getExplicitRules() {
		return explicitRuleSet;
	}

	@Override
	public LinkedList<IRule> getImplicitRules() {
		return implicitRuleSet;
	}
	
	public LinkedList<IRule> getEndRules() {
		return endRuleSet;
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
		ProPraProperties properties = ProPraProperties.getInstance();
		ArrayList<String> rules = properties.getActiveRuleNames();
		try {
			for (String ruleName : rules) {
				IRule rule = (IRule) Class.forName(ruleName).newInstance();
				explicitRuleSet.add(rule);
				log.info("Activated " + rule.getClass().getSimpleName() + " ...");
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SecurityException
				| IllegalArgumentException e) {
			throw new PropertyException(
					"Invalid parameter in properties file in heuristics. Please check if your properties file is valid.", e);
		}
	}
	
	private void addImplicitRules() {
		implicitRuleSet.add(new EntryExceedsSurfaceRule());
		log.info("Activated EntryExceedsSurfaceRule ...");
		implicitRuleSet.add(new EntryCoversOtherTileRule());
		log.info("Activated EntryCoversOtherTileRule ...");
	}
	
	private void addEndRules() {
		endRuleSet.add(new SurfaceIsFilledCompletelyRule());
		log.info("Activated SurfaceIsFilledCompletelyRule ...");
	}
}
