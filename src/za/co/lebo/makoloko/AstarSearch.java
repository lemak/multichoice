package za.co.lebo.makoloko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import za.co.lebo.makoloko.grid.NodeGrid;
import za.co.lebo.makoloko.grid.TileGrid;

/**
 * @author Lebo Makoloko
 * 
 * This is an implementation of an algorithm at http://en.wikipedia.org/wiki/A*_search_algorithm
 *
 */

public abstract class AstarSearch {
	private TileGrid tileGrid;
	private NodeGrid startNode;
	private NodeGrid goalNode;
	public TileGrid getTileGrid() {
		return tileGrid;
	}
	public void setTileGrid(TileGrid tileGrid) {
		this.tileGrid = tileGrid;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AstarSearch astarSearch = DistanceFormulaFactory.getDistanceFormula(DistanceFormula.Manhattan);
		astarSearch.setUpTileGrid(7, 7);
		
		//NodeGrid startNode = new NodeGrid(2, 1);
		//NodeGrid endNode = new NodeGrid(5, 5);
		
		//List<NodeGrid> nodeGridList = astarSearch.searchForPath(startNode, goalNode);
		List<NodeGrid> nodeGridList = astarSearch.searchForPath(astarSearch.getStartNode(), astarSearch.getGoalNode());
		int count = 0;
		for (NodeGrid nodeGrid : nodeGridList) {
			System.out.println("Step " + count++);
			System.out.printf("(%d, %d)\n", nodeGrid.getxCoordinate(), nodeGrid.getyCoordinate());
		}
	}
	private NodeGrid getStartNode() {
			startNode = tileGrid.getNodeFromRandom();
		return startNode;
	}
	private NodeGrid getGoalNode() {
			goalNode = tileGrid.getNodeFromRandom();
		return goalNode;
	}
	
	private void setUpTileGrid(int xCoodinate, int yCoodinate) {
		TileGrid tileGrid = new TileGrid();
		setTileGrid(tileGrid.getInstance(xCoodinate, yCoodinate));
		System.out.println("Tile Grid:");
		System.out.println(this.tileGrid);
	}
	/**
	 * This method initializes displays the source and the target nodes. It also initializes the variables to be used by the 
	 * algorithm. The remaining code is the rest of the implementation of the A* algorithm.
	 *
	 */
	private List<NodeGrid> searchForPath(NodeGrid start, NodeGrid goal) {
		System.out.println("Start node coordinates : ");
		System.out.printf("(%d, %d)\n", start.getxCoordinate(), start.getyCoordinate());
		System.out.println("Goal node coordinates : ");
		System.out.printf("(%d, %d)\n", goal.getxCoordinate(), goal.getyCoordinate());
		
		Map<NodeGrid, Double> movementCostScore = new HashMap<NodeGrid, Double>();
		Set<NodeGrid> openTiles = new HashSet<NodeGrid>();
		Map<NodeGrid, Double> lowestCostScore = new HashMap<NodeGrid, Double>();
		Map<NodeGrid, NodeGrid> fromNode = new HashMap<NodeGrid, NodeGrid>();
		Set<NodeGrid> closedTiles = new HashSet<NodeGrid>();

		openTiles.add(start);
		movementCostScore.put(start, 0D);
		lowestCostScore.put(start, getHeuristicCostEstimate(start, goal));

		while (!openTiles.isEmpty()) {
			NodeGrid current = getLowestCostScore(openTiles, lowestCostScore);
			if (current.equals(goal)) {
				return reconstructPath(fromNode, goal);
			}
			openTiles.remove(current);
			closedTiles.add(current);
			for (NodeGrid neighbouringNode : tileGrid.getNeighbours(current)) {
				Double gScore = movementCostScore.get(current) + getHeuristicCostEstimate(current, neighbouringNode);
				// Check if it's a walkable terrain
				if (closedTiles.contains(neighbouringNode)) {
					if (gScore >= movementCostScore.get(neighbouringNode)) {
						continue;
					}
				}
				fromNode.put(neighbouringNode, current);
				movementCostScore.put(neighbouringNode, gScore);
				lowestCostScore.put(neighbouringNode, gScore + getHeuristicCostEstimate(neighbouringNode, goal));
				if (!openTiles.contains(neighbouringNode)) {
					openTiles.add(neighbouringNode);
				}
			}
		}
		return null;
	}
	protected abstract Double getHeuristicCostEstimate(NodeGrid start, NodeGrid end);

	/**
	 * This method returns the path as calculated by the algorithm
	 *
	 */
	private List<NodeGrid> reconstructPath(Map<NodeGrid, NodeGrid> fromNode, NodeGrid currentNode) {
		if (fromNode.keySet().contains(fromNode.get(currentNode))) {
			ArrayList<NodeGrid> foundPath = (ArrayList<NodeGrid>) reconstructPath(fromNode, fromNode.get(currentNode));
			foundPath.add(currentNode);

			return foundPath;
		} else {
			ArrayList<NodeGrid> noPath = new ArrayList<NodeGrid>();
			noPath.add(currentNode);
			return noPath;
		}
	}
	/**
	 * This method calculates the lowest cost movement from current tile to the goal tile
	 *
	 */
	private NodeGrid getLowestCostScore(Set<NodeGrid> openSet, Map<NodeGrid, Double> score) {
		NodeGrid nodeWithLowestScore = null;

		for (NodeGrid nodeGrid : openSet) {
			if (nodeWithLowestScore == null || score.get(nodeWithLowestScore) > score.get(nodeGrid)) {
				nodeWithLowestScore = nodeGrid;
			}
		}
		return nodeWithLowestScore;
	}
}
