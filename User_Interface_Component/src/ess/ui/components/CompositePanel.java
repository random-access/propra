package ess.ui.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import ess.data.Composite;
import ess.data.Surface;
import ess.exc.PropertyException;
import ess.strings.CustomErrorMessages;
import ess.utils.ProPraProperties;

/**
 * This class is a subclass of <code>JComponent</code> that can display 
 * a Composite's Surface.
 * 
 * It provides methods for dynamically increasing and decreasing the 
 * field size in order to make zooming possible.
 * 
 * @author Monika Schrenk
 *
 */
public class CompositePanel extends JComponent implements Zoomable {

    private static final long serialVersionUID = -4131400974188958938L;
    
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
    
    // How to color the tiles, parsed from config
    private ColorStrategy currentColorStrategy;
    private TileColorMapper colorMapper;

    
    public enum ColorStrategy {
        NO_COLOR,
        SMALLEST_TILE_GREEN,
        LARGEST_TILE_RED,
        RANDOM_COLOR
    }
    
    /**
     * Instantiates a new CompositePanel.
     * @param surface Data to be displayed.
     * @param initialFieldSize Initial size of a single field in pixel.
     */
    public CompositePanel(Composite composite, int initialFieldSize) {
        super();
        this.currentFieldSize = initialFieldSize;
        this.surface = composite.getSurface();
        currentColorStrategy = parseColorStrategy();
        colorMapper = TileColorMapper.getInstance(composite.getTileSorts(), currentColorStrategy);
    }

    /** 
     * Returns the preferred size of this Component, which equals the size of the background grid.
     * 
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        int contentWidth = currentFieldSize * surface.getCols() + 2 * (STROKE_CORRECTION + MINOR_STROKE_CORR);
        int contentHeight = currentFieldSize * surface.getRows() + 2 * (STROKE_CORRECTION + MINOR_STROKE_CORR);
        return new Dimension(contentWidth, contentHeight);
    }

    /**
     * Returns the minimum size of this Component grid which is equal to its preferred size.
     * 
     * @see javax.swing.JComponent#getMinimumSize()
     */
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /** 
     * Returns the maximum size of this Component which is equal to its preferred size.
     * 
     * @see javax.swing.JComponent#getMaximumSize()
     */
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    /**
     * Paints the Surface of the Composite.
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        drawComposite(g2D);
    }
    
    // Read the color strategy from config file & return the corresponding ColorStrategy enum value.
    private ColorStrategy parseColorStrategy() {
        try {
            ProPraProperties properties = ProPraProperties.getInstance();
            String colorStrategyName = properties.getValue(ProPraProperties.KEY_COLORING);
            return ColorStrategy.valueOf(colorStrategyName.toUpperCase());
        } catch (PropertyException e) {
            System.out.println(CustomErrorMessages.ERROR_INVALID_VALUE_COLORING);
        }
        return ColorStrategy.NO_COLOR;
    }
    
    // Draw the whole composite
    private void drawComposite(Graphics2D g2D) {
        drawBackground(g2D);
        drawGrid(g2D);
        drawTiles(g2D);
    }
    
    // Set the background color.
    private void drawBackground(Graphics2D g2d) {
        g2d.setColor(LIGHT_BLUE);
        g2d.fill(new Rectangle(0, 0, getWidth(), getHeight()));
    }

    // Draws the background grid.
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

    // Draw tile borders & colorize tiles
    private void drawTiles(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH_FG));

        // draw outer tile borders
        g2d.drawRect(STROKE_CORRECTION, STROKE_CORRECTION, currentFieldSize * surface.getCols() + STROKE_CORRECTION, 
                currentFieldSize * surface.getRows() + STROKE_CORRECTION);

        // draw inner tile borders
        for (int i = 0; i < surface.getRows(); i++) {
            for (int j = 0; j < surface.getCols(); j++) {
                colorizeTile(g2d, i, j);
                drawTileBorders(g2d, i, j);
            }
        }
    }

    // Draws the inner tile borders of a single field in the grid
    private void drawTileBorders(Graphics2D g2d, int i, int j) {
        // draw tile border below
        if (i + 1 < surface.getRows() && surface.getEntryAt(i, j) != surface.getEntryAt(i + 1, j)) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(currentFieldSize * j + STROKE_CORRECTION, currentFieldSize * (i + 1) + STROKE_CORRECTION, 
                    currentFieldSize * (j + 1) + STROKE_CORRECTION, currentFieldSize * (i + 1) + STROKE_CORRECTION);
        }
        // draw tile border right
        if (j + 1 < surface.getCols() && surface.getEntryAt(i, j) != surface.getEntryAt(i, j + 1)) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(currentFieldSize * (j + 1) + STROKE_CORRECTION, currentFieldSize * i + STROKE_CORRECTION, 
                    currentFieldSize * (j + 1) + STROKE_CORRECTION, currentFieldSize * (i + 1) + STROKE_CORRECTION);
        }
    }

    // Draws the background color of a single field in the grid
    private void colorizeTile(Graphics2D g2d, int i, int j) {
        switch (currentColorStrategy) {
            case SMALLEST_TILE_GREEN:
                if (surface.getSmallestTiles().contains(surface.getEntryAt(i, j))) {
                    drawTile(g2d, i, j, colorMapper.getColor(surface.getEntryAt(i, j)));
                }
                break;
            case RANDOM_COLOR:
                drawTile(g2d, i, j, colorMapper.getColor(surface.getEntryAt(i, j)));
                break;
            case LARGEST_TILE_RED:
                if (surface.getLargestTiles().contains(surface.getEntryAt(i, j))) {
                    drawTile(g2d, i, j, colorMapper.getColor(surface.getEntryAt(i, j)));
                }
                break;
            case NO_COLOR:
                // do nothing
        }

    }
    
    // Method for drawing a single tile
    private void drawTile(Graphics2D g2d, int i, int j, Color color) {
        g2d.setColor(color);
        g2d.fillRect(currentFieldSize * j + STROKE_CORRECTION + 1, currentFieldSize * i + STROKE_CORRECTION + 1, currentFieldSize-1, currentFieldSize-1);
    }

    /**
     * Increases the size of a single field by 1 pixel and refreshes the view.
     */
    public void zoomIn() {
        if (currentFieldSize < MAX_FIELD_SIZE) {
            currentFieldSize++;
        }
        this.revalidate();
    }
    
    /**
     * Decreases the size of a single field by 1 pixel and refreshes the view.
     */
    public void zoomOut() {
        if (currentFieldSize > MIN_FIELD_SIZE) {
            currentFieldSize--;
        }
        this.revalidate();
    }
}
