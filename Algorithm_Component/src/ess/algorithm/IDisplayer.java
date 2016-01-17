package ess.algorithm;


/**
 * <code>IDisplayer</code> is the basic interface for preparing an output of Roman Composites that can
 * be displayed and should be implemented by all classes that want to display Roman Composites. 
 * It defines a single method, {@link #constructOutput()}. Implement this method using your own logic.
 * <br><br>
 * Currently an instance of <code>Displayer</code> gets initialized directly in {@link RoemischerVerbund}, 
 * so changing the object type there changes the Displayer that will be used.
 * 
 * @author Monika Schrenk
 */
public interface IDisplayer {

    /**
     * Method for preparing data for display output. This method should display all input, only taking 
     * care that there are no exceptions regarding the data structure that is used.
     */
    public void constructOutput();
}
