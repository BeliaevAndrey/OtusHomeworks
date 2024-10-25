package graph;

public class Edge {

    public int begin;
    public int end;

    public Edge(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("%d - %d", begin, end);
    }
}
