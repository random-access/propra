package ess.algorithm;

import java.util.List;
import java.util.Observable;

import ess.data.Composite;

/**
 * This class should be extended by any class that wants to display a <code>Composite</code> or information about it 
 * (including error messages) depending on the result of a <code>Solver</code> or <code>Validator</code>. 
 * The <code>Observer</code> pattern is used because the main component doesn't know if a solution could be found or is valid
 * and the <code>Solver</code> or <code>Validator</code> doesn't know if the user started the application with the option 
 * "display" or not. 
 * 
 * @author Monika Schrenk
 *
 */
public abstract class AbstractOutputObservable extends Observable {
	
    /**
     * Get the <code>Composite</code> to be able to display it. <br><br>
     * 
     * Please make sure to call this after the <code>Solver</code> / the <code>Validator has finished its algorithm.
     * @return a Composite
     */
	public abstract Composite getComposite();
	
	/**
	 * Get the error list to print out any errors that were found. 
	 * The <code>String</code> array contains an info message for each error. 
	 * It doesn't contain duplicate messages. The error list gets only filled during validation.<br><br>
	 * Please make sure to call this method after the <code>Validator</code> has finished validation.
	 * @return a <code>String</code> list containing info messages for every rule that was discovered to be broken at least once 
	 * during Validation.
	 */
	public abstract List<String> getErrors();
	
	/**
	 * Indicates if a valid solution for a <code>Composite</code> could be found or the given data
	 * contains a valid composite. 
	 * @return true if a valid solution could be found, else false
	 */
	public abstract boolean hasValidComposite();
	
	/**
	 * Get the file path to the file which holds the data.
	 * @return the file path
	 */
	public abstract String getPathToSource();

}
