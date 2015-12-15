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
	
    /**
     * This method gets called when a composite should be displayed.
     * 
     * @param errorList A list of error messages to display. 
     * @param pathToSource The path to the file the composite's data was read from.
     * @param execMode A message explaining the execution mode that lead to displaying a composite.
     */
	void display(List<String> errorList, String pathToSource, String execMode);
	
}
