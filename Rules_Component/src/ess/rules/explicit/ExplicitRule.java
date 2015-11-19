package ess.rules.explicit;

import java.util.ArrayList;

import ess.data.Position;
import ess.rules.IRule;

public abstract class ExplicitRule implements IRule {
	
	// TODO use list to localize the errors or remove it
	
	protected ArrayList<Position> errorEntries = new ArrayList<>();
	
	public void resetErrorEntries() {
		errorEntries.clear();
	}
	
	public ArrayList<Position> getErrorEntries() {
		return errorEntries;
	}
	
}
