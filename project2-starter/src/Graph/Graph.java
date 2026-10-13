package Graph;

import java.io.FileWriter;
import java.io.IOException;

public class Graph {
	private GraphNode[] Nodes;
	private int[][] AdjMatrix;
	private boolean[] visited;

	public Graph(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Board size must be positive");
		}
		this.Nodes = new GraphNode[n * n];
		this.AdjMatrix = new int[n * n][n * n];
		this.visited = new boolean[n * n];
		// You are allowed to add more code here if necessary.
	}

	public void BuildingGraphDFS(int start_x, int start_y) {
		// TODO: Part 1. Build the knight-move graph using recursive DFS.
		// Remember the starting square for Parts 2 through 4.
		throw new UnsupportedOperationException("Implement Part 1");
	}

	public boolean KMoves(int end_x, int end_y, int k) {
		// TODO: Part 2. Return true if the destination is reachable within k moves.
		throw new UnsupportedOperationException("Implement Part 2");
	}

	public GraphNode[] KnightsTourClosedPath() {
		// TODO: Part 3. Return a closed tour visiting each square exactly once.
		// Repeat the starting node at the end. Return null if no tour exists.
		throw new UnsupportedOperationException("Implement Part 3");
	}

	public int MinimumCosts(int end_x, int end_y) {
		// TODO: Part 4. Return the minimum total spending from start to end.
		// Arriving at white costs $2, black costs $3, and red costs $4.
		// The starting square is not charged initially.
		// If the destination cannot be reached, raise an error "Not reachable".
		throw new UnsupportedOperationException("Implement Part 4");
	}

	public void saveGraph() {
		// Provided testing utility. Run from the project folder.
		try {
			FileWriter writer = new FileWriter("tests/graph-color.txt");
			try {
				writer.write("row column color\n");
				for (int i = 0; i < Nodes.length; i++) {
					writer.write(Nodes[i].getRow() + " " + Nodes[i].getCol()
						+ " " + Nodes[i].getColor() + "\n");
				}
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unable to save graph colors", e);
		}
	}
}
