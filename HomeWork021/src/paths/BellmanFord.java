package paths;

import graph.Edge;
import graph.Graph;
import structures.CustomLinkedList;

public class BellmanFord {


    // region

    private final int[][] matrix;
    private final int size;
    private final int totalWeight;
    private final CustomLinkedList<Edge> edgesList;

    // endregion


    public BellmanFord(Graph graph) {
        matrix = graph.getMatrixAdj();
        size = graph.getSize();
        edgesList = new CustomLinkedList<>();
        totalWeight = graph.findTotalWeight();
    }

    public int[] findPaths(int vertex) {
        int[] ways = new int[size];
        for (int i = 0; i < size; i++) {
            if (i == vertex)
                ways[i] = 0;
            else if (matrix[vertex][i] == 0)
                ways[i] = totalWeight;
            else
                ways[i] = matrix[vertex][i];
        }

        for (int k = 0; k < size - 1; k++) {
            for (int i = 0; i < size; i++) {
                if (i == vertex) continue;
                for (int j = 0; j < size; j++) {
                    if (i == j) continue;
                    if (j == vertex) continue;
                    if (matrix[i][j] == 0) continue;
                    if ((ways[i] + matrix[i][j]) < ways[j]) ways[j] = ways[i] + matrix[i][j];
                }
            }
        }
        return ways;
    }


    // ========================================================================
    // === Extra ==============================================================

    void listEdges() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                if (matrix[i][j] == 0) continue;
                edgesList.add(new Edge(i, j, matrix[i][j]));
            }
        }
    }

    void printEL() {
        for (int i = 0; i < edgesList.size(); i++) {
            System.out.println(edgesList.get(i));
        }
    }


    public int getSize() {return size;}
}
