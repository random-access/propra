package ess.algorithm;

import java.util.List;
import java.util.Observable;

import ess.data.Composite;

/**
 * This class should be extended by any class that wants to display a composite, if a valid solution exists. 
 * The observer pattern is used because the main component doesn't know if the Solver finds a solution or not 
 * and the Solver / Validator doesn't know if the user started the application with the option "display" or not. 
 * 
 * @author monika
 *
 */
public abstract class AbstractOutputObservable extends Observable {
	
    /**
     * Get the composite to be able to display it. <br><br>
     * 
     * Please make sure to call this after the Solver / the Validator has finished its algorithm.
     * @return a Composite
     */
	public abstract Composite getComposite();
	
	/**
	 * Get the error list to print out any errors that were found, the String array contains an info message for each 
	 * rule that was broken. The String array doesn't contain duplicate messages. The error list gets only filled 
	 * during validation.<br><br>
	 * Please make sure to call this method after the Validator has finished validation.
	 * @return a String list containing info messages for every rule that was discovered to be broken at least once 
	 * during Validation.
	 */
	public abstract List<String> getErrors();
	
	/**
	 * Get the file path to the file which holds the data.
	 * @return the file path
	 */
	public abstract String getPathToSource();

}
