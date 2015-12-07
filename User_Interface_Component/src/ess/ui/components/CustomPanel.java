package ess.ui.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {

    private static final long serialVersionUID = 9033530591528898532L;
    
    public CustomPanel(int padding) {
        super();
        setBorder(BorderFactory.createEmptyBorder(padding, padding,padding, padding));
        setOpaque(false);
    }
    
    public CustomPanel(Color color, int padding) {
        super();
        setBorder(BorderFactory.createEmptyBorder(padding, padding,padding, padding));
        setBackground(color);
    }

}
