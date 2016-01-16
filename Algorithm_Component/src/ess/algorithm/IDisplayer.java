package ess.algorithm;


/**
 * <code>IDisplayer</code> is the basic interface for preparing a displayable output of Roman Composites
 * and should be implemented by all classes that want to display Roman Composites. 
 * It defines a single method, {@link #constructOutput()}. Implement this method using your own logic.
 * Currently the concrete <code>Displayer</code> gets instantiated directly in
 * {@link RoemischerVerbund}, so changing the object type there changes the
 * <code>Displayer</code> that will be used.
 * 
 * @author Monika Schrenk
 */
public interface IDisplayer {

    /**
     * Method for preparing data for displayable output. This method 
     * should display all input, only taking care that there are 
     * no exceptions regarding the data structure that is used.
     */
    public void constructOutput();
}
