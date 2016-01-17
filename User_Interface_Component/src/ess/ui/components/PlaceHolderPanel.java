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
 * CompositePanel if there is no valid composite that can be displayed.
 * 
 * This component is zoomable to some extent.
 * 
 * @author monika
 *
 */
public class PlaceHolderPanel extends JComponent implements Zoomable {

    private static final long serialVersionUID = 1417835783573413637L;
    
    private static final int MAX_ZOOM_FACTOR = 150;
    private static final int MIN_ZOOM_FACTOR = 50;
    
    private static final int INITIAL_LENGTH = 200;
    private static final int INITIAL_ZOOM_FACTOR = 100;
    
    private int currentLength;
    private int currentZoomFactor;
    

    /**
     * Instantiates a PlaceHolderPanel with a default size of 200 pixel
     * in height and width.
     */
    public PlaceHolderPanel() {
        currentLength = INITIAL_LENGTH;
        currentZoomFactor = INITIAL_ZOOM_FACTOR;
    }
    
    /** 
     * Returns the preferred size of the placeholder.
     * 
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(currentLength * currentZoomFactor / 100, currentLength * currentZoomFactor / 100);
    }

    /**
     * Returns the minimum size of the placeholder, which is the same as its preferred size.
     * 
     * @see javax.swing.JComponent#getMinimumSize()
     */
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /** 
     * Returns the maximum size of the placeholder, which is the same as its preferred size.
     * 
     * @see javax.swing.JComponent#getMaximumSize()
     */
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
     
    
    /**
     * Paints the surface of the composite.
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        drawSadSmiley(g2D);
    }
    
    
    private void drawSadSmiley(Graphics2D g2D) {
        g2D.setColor(Color.GRAY);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setStroke(new BasicStroke(7));
        AffineTransform zoom = new AffineTransform();
        zoom.scale((double)currentZoomFactor / 100, (double)currentZoomFactor / 100);
        g2D.transform(zoom);
        g2D.drawOval((currentLength / 2) - 65, (currentLength / 2) - 65, 130, 130);
        g2D.fillOval((currentLength / 2) - 35, (currentLength / 2) - 25, 15, 15);
        g2D.fillOval((currentLength / 2) + 20, (currentLength / 2) - 25, 15, 15);
        g2D.drawArc((currentLength / 2) - 30, (currentLength / 2) + 15, 60, 60, 30, 120);
        
        
    }
    
    /**
     * Increase the size of this component by ZOOM_STEP pixel and refresh the view.
     */
    public void zoomIn() {
        if (currentZoomFactor < MAX_ZOOM_FACTOR) {
            currentZoomFactor++;
        }
        this.revalidate();
    }
    
    /**
     * Decrease the size of this component by ZOOM_STEP pixel and refresh the view.
     */
    public void zoomOut() {
        if (currentZoomFactor > MIN_ZOOM_FACTOR) {
            currentZoomFactor--;
        }
        this.revalidate();
    }


}
