package presents.VerticesEdgesLists;

// ====================================================
public class Edge {

    private final Vertex begin;
    private final Vertex end;
    private final boolean directed;


    public Edge(Vertex begin, Vertex end, boolean isDirected) {
        this.begin = begin;
        this.end = end;
        this.directed = isDirected;
    }

    public Edge(Vertex begin, Vertex end) {
        this.begin = begin;
        this.end = end;
        this.directed = false;
    }

    public Vertex getBegin() {return begin;}

    public Vertex getEnd() {return end;}

    public boolean isDirected() {return directed;}

    @Override
    public String toString() {
        return String.format("{%s, %s}", begin, end);
    }
}
