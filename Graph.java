import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices;

	public Graph() {
		this.vertices = new ArrayList<Vertex>();
	}

	public Vertex addVertex(int x, int y, int z) {
	    Vertex newVertex = new Vertex(x, y, z);
	    this.vertices.add(newVertex);
	    return newVertex;
	}

	public void addEdge(Vertex vertex1, Vertex vertex2, char label) {
	    vertex1.addEdge(vertex2, label);
	    vertex2.addEdge(vertex1, label);
	}

	public void removeEdge(Vertex vertex1, Vertex vertex2) {
	    vertex1.removeEdge(vertex2);
	    vertex2.removeEdge(vertex1);
	}

	public void removeVertex(Vertex vertex) {
	    this.vertices.remove(vertex);
	}

	public ArrayList<Vertex> getVertices() {
	    return this.vertices;
	}
	
	public Vertex getVertexById(int i) {
	    if(i < this.vertices.size()) { 
	    	return this.vertices.get(i);
	    }

	    return null;
	}

	public Vertex getVertexByCoords(int x, int y, int z) {
	    for(Vertex v: this.vertices) { 
	    	if (v.findByCoords(x, y, z)) {
	    		return v;
	    	}
	    }

	    return null;
	}
	
	public void print(int c, int r, int l) {
		for(int i = 0; i < this.vertices.size(); i++) {
			System.out.print(this.vertices.get(i).x + ", " + this.vertices.get(i).y + ", " + this.vertices.get(i).z + " | ");
			if(i / c != (i + 1) / c)
				System.out.print(System.lineSeparator());
			if(i / (c * r) != (i + 1) / (c * r))
				System.out.print("================================\n");
		}
	}
	
	public void printEdges() {
		for(int i = 0; i < this.vertices.size(); i++) {
			System.out.print(this.vertices.get(i).x + ", " + this.vertices.get(i).y + ", " + this.vertices.get(i).z + " |-> ");
			ArrayList<Edge> edges = this.vertices.get(i).getEdges();
			edges.forEach((e) -> {
				System.out.print(e.getEnd().x + ", " + e.getEnd().y + ", " + e.getEnd().z + "; ");
			});
			System.out.print(System.lineSeparator());
		}
	}
}
