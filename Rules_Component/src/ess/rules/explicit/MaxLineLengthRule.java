package ess.rules.explicit;

import java.util.logging.Logger;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.SurfaceEntry;
import ess.rules.sets.ErrorType;

public class MaxLineLengthRule extends ExplicitRule{
	
	private static final Logger log = Logger.getGlobal();
			
	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		
		for (Edge edge : Edge.values()) {
			int lineLength = calculateLineLength(c,e,edge);
			if (lineLength > c.getMaxLineLength()) {
				log.fine("Max. line length exceeded, value is " + lineLength);
				return false;
			}
		}
		return true;
	}

	private int calculateLineLength(Composite c, SurfaceEntry e, Edge edge) {
		Corner c1 = edge.getFirstCorner();
		Corner c2 = edge.getSecondCorner();
		
		Position backStep = new Position(c1.getNextRowOffset() - edge.getNextRowOffset(), c1.getNextColOffset() - edge.getNextColOffset());
		Position fwdStep = new Position(c2.getNextRowOffset() - edge.getNextRowOffset(), c2.getNextColOffset() - edge.getNextColOffset()); 
		
		Position p1 = e.getCorner(c1);
		Position p2 = e.getCorner(c2);

		return getLineLength(c,edge,p1,backStep) + getLineLength(c,edge,p2,fwdStep) + getEntryLength(edge,e);
	}
	
	private int getEntryLength(Edge edge, SurfaceEntry e) {
		switch (edge) {
		case TOP: 
		case BOTTOM:
			return e.getTile().getCols();
		case LEFT:
		case RIGHT:
			return e.getTile().getRows();
		default:
			throw new IllegalArgumentException("Not a valid edge!");
		}
	}

	private int getLineLength(Composite c, Edge edge, Position startPos, Position step) {
		Surface s = c.getSurface();
		Position currentInsidePos = new Position(startPos.getRow() + step.getRow(), startPos.getColumn() + step.getColumn());
		int currentLineLength = 0;
		boolean isLine = true;
		while (s.isInsideSurface(currentInsidePos) && isLine) {
			Position currentOutsidePos = new Position(currentInsidePos.getRow() + edge.getNextRowOffset(), currentInsidePos.getColumn() + edge.getNextColOffset());
			
			SurfaceEntry insideEntry = c.getSurface().getEntryAt(currentInsidePos);
			SurfaceEntry outsideEntry = c.getSurface().getEntryAt(currentOutsidePos);
			if (!s.isInsideSurface(currentOutsidePos) || insideEntry == null && outsideEntry == null || insideEntry != null && outsideEntry != null && 
					insideEntry.equals(outsideEntry)) {
				isLine = false;
			} else {
				currentLineLength++;
			}
			currentInsidePos.setRow(currentInsidePos.getRow() + step.getRow());
			currentInsidePos.setColumn(currentInsidePos.getColumn() + step.getColumn());
		}
		return currentLineLength;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.MAX_LINE_LENGTH;
	}

}
