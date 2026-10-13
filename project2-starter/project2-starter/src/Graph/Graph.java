/**
 * @file Graph.java
 *
 * makes and traverses a chess board assuming that it is being traversed as a knight
 *
 * @author Conor Williams
 * @date October 12, 2026
 */

package Graph;

import java.io.FileWriter;
import java.io.IOException;

public class Graph {
	private GraphNode[] Nodes;
	private int[][] AdjMatrix;
	private boolean[] visited;
	private int n;
	private int startX, startY;

	public Graph(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Board size must be positive");
		}
		this.Nodes = new GraphNode[n * n];
		this.AdjMatrix = new int[n * n][n * n];
		this.visited = new boolean[n * n];
		this.n = n;
		// You are allowed to add more code here if necessary.
	}

	public void BuildingGraphDFS(int start_x, int start_y) {		
		if (start_x < 0 || start_x >= n || start_y < 0 || start_y >= n) {
	        throw new IllegalArgumentException("Starting square is outside the board");
	    }

		startX = start_x; // stores the starting x for future reference
		startY = start_y; // stores the y for future reference
		
		visited = new boolean[n*n]; //makes a empty array to track what has been visited
		AdjMatrix = new int[n*n][n*n]; // makes the adjacency matrix to store possible edges
		Nodes = new GraphNode[n * n]; // makes an array to store the GrapHNode objects

		for (int row = 0; row < n; row++) { // iterates though the number of rows of the board
		    for (int col = 0; col < n; col++) { // iterates through the number of column's of the board
		        int index = row * n + col; // finds the node index at this point
		        Nodes[index] = new GraphNode(row, col); // makes and assigns the GraphNode for this tile
		    }
		}
		
		DFS(start_x, start_y); // the recursive call to build the graph nodes and edges
	}

	private void DFS(int x, int y) {
		int rx[] = {1, 1, 2, 2,-1, -1, -2, -2}; // uses this to go through possible moves instead of a if else nightmare
		int ry[] = {2, -2, 1, -1, 2, -2, 1, -1}; // uses this to go through possible moves instead of a if else nightmare
		
		int currentIndex = y * n + x; // gets the current index
		
		if (visited[currentIndex] == true) { // checks if we have visited a tile to avoid loops
			return;
		}
		
		visited[currentIndex] = true; // marks the current tile as visited
		
		for(int i=0; i<8; i++) { // iterates through the possible moves
			int newX = x + rx[i]; // the new x value we will use for traversal
			int newY = y + ry[i]; // the new y value we will use for traversal
			
			if (newX >= 0 && newX < n && newY >= 0 && newY < n) { // if the move is possible it start exploring that branch
				int nextIndex = newY * n + newX; // finds the next index
				
				AdjMatrix[currentIndex][nextIndex] = 1; // adds the edges to the adj matrix
				AdjMatrix[nextIndex][currentIndex] = 1; // adds the edges to the adj matrix
				
				if (!visited[nextIndex]) { // if we haven't visited it and its possible start the recursion to continue exploring
					DFS(newX,newY);
				}
			}
		}
	}
	
	public boolean KMoves(int end_x, int end_y, int k) {
		// TODO: Part 2. Return true if the destination is reachable within k moves.
		throw new UnsupportedOperationException("Implement Part 2");
	}

	
	/**
	 * finds a knight's tour of the chess board that visits every tile once before
	 * returning to the start
	 * 
	 * @return an array of GraphNode objects that represents the tour that the knight
	 * makes, or it returns null if the tour doesn't exist
	 */
	public GraphNode[] KnightsTourClosedPath() {
		int path[] = new int[n*n+1]; // stores the indices of the tiles in the tour
		this.visited = new boolean[n*n]; // resets the visited value of the tour to false
		
		int start = startY * n + startX; // converts starting cords to a index
		
		visited[start] = true; // sets the starting tile to visited
		path[0] = start; // puts the starting value at the start
		
		if (FindTour(start, start, path, 1)) { // searches for a tour from the new starting point
			GraphNode[] tour = new GraphNode[n * n + 1]; // creates an array to store the nodes of the tour

	        for (int i = 0; i < tour.length; i++) { // iterates through every position
	            tour[i] = Nodes[path[i]]; // converts indices into GraphNode references
	        }

	        return tour; // returns the complete tour
	    }
		
		return null; // returns null since no tour was found
	}
	
	/**
	 * searches for a tour(traveling though every spot once) using recursion
	 * 
	 * @param current the index of the current tile
	 * @param start the starting tile of the tour
	 * @param path the array that stores the tiles of the tour
	 * @param pathLength the number of tiles currently traversed
	 * @return true if a tour is found, false otherwise
	 */
	private boolean FindTour(int current, int start, int[] path, int pathLength) {
		
		if (pathLength == n*n) { // checks if every tile has been visited
			if (AdjMatrix[current][start] == 1) { // checks connections
				path[pathLength] = start; // adds the starting tile
				return true; // a tour was found
			} else {
				return false; // no tour was found here
			}
		}
		
		for (int i = 0; i < Nodes.length; i++) { // iterates through every node
			
			if (AdjMatrix[current][i] == 1 && !visited[i]) { // checks if the node is legal and has not been visited
				int next = i; // stores the index of the next tile

				visited[next] = true; // marks the next tile as visited
				path[pathLength] = next; // adds the tile to the path
					
				if (FindTour(next, start, path, pathLength + 1)) { // makes a recursive call to find the rest of the tour
					return true; // a complete tour was found
				}
				visited[next] = false; // the path failed so this backtracks to check other paths
			}
		}
		return false; // no possible tour exists
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
