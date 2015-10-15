package ess.data;

public class Tile {

	private int id, width, height;

	public Tile(int id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public int getId() {
		return id;
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
		return "Tile [id=" + id + ", width=" + width + ", height=" + height
				+ "]";
	}

}
