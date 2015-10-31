package ess.data;

public class SurfaceEntry {
	
	private Tile tile;
	private Position topLeft;
	
	public SurfaceEntry(Tile tile, Position topLeft) {
		this.tile = tile;
		this.topLeft = topLeft;
	}
	
	public Tile getTile() {
		return tile;
	}

	public Position getTopLeft() {
		return topLeft;
	}

	public Position getTopRight() {
		return new Position(topLeft.getRow(), topLeft.getColumn() + tile.getCols()-1);
	}

	public Position getBottomLeft() {
		return new Position(topLeft.getRow() + tile.getRows()-1, topLeft.getColumn());
	}

	public Position getBottomRight() {
		return new Position(topLeft.getRow() + tile.getRows()-1, topLeft.getColumn() + tile.getCols()-1);
	}

}
