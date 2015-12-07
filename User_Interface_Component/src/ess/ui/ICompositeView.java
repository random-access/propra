package ess.ui;

import java.util.List;

/**
 * Basic interface for views that display composites. It provides two different methods, one 
 * for showing a composite with additional information about broken rules
 * 
 * @author Monika Schrenk
 *
 */
public interface ICompositeView {
	
	public void display(List<String> errorList, String pathToSource, String execMode);
	
}
