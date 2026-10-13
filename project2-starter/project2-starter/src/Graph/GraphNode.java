package Graph;
import java.util.Random;
public class GraphNode {
	private static final Random RANDOM = new Random();
	private int row;
	private int col;
	private String color;
	public GraphNode(int x, int y) {
		this.row=x;
		this.col=y;
		setColor();
	}
	private void setColor() {
		int choice = RANDOM.nextInt(3);
		if (choice == 0) { this.color = "white"; }
		else if (choice == 1) { this.color = "black"; }
		else { this.color = "red"; }
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public String getColor() {
		return color;
	}
}
