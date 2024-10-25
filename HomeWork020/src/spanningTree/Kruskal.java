package spanningTree;

import graph.Edge;
import graph.Graph;
import structures.CustomLinkedList;

public class Kruskal {

    // region privates (basics)

    private final int[][] matrix;

    private final int size;
    
    private final CustomLinkedList<Edge> edges;

    private int minWeight = 0;

    // endregion

    private int[] parents; // main vertices

    public Kruskal(Graph graph) {
        this.matrix = graph.getMatrixAdj();
        this.size = graph.getSize();
        this.edges = new CustomLinkedList<>();
    }


    public void spanningTree() {
        minWeight = 0;
        CustomLinkedList<Edge> list = new CustomLinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] == 0) continue;
                Edge edge = new Edge(i, j, matrix[i][j]);
                list.add(edge);
            }
        }
        
        list.sort();
        
        parents = new int[size];
        for (int i = 0; i < size; i++) parents[i] = i;

        for (int i = 0; i < size; i++) {
            Edge e = list.get(i);
            int begin = getParent(e.getBegin());
            int end = getParent(e.getEnd());
            if (begin == end) continue;
            edges.add(e);
            minWeight += e.getWeight();
            parents[end] = begin;
        }

    }

    /**
     * recursive choice
     */
    private int getParent(int v) {
        return parents[v] == v ? v : getParent(parents[v]);
    }
    
    
    // region getters

    public CustomLinkedList<Edge> getEdges() { return edges; }

    public int getMinWeight() { return minWeight; }

    // endregion

}
