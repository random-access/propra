package ess.rules.helpers;


public enum Direction {
	
	TOP(-1, 0, Corner.TOP_LEFT, Corner.TOP_RIGHT), 
	RIGHT(0, 1, Corner.TOP_RIGHT, Corner.BOTTOM_RIGHT), 
	BOTTOM(1, 0, Corner.BOTTOM_RIGHT, Corner.BOTTOM_LEFT), 
	LEFT(0, -1, Corner.BOTTOM_LEFT, Corner.TOP_LEFT);
	
	protected int neighbourColOffset, neighbourRowOffset;
	protected Corner corner1, corner2;
	
	private Direction(int neighbourRowOffset, int neighbourColOffset, Corner corner1, Corner corner2) {
		this.neighbourRowOffset = neighbourRowOffset;
		this.neighbourColOffset = neighbourColOffset;
		this.corner1 = corner1;
		this.corner2 = corner2;
	}
	
}