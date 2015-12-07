package ess.ui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CustomLabel extends JLabel {

    private static final long serialVersionUID = -5011727714860521300L;

    public CustomLabel(String text, int fontSize, Color color) {
        super(text);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, fontSize));
        setOpaque(false);
        setForeground(color);
    }

}
