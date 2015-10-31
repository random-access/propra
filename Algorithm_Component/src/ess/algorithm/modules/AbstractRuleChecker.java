package ess.algorithm.modules;

import java.util.EnumSet;
import java.util.LinkedList;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.IRule;

public abstract class AbstractRuleChecker {
	
	protected LinkedList<Validation> errorList = new LinkedList<>();
	
	protected LinkedList<IRule> stepRules = new LinkedList<>();
	
	public abstract void loadRules();
	
	public abstract void checkRule(IRule rule, Validation validation, Composite composite, Tile tile, Position pos);
	
	protected void addError(Validation v) {
		if (!errorList.contains(v)) {
			errorList.add(v);
		}
	}
	
	public LinkedList<Validation> getListWithAllErrors() {
		return new LinkedList<Validation>(EnumSet.allOf(Validation.class));
	}
	
	public LinkedList<Validation> getErrorList() {
		return errorList;
	}
	
}
