package ess.data;

// TODO Javadoc
public enum Edge {
	TOP(-1, 0, Corner.TOP_LEFT, Corner.TOP_RIGHT), 
	LEFT(0, -1, Corner.TOP_LEFT, Corner.BOTTOM_LEFT), 
	BOTTOM(1, 0, Corner.BOTTOM_LEFT, Corner.BOTTOM_RIGHT), 
	RIGHT(0, 1, Corner.TOP_RIGHT, Corner.BOTTOM_RIGHT);
	
	private int nextRowOffset, nextColOffset;
	private Corner firstCorner, secondCorner;
	
	private Edge(int nextRowOffset, int nextColOffset, Corner firstCorner, Corner secondCorner) {
		this.nextRowOffset = nextRowOffset;
		this.nextColOffset = nextColOffset;
		this.firstCorner = firstCorner;
		this.secondCorner = secondCorner;
	}

	public int getNextRowOffset() {
		return nextRowOffset;
	}

	public int getNextColOffset() {
		return nextColOffset;
	}

	public Corner getFirstCorner() {
		return firstCorner;
	}

	public Corner getSecondCorner() {
		return secondCorner;
	}

}
