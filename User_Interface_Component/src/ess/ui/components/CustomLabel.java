package ess.ui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * This class provides a customized label with modifiable font and foreground color, allowing 
 * the background of the parent component to paint parts of this label.<br>
 * The advantage of using this component is that parameters that get modified in this 
 * application can be passed directly into the constructor.
 * 
 * @author Monika Schrenk
 *
 */
public class CustomLabel extends JLabel {

    private static final long serialVersionUID = -5011727714860521300L;

    /**
     * Instantiates a new CustomLabel
     * @param text Text that should be displayed.
     * @param fontSize Size of the text in points.
     * @param color Color of the text.
     */
    public CustomLabel(String text, int fontSize, Color color) {
        super(text);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, fontSize));
        setOpaque(false);
        setForeground(color);
    }

}
