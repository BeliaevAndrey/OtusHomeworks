package graph;


public class Edge implements Comparable<Edge>{

    private final int begin;

    private final int end;

    private final int weight;

    public Edge(int begin, int end) {
        this.begin = begin;
        this.end = end;
        this.weight = 1;
    }

    public Edge(int begin, int end, int weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%d - %d: %d", begin + 1, end + 1, weight);
    }

    // region getters

    public int getBegin() { return begin; }

    public int getEnd()  { return end; }

    public int getWeight() { return weight; }


    @Override
    public int compareTo(Edge o) {
        return weight - o.getWeight();
    }

    // endregion

}
