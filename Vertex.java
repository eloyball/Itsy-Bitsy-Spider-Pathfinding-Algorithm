import java.util.ArrayList;

public class Vertex {	
	public int x;
	public int y;
	public int z;
	
	private boolean visited;
	
	private ArrayList<Edge> edges;
	
	public Vertex(int x, int y, int z) {
		this.x = x;
	    this.y = y;
	    this.z = z;
	    this.visited = false;
	    this.edges = new ArrayList<Edge>();
	  }
	
	public void addEdge(Vertex endVertex, char label) {
	    this.edges.add(new Edge(this, endVertex, label));
	}

	public void removeEdge(Vertex endVertex) {
		this.edges.removeIf(edge -> edge.getEnd().equals(endVertex));
	}

	public ArrayList<Edge> getEdges(){
	    return this.edges;
	}
	
	public boolean findByCoords(int x, int y, int z) {
		return x == this.x && y == this.y && z == this.z;
	}
	
	public void setVisited(boolean bool) {
		this.visited = bool;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
}
