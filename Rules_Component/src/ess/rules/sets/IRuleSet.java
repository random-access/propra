package ess.rules.sets;

import java.util.LinkedList;

import ess.rules.IRule;

public interface IRuleSet {
	
	public LinkedList<IRule> getExplicitRules();
	
	public LinkedList<IRule> getImplicitRules();
	
	public LinkedList<ErrorType> getErrorList ();

	public void addError(ErrorType errorType);

}
