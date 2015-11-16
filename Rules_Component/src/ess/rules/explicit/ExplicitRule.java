package ess.rules.explicit;

import java.util.ArrayList;

import ess.data.Position;
import ess.rules.IRule;

public abstract class ExplicitRule implements IRule {
	
protected ArrayList<Position> errorEntries = new ArrayList<>();
	
	public void resetErrorEntries() {
		errorEntries.clear();
	}
	
	public ArrayList<Position> getErrorEntries() {
		return errorEntries;
	}
	
}
