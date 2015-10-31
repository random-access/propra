package ess.rules.helpers;


public enum Corner {
	TOP_LEFT(-1,-1), TOP_RIGHT (-1,1), BOTTOM_RIGHT(1,1), BOTTOM_LEFT (1,-1);
	
	protected int neighbourRowOffset, neighbourColOffset;
	
	private Corner(int neighbourRowOffset, int neighbourColOffset) {
		this.neighbourRowOffset = neighbourRowOffset;
		this.neighbourColOffset = neighbourColOffset;
	}
	
	public int getNeighbourRowOffset() {
		return neighbourRowOffset;
	}
	
	public int getNeighbourColOffset() {
		return neighbourColOffset;
	}
}

