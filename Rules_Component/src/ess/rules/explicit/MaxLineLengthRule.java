package ess.rules.explicit;

import ess.data.Composite;
import ess.data.Corner;
import ess.data.Edge;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.rules.ErrorType;
import ess.rules.IRule;

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
		Position currentInnerPos = new Position(startPos.getRow() + step.getRow(), startPos.getCol() + step.getCol());
		int currentLineLength = 0;
		boolean isLine = true;
		while (s.isInsideSurface(currentInnerPos) && isLine) {
			Position currentOuterPos = new Position(currentInnerPos.getRow() + edge.getNextRowOffset(), currentInnerPos.getCol() + edge.getNextColOffset());
			
			Tile innerTile = c.getSurface().getEntryAt(currentInnerPos);
			Tile outerTile = c.getSurface().getEntryAt(currentOuterPos);
			if (!s.isInsideSurface(currentOuterPos) || innerTile == null && outerTile == null || innerTile != null && outerTile != null && 
					innerTile == outerTile) {
				isLine = false;
			} else {
				currentLineLength++;
			}
			currentInnerPos.setRow(currentInnerPos.getRow() + step.getRow());
			currentInnerPos.setColumn(currentInnerPos.getCol() + step.getCol());
		}
		return currentLineLength;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.MAX_LINE_LENGTH;
	}

}
