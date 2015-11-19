package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.IRule;
import ess.rules.sets.ErrorType;

public class MaxLineLengthRule implements IRule {
			
	@Override
	public boolean check(Composite c, Tile tile, Position pos) {
		for (Edge edge : Edge.values()) {
			if (calculateLineLength(c, tile, pos, edge) > c.getMaxLineLength()) {
				return false;
			}
		}
		return true;
	}

	private int calculateLineLength(Composite c, Tile tile, Position pos, Edge edge) {
		Corner c1 = edge.getFirstCorner();
		Corner c2 = edge.getSecondCorner();
		
		Position backStep = new Position(c1.getNextRowOffset() - edge.getNextRowOffset(), c1.getNextColOffset() - edge.getNextColOffset());
		Position fwdStep = new Position(c2.getNextRowOffset() - edge.getNextRowOffset(), c2.getNextColOffset() - edge.getNextColOffset()); 
		
		Position p1 = c.getSurface().getCornerPos(tile,  pos, c1);
		Position p2 = c.getSurface().getCornerPos(tile,  pos, c2);

		return getLineLength(c,edge,p1,backStep) + getLineLength(c,edge,p2,fwdStep) + getEntryLength(edge, tile);
	}
	
	private int getEntryLength(Edge edge, Tile t) {
		switch (edge) {
		case TOP: 
		case BOTTOM:
			return t.getCols();
		case LEFT:
		case RIGHT:
			return t.getRows();
		default:
			throw new IllegalArgumentException("Not a valid edge!");
		}
	}

	private int getLineLength(Composite c, Edge edge, Position startPos, Position step) {
		Surface s = c.getSurface();
		Position currentInsidePos = new Position(startPos.getRow() + step.getRow(), startPos.getCol() + step.getCol());
		int currentLineLength = 0;
		boolean isLine = true;
		while (s.isInsideSurface(currentInsidePos) && isLine) {
			Position currentOutsidePos = new Position(currentInsidePos.getRow() + edge.getNextRowOffset(), currentInsidePos.getCol() + edge.getNextColOffset());
			
			Tile insideTile = c.getSurface().getEntryAt(currentInsidePos);
			Tile outsideTile = c.getSurface().getEntryAt(currentOutsidePos);
			if (!s.isInsideSurface(currentOutsidePos) || insideTile == null && outsideTile == null || insideTile != null && outsideTile != null && 
					insideTile == outsideTile) {
				isLine = false;
			} else {
				currentLineLength++;
			}
			currentInsidePos.setRow(currentInsidePos.getRow() + step.getRow());
			currentInsidePos.setColumn(currentInsidePos.getCol() + step.getCol());
		}
		return currentLineLength;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.MAX_LINE_LENGTH;
	}

}
