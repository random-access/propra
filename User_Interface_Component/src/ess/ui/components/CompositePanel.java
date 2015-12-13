package ess.ui.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import ess.data.Surface;

/**
 * This class is a subclass of JComponent that can display 
 * a composite's surface.
 * 
 * It provides methods for dynamically increasing and decreasing the 
 * field size in order to make zooming possible.
 * 
 * @author Monika Schrenk
 *
 */
public class CompositePanel extends JComponent {

    private static final long serialVersionUID = 1L;
    
    // bounds for the size of a single field in pixels
    private static final int MAX_FIELD_SIZE = 50;
    private static final int MIN_FIELD_SIZE = 2;
    
    // custom background color
    private static final Color LIGHT_BLUE = new Color(225, 225, 255);
    
    // measurements for line width
    private static final int STROKE_WIDTH_BG = 1;
    private static final int STROKE_WIDTH_FG = 2 * STROKE_WIDTH_BG;
    
    // correction factors for component size and drawing
    private static final int MINOR_STROKE_CORR = STROKE_WIDTH_FG / 2;
    private static final int STROKE_CORRECTION = (int) Math.ceil(STROKE_WIDTH_FG / (double) 2);
    
    // current size of a single field in pixel
    private int currentFieldSize;

    // data to be displayed
    private Surface surface;

    /**
     * Instantiates a new CompositePanel.
     * @param surface Data to be displayed.
     * @param initialFieldSize Initial size of a single field in pixel.
     */
    public CompositePanel(Surface surface, int initialFieldSize) {
        super();
        this.currentFieldSize = initialFieldSize;
        this.surface = surface;
    }

    @Override
    public Dimension getPreferredSize() {
        int contentWidth = currentFieldSize * surface.getCols() + 2 * (STROKE_CORRECTION + MINOR_STROKE_CORR);
        int contentHeight = currentFieldSize * surface.getRows() + 2 * (STROKE_CORRECTION + MINOR_STROKE_CORR);
        return new Dimension(contentWidth, contentHeight);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        drawBackground(g2D);
        drawGrid(g2D);
        drawTiles(g2D);
    }
    
    private void drawBackground(Graphics2D g2d) {
        g2d.setColor(LIGHT_BLUE);
        g2d.fill(new Rectangle(0, 0, getWidth(), getHeight()));
    }

    private void drawTiles(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH_FG));

        // draw outer tile borders
        g2d.drawRect(STROKE_CORRECTION, STROKE_CORRECTION, currentFieldSize * surface.getCols() + STROKE_CORRECTION, 
                currentFieldSize * surface.getRows() + STROKE_CORRECTION);

        // draw inner tile borders
        for (int i = 0; i < surface.getRows(); i++) {
            for (int j = 0; j < surface.getCols(); j++) {
                // draw tile border below
                if (i + 1 < surface.getRows() && surface.getEntryAt(i, j) != surface.getEntryAt(i + 1, j)) {
                    g2d.drawLine(currentFieldSize * j + STROKE_CORRECTION, currentFieldSize * (i + 1) + STROKE_CORRECTION, 
                            currentFieldSize * (j + 1) + STROKE_CORRECTION, currentFieldSize * (i + 1) + STROKE_CORRECTION);
                }
                // draw tile border right
                if (j + 1 < surface.getCols() && surface.getEntryAt(i, j) != surface.getEntryAt(i, j + 1)) {
                    g2d.drawLine(currentFieldSize * (j + 1) + STROKE_CORRECTION, currentFieldSize * i + STROKE_CORRECTION, 
                            currentFieldSize * (j + 1) + STROKE_CORRECTION, currentFieldSize * (i + 1) + STROKE_CORRECTION);
                }

            }
        }
    }

    private void drawGrid(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.setStroke(new BasicStroke(STROKE_WIDTH_BG));

        // draw rows
        for (int i = 0; i <= surface.getRows(); i++) {
            g2D.drawLine(0, currentFieldSize * i, currentFieldSize * surface.getCols(), currentFieldSize * i);
        }
        // draw cols
        for (int i = 0; i <= surface.getCols(); i++) {
            g2D.drawLine(currentFieldSize * i, 0, currentFieldSize * i, currentFieldSize * surface.getRows());
        }
    }
    
    /**
     * Increase the size of a single field by 1 pixel and refresh the view.
     */
    public void increaseFieldSize() {
        if (currentFieldSize < MAX_FIELD_SIZE) {
            currentFieldSize++;
        }
        this.revalidate();
    }
    
    /**
     * Decrease the size of a single field by 1 pixel and refresh the view.
     */
    public void decreaseFieldSize() {
        if (currentFieldSize > MIN_FIELD_SIZE) {
            currentFieldSize--;
        }
        this.revalidate();
    }

}
