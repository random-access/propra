package ess.data;

public class SurfaceEntry {

	private Tile tile;
	private Position pos;

	public SurfaceEntry(Tile tile, Position pos) {
		this.tile = tile;
		this.pos = pos;
	}

	public SurfaceEntry(Tile tile, Position p, Corner corner) {
		this.tile = tile;
		this.pos = getTopLeft(tile, p, corner);
	}
	
	public Position getPosition() {
		return getCorner(Corner.TOP_LEFT);
	}

	public Tile getTile() {
		return tile;
	}

	public Position getCorner(Corner c) {
		switch (c) {
		case TOP_LEFT: 
			return pos;
		case TOP_RIGHT:
			return new Position(pos.getRow(), pos.getColumn() + tile.getCols() - 1);
		case BOTTOM_LEFT: 
			return new Position(pos.getRow() + tile.getRows() - 1, pos.getColumn());
		case BOTTOM_RIGHT: 
			return new Position(pos.getRow() + tile.getRows() - 1, pos.getColumn() + tile.getCols() - 1);
		default:
			throw new IllegalArgumentException("Not a valid value for corner.");

		}
	}
	
	private Position getTopLeft(Tile t, Position pos, Corner c) {
		switch (c) {
		case TOP_LEFT:
			return pos;
		case TOP_RIGHT:
			return new Position(pos.getRow(), pos.getColumn() - t.getCols() + 1);
		case BOTTOM_LEFT: 
			return new Position(pos.getRow() - t.getRows() + 1, pos.getColumn());
		case BOTTOM_RIGHT: 
			return new Position(pos.getRow() - t.getRows() + 1, pos.getColumn() - t.getCols() + 1);
		default:
			throw new IllegalArgumentException("Not a valid value for corner.");

		}
	}

	@Override
	public String toString() {
		return "SurfaceEntry [tile=" + tile + ", topLeft=" + pos + "]";
	}
}
