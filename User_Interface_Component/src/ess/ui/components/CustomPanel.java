package ess.ui.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * This class provides a customized label with modifiable background color and a border.
 * 
 * @author Monika Schrenk
 *
 */
public class CustomPanel extends JPanel {

    private static final long serialVersionUID = 9033530591528898532L;
    
    /**
     * Instantiates a new CustomPanel which inherits the background color from its 
     * parent and has a border of width padding.
     * @param padding Border width.
     */
    public CustomPanel(int padding) {
        super();
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        setOpaque(false);
    }
    
    /**
     * Instantiates a new CustomPanel which has its own background color a border of width padding.
     * @param color The background color.
     * @param padding Border width.
     */
    public CustomPanel(Color color, int padding) {
        super();
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        setBackground(color);
    }

}
