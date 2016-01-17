package ess.ui.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * This class provides a customized <code>JPanel</code> with modifiable background color and a border.
 * 
 * @author Monika Schrenk
 *
 */
public class CustomPanel extends JPanel {

    private static final long serialVersionUID = 9033530591528898532L;
    
    /**
     * Instantiates a new <code>CustomPanel</code> which inherits the background color from its 
     * parent and has the given padding in pixel.
     * @param padding Padding width in pixel.
     */
    public CustomPanel(int padding) {
        super();
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        setOpaque(false);
    }
    
    /**
     * Instantiates a new <code>CustomPanel<code> which has its own background color and a border with
     * the given padding in pixel.
     * @param color The background color.
     * @param padding Padding width in pixel.
     */
    public CustomPanel(Color color, int padding) {
        super();
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        setBackground(color);
    }
}
