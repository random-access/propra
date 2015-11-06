package ess.data;

public enum Corner {
	TOP_LEFT(-1,-1), TOP_RIGHT (-1,1), BOTTOM_RIGHT(1,1), BOTTOM_LEFT (1,-1);
	
	protected int nextRowOffset, nextColOffset;
	
	private Corner(int neighbourRowOffset, int neighbourColOffset) {
		this.nextRowOffset = neighbourRowOffset;
		this.nextColOffset = neighbourColOffset;
	}
	
	public int getNextRowOffset() {
		return nextRowOffset;
	}
	
	public int getNextColOffset() {
		return nextColOffset;
	}
}

