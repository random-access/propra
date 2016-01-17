package ess.algorithm;

/**
 * This interface adds the ability to display a <code>Composite</code> without any check performed. 
 * <br><br>
 * <b>Advantages</b>: Saves time for a large Composite, it is also possible to show an invalid
 * Composite, if there is no structural problem with the input itself.
 * 
 * @author monika
 *
 */
public interface DisplayableWithoutCheck {

    /**
     * Method for displaying a <code>Composite</code>, this should start a construction mechanism
     * which assembles the data from the given source.
     * @param xmlFile path to input
     */
    public void display(String xmlFile);    
}
