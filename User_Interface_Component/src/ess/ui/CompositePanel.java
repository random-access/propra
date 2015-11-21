package ess.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import ess.data.Surface;

public class CompositePanel extends JComponent {

	private static final long serialVersionUID = 1L;

	private static final int FIELD_SIZE = 20;
	private static final int STROKE_WIDTH_BG = 1;
	private static final int STROKE_WIDTH_FG = 2 * STROKE_WIDTH_BG;
	private static final int MINOR_STROKE_CORR = STROKE_WIDTH_FG/2;
	private static final int STROKE_CORRECTION =  (int) Math.ceil(STROKE_WIDTH_FG/(double)2);
	
	private Surface surface;

	public CompositePanel(Surface surface) {
		this.surface = surface;
		// System.out.println("Stroke-correction: " + STROKE_CORRECTION);
		// System.out.println("Minor stroke-correction: " + MINOR_STROKE_CORR);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(FIELD_SIZE * surface.getCols()+ 2*STROKE_CORRECTION+MINOR_STROKE_CORR, FIELD_SIZE * surface.getRows()+2*STROKE_CORRECTION+MINOR_STROKE_CORR);
	}
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(FIELD_SIZE * surface.getCols()+2*STROKE_CORRECTION+MINOR_STROKE_CORR, FIELD_SIZE * surface.getRows()+2*STROKE_CORRECTION+MINOR_STROKE_CORR);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(FIELD_SIZE * surface.getCols()+2*STROKE_CORRECTION+MINOR_STROKE_CORR, FIELD_SIZE * surface.getRows()+2*STROKE_CORRECTION+MINOR_STROKE_CORR);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		drawGrid(g2D);
		drawTiles(g2D);
	}

	private void drawTiles(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(STROKE_WIDTH_FG));
		
		// draw outer tile borders
		g2d.drawRect(STROKE_CORRECTION, STROKE_CORRECTION, FIELD_SIZE * surface.getCols()+STROKE_CORRECTION, FIELD_SIZE *surface.getRows()+STROKE_CORRECTION);
		
		// draw inner tile borders
		for (int i = 0; i < surface.getRows(); i++) {
			for (int j = 0; j < surface.getCols(); j++) {
				// draw tile border below
				if (i+1 < surface.getRows() && surface.getEntryAt(i,j) != surface.getEntryAt(i+1, j)) {
					g2d.drawLine(FIELD_SIZE * j +STROKE_CORRECTION, FIELD_SIZE *(i+1) + STROKE_CORRECTION, 
							FIELD_SIZE * (j+1) + STROKE_CORRECTION, FIELD_SIZE *(i+1) + STROKE_CORRECTION);
				}
				// draw tile border right
				if (j+1 < surface.getCols() && surface.getEntryAt(i,j) != surface.getEntryAt(i, j+1)) {
					g2d.drawLine(FIELD_SIZE * (j+1)+STROKE_CORRECTION, FIELD_SIZE*i+STROKE_CORRECTION, 
							FIELD_SIZE * (j+1)+STROKE_CORRECTION, FIELD_SIZE*(i+1)+STROKE_CORRECTION);
				}
				
			}
		}
	}

	private void drawGrid(Graphics2D g2D) {
	        g2D.setColor(Color.LIGHT_GRAY);
	        g2D.setStroke(new BasicStroke(STROKE_WIDTH_BG));
	        
	        // draw rows
	        for (int i = 0; i <= surface.getRows(); i++) {
	        	g2D.drawLine(STROKE_CORRECTION, FIELD_SIZE * i+STROKE_CORRECTION, 
	        			FIELD_SIZE * surface.getCols()+STROKE_CORRECTION, FIELD_SIZE * i + STROKE_CORRECTION);
	        }
	        // draw cols
	        for (int i = 0; i <= surface.getCols(); i++) {
	        	g2D.drawLine(FIELD_SIZE * i + STROKE_CORRECTION, STROKE_CORRECTION, 
	        			FIELD_SIZE * i + STROKE_CORRECTION, FIELD_SIZE * surface.getRows()+STROKE_CORRECTION);
	        }
	    }

}
