package za.co.lebo.makoloko.grid;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class TileGrid {
	private NodeGrid[][] nodeGrid;
	private final static Random randomNumber = new Random();
	public void setNodeGrid(NodeGrid[][] nodeGrid) {
		this.nodeGrid = nodeGrid;
	}
	private int yCoordinate;
	public int getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public int getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	private int xCoordinate;
	
	public TileGrid getInstance(int xCoordinate, int yCoordinate) {
		setxCoordinate(xCoordinate);
		setyCoordinate(yCoordinate);
		setNodeGrid(createNodeGrid(this.xCoordinate, this.yCoordinate));
		return this;
	}
	private NodeGrid[][] createNodeGrid(int x, int y) {
		NodeGrid[][] nodeGrid = new NodeGrid[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				nodeGrid[j][i] = NodeGrid.getInstance(i, j);
			}
		}
		return nodeGrid;
	}
	
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		for (int x = 0; x < yCoordinate; x++) {
			for (int y = 0; y < xCoordinate; y++) {
				if (nodeGrid[x][y] != null) {
					stringBuffer.append(nodeGrid[x][y]);
				}
				stringBuffer.append(" ");
			}
			stringBuffer.append("\n");
		}
		return stringBuffer.toString();
	}
	/**
	 * This method determines all the adjacent tiles to the current tile
	 *
	 */
	public Set<NodeGrid> getNeighbours(NodeGrid nodeGrid) {
		Set<NodeGrid> adjacentTiles = new HashSet<NodeGrid>();

		int leftTile = Math.max(0, nodeGrid.getxCoordinate() - 1);
		int rightTile = Math.min(this.xCoordinate - 1, nodeGrid.getxCoordinate() + 1);
		int bottomTile = Math.max(0, nodeGrid.getyCoordinate() - 1);
		int topTile = Math.min(this.yCoordinate - 1, nodeGrid.getyCoordinate() + 1);

		for (int i = leftTile; i <= rightTile; i++) {			
			for (int j = bottomTile; j <= topTile; j++) {
				NodeGrid adjacentNode = this.nodeGrid[i][j];
				if (adjacentNode != null && adjacentNode != nodeGrid) {
					adjacentTiles.add(adjacentNode);
				}
			}
		}

		return adjacentTiles;
	}
	public NodeGrid getNodeFromRandom() {
		return this.nodeGrid[getXCoordinateFromRandom()][getYCoordinateFromRandom()];
	}
	public int getXCoordinateFromRandom() {
		return randomNumber.nextInt(this.xCoordinate);
	}

	public int getYCoordinateFromRandom() {
		return randomNumber.nextInt(this.yCoordinate);
	}
}
