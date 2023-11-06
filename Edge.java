
public class Edge {
	private Vertex start;
	private Vertex end;
	private char label;
	
	public Edge(Vertex startV, Vertex endV, char label) {
		this.start = startV;
		this.end = endV;
		this.label = label;
	}
	
	public Vertex getStart() {
		return this.start;
	}
	
	public Vertex getEnd() {
		return this.end;
	}
	
	public char getLabel() {
		return this.label;
	}
	
}
