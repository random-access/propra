package ess.ui.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

/**
 * This component displays a sad smiley. It can be used as a placeholder for 
 * <code>CompositePanel</code> if there is no valid Composite that can be displayed.
 * 
 * This component is zoomable to some extent.
 * 
 * @author monika
 *
 */
public class PlaceHolderPanel extends JComponent implements Zoomable {

    private static final long serialVersionUID = 1417835783573413637L;
    
    // component zooming, initially: 100%, else: between 50 and 150%
    private static final int INITIAL_ZOOM_FACTOR = 100;
    private static final int MAX_ZOOM_FACTOR = 150;
    private static final int MIN_ZOOM_FACTOR = 50;
    
    // original width and height of the component 
    private static final int LENGTH = 200;
    
    // width of the smiley's stroke
    private static final int STROKE_WIDTH = 7;
    
    // converts percent values in floats
    private static final int PERCENT = 100;
    
    // measurements of circle (head outline)
    private static final int CIRCLE_WIDTH = 130;
    
    // measurements of ovals (eyes)
    private static final int OVAL_WIDTH = 15;
    private static final int OVAL_LEFT_X_OFFSET = 35;
    private static final int OVAL_RIGHT_X_OFFSET = 20;
    private static final int OVAL_Y_OFFSET = 25;
    
    // measurements of arc (mouth)
    private static final int ARC_X_OFFSET = 30;
    private static final int ARC_Y_OFFSET = 15;
    private static final int ARC_WIDTH = 60;
    private static final int ARC_BEGIN_ANGLE = 30;
    private static final int ARC_ANGLE_LENGTH = 120;

    private int currentZoomFactor;
    
    /**
     * Instantiates a <code>PlaceHolderPanel</code> with a default size of 200 pixel
     * in height and width.
     */
    public PlaceHolderPanel() {
        currentZoomFactor = INITIAL_ZOOM_FACTOR;
    }
    
    /** 
     * Returns the preferred size of this component 
     * 
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(LENGTH * currentZoomFactor / PERCENT, LENGTH * currentZoomFactor / PERCENT);
    }

    /**
     * Returns the minimum size of this component, which is the same as its preferred size.
     * 
     * @see javax.swing.JComponent#getMinimumSize()
     */
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /** 
     * Returns the maximum size of this component, which is the same as its preferred size.
     * 
     * @see javax.swing.JComponent#getMaximumSize()
     */
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
     
    
    /**
     * Paints the surface of the Composite.
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        drawSadSmiley(g2D);
    }
    
    // method for drawing the smiley 
    private void drawSadSmiley(Graphics2D g2D) {
        g2D.setColor(Color.GRAY);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setStroke(new BasicStroke(STROKE_WIDTH));
        AffineTransform zoom = new AffineTransform();
        zoom.scale((double) currentZoomFactor / PERCENT, (double) currentZoomFactor / PERCENT);
        g2D.transform(zoom);
        g2D.drawOval((LENGTH / 2) - (CIRCLE_WIDTH / 2), (LENGTH / 2) - (CIRCLE_WIDTH / 2), 
                CIRCLE_WIDTH, CIRCLE_WIDTH);
        g2D.fillOval((LENGTH / 2) - OVAL_LEFT_X_OFFSET, (LENGTH / 2) - OVAL_Y_OFFSET, 
                OVAL_WIDTH, OVAL_WIDTH);
        g2D.fillOval((LENGTH / 2) + OVAL_RIGHT_X_OFFSET, (LENGTH / 2) - OVAL_Y_OFFSET, 
                OVAL_WIDTH, OVAL_WIDTH);
        g2D.drawArc((LENGTH / 2) - ARC_X_OFFSET, (LENGTH / 2) + ARC_Y_OFFSET, 
                ARC_WIDTH, ARC_WIDTH, ARC_BEGIN_ANGLE, ARC_ANGLE_LENGTH); 
    }
    
    /**
     * Increase the size of this component by <code>ZOOM_STEP</code> pixel and refresh the view.
     */
    public void zoomIn() {
        if (currentZoomFactor < MAX_ZOOM_FACTOR) {
            currentZoomFactor++;
        }
        this.revalidate();
    }
    
    /**
     * Decrease the size of this component by <code>ZOOM_STEP</code> pixel and refresh the view.
     */
    public void zoomOut() {
        if (currentZoomFactor > MIN_ZOOM_FACTOR) {
            currentZoomFactor--;
        }
        this.revalidate();
    }
}
