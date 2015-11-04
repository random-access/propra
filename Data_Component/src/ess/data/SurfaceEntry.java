package ess.data;

public class SurfaceEntry {

	private Tile tile;
	private Position topLeft, topRight, bottomLeft, bottomRight;

	public SurfaceEntry(Tile tile, Position pos) {
		this(tile, pos, Corner.TOP_LEFT);
	}

	public SurfaceEntry(Tile tile, Position pos, Corner corner) {
		this.tile = tile;
		this.topLeft = getTopLeft(tile, pos, corner);
		this.topRight = new Position(topLeft.getRow(), topLeft.getColumn() + tile.getCols() - 1);
		this.bottomLeft = new Position(topLeft.getRow() + tile.getRows() - 1, topLeft.getColumn());
		this.bottomRight = new Position(topLeft.getRow() + tile.getRows() - 1, topLeft.getColumn() + tile.getCols() - 1);
			
	}

	public Tile getTile() {
		return tile;
	}

	public Position getCorner(Corner c) {
		switch (c) {
		case TOP_RIGHT:
			return topRight;
		case BOTTOM_LEFT: 
			return bottomLeft;
		case BOTTOM_RIGHT: 
			return bottomRight;
		default:
			return topLeft;

		}
	}
	
	/* public Position getRowCornerNeighbourPos(Corner c) {
		return new Position (getCorner(c).getRow(), getCorner(c).getColumn()+c.getNextColOffset());
	}
	
	public Position getColCornerNeighbourPos(Corner c) {
		return new Position (getCorner(c).getRow()+c.getNextRowOffset(), getCorner(c).getColumn());
	}
	
	public Position getNeighbourCornerPos(Corner c) {
		return new Position(getCorner(c).getRow() + c.getNextRowOffset(), getCorner(c).getColumn() + c.getNextColOffset());
	} */
	
	private Position getTopLeft(Tile t, Position pos, Corner c) {
		switch (c) {
		case TOP_RIGHT:
			return new Position(pos.getRow(), pos.getColumn() - t.getCols() + 1);
		case BOTTOM_LEFT: 
			return new Position(pos.getRow() - t.getRows() + 1, pos.getColumn());
		case BOTTOM_RIGHT: 
			return new Position(pos.getRow() - t.getRows() + 1, pos.getColumn() - t.getCols() + 1);
		default:
			return pos;

		}
	}

	@Override
	public String toString() {
		return "SurfaceEntry [tile=" + tile + ", topLeft=" + topLeft + ", topRight=" + topRight + ", bottomLeft=" + bottomLeft
				+ ", bottomRight=" + bottomRight + "]";
	}
	
	

}
