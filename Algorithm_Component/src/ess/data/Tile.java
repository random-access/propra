package ess.data;

public class Tile {
	
	private String ident;
	private int width, height;

	public Tile(String ident, int width, int height) {
		this.ident = ident;
		this.width = width;
		this.height = height;
	}
	
	public String getIdent() {
		return ident;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getArea() {
		return width * height;
	}

	@Override
	public String toString() {
		return "Tile [ident=" + ident + ", width=" + width + ", height=" + height
				+ "]";
	}

}
