package presents.Lists;

public class Vertex {

    private final int id;

    private Edge[] edges;

    private boolean visited = false;
    public Vertex(int id) { this.id = id; }

    public void addEdge(Edge edge) {

        if (edges == null) {
            edges = new Edge[]{edge};
            return;
        }

        Edge[] edgesTmp = new Edge[edges.length + 1];
        for (int i = 0; i < edges.length; i++) { edgesTmp[i] = edges[i]; }

        edgesTmp[edges.length] = edge;
        edges = edgesTmp;
    }

    public boolean isVisited() { return visited; }

    public void setVisited(boolean visited) { this.visited = visited; }

    public void reset() { visited = false; }    // just in case

    public Edge[] getEdges() {return edges;}

    public int getId() {return id;}

    @Override
    public String toString() {return String.format("%d", id);}
}
