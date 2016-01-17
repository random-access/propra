package ess.algorithm;

import java.util.List;
import java.util.Observable;

import ess.data.Composite;

/**
 * This class should be extended by any class that wants to display a <code>Composite</code> or information about it 
 * (including error messages) depending on the result of a <code>Solver</code> or <code>Validator</code>. 
 * The Observer pattern is used because the main component doesn't know if a solution could be found or is valid
 * and the Solver or Validator doesn't know if the user started the application with the option "display" or not. 
 * 
 * @author Monika Schrenk
 *
 */
public abstract class AbstractOutputObservable extends Observable {
    
    /**
     * Gets a <code>String</code> holding an error message to display if a composite contains invalid data
     * or any other Exception happens, which makes it impossible to finish solving / validating / displaying a
     * Composite 
     * @return String with an error message
     */
    public abstract String getErrorMessage();
	
    /**
     * Gets the <code>Composite</code> to be able to display it. <br><br>
     * 
     * Please make sure to call this after the <code>Solver</code> or <code>Validator</code> has finished its algorithm.
     * @return a Composite
     */
	public abstract Composite getComposite();
	
	/**
	 * Gets the error list to print out any errors that were found. 
	 * The list that is returned contains an info message for each error. 
	 * It doesn't contain duplicate messages. The error list gets only filled during validation.<br><br>
	 * Please make sure to call this method after the <code>Validator</code> has finished validation.
	 * @return a list containing info messages for every rule that was discovered to be broken at least once 
	 * during Validation.
	 */
	public abstract List<String> getErrorList();
	
	/**
	 * Indicates if a valid solution for a <code>Composite</code> could be found or the given data
	 * contains a valid composite. 
	 * @return <code>true</code> if a valid solution could be found, else <code>false</code>.
	 */
	public abstract boolean hasValidComposite();
	
	/**
	 * Get the file path to the file which holds the data.
	 * @return the file path
	 */
	public abstract String getPathToSource();
}
