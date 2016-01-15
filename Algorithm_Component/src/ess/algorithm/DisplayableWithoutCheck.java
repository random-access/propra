package ess.algorithm;

/**
 * This interface adds the ability to display a composite without any check performed. 
 * Advantages: Saves time for large composites, it is also possible to show invalid
 * composites, if there is no structural problem with the input itself.
 * 
 * @author monika
 *
 */
public interface DisplayableWithoutCheck {

    /**
     * Method for displaying a composite, this should start a construction mechanism
     * which assembles the data from the given source to a composite.
     * @param xmlFile path to input
     */
    public void display(String xmlFile);
    
}
