package za.co.lebo.makoloko;

import za.co.lebo.makoloko.grid.NodeGrid;

public class Manhattan extends AstarSearch {
	/**
	 * This method implements the Manhattan distance formula
	 *
	 */
	public Double getHeuristicCostEstimate(NodeGrid start, NodeGrid end) {
		return Double.valueOf(
				Math.abs(start.getxCoordinate() - end.getxCoordinate()) + Math.abs(start.getyCoordinate() - end.getyCoordinate()));
	}
}
