package ess.ui.components;
/**
 * This interface is for making a <code>JComponent</code> zoomable.
 * 
 * It provides a method for zooming in and one for zooming out, which must be implemented 
 * with the logic of the <code>JComponent</code>.
 * 
 * @author Monika Schrenk
 *
 */
public interface Zoomable {

    /**
     * Method for zooming in, which makes the component larger.
     */
    void zoomIn();
    
    /**
     * Method for zooming out, which makes the component smaller.
     */
    void zoomOut();
}
