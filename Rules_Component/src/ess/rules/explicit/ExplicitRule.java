package ess.rules.explicit;

import java.util.ArrayList;

import ess.data.SurfaceEntry;
import ess.rules.IRule;

public abstract class ExplicitRule implements IRule {
	
protected ArrayList<SurfaceEntry> errorEntries = new ArrayList<>();
	
	public void resetErrorEntries() {
		errorEntries.clear();
	}
	
	public ArrayList<SurfaceEntry> getErrorEntries() {
		return errorEntries;
	}
	
}
