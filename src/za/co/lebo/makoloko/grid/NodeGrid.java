package za.co.lebo.makoloko.grid;

public class NodeGrid {
	private int xCoordinate;
	
	public int getxCoordinate() {
		return xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}
	private int yCoordinate;
	public NodeGrid(int xCoordinate, int yCoordinate) {
		super();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	public static NodeGrid getInstance(int x, int y) {
		return new NodeGrid(x, y);
	}
	@Override
	public String toString() {
		return String.format("(%d,%d)", xCoordinate, yCoordinate);
	}
}
