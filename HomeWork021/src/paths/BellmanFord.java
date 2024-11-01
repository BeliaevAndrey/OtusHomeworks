package paths;

import graph.Edge;
import graph.Graph;
import structures.CustomLinkedList;

public class BellmanFord {


    // region

    private final int[][] matrix;
    private final int size;
    private final int totalWeight;

    // endregion


    public BellmanFord(Graph graph) {
        matrix = graph.getMatrixAdj();
        size = graph.getSize();
        totalWeight = graph.findTotalWeight();
    }

    public int[] findPaths(int vertex) {
        int[] paths = new int[size];        // path costs array
        for (int i = 0; i < size; i++) {
            if (i == vertex) paths[i] = 0;
            else if (matrix[vertex][i] == 0) paths[i] = totalWeight;    // instead infinity
            else paths[i] = matrix[vertex][i];
        }

        for (int k = 0; k < size - 1; k++) {
            for (int i = 0; i < size; i++) {
                if (i == vertex) continue;
                for (int j = 0; j < size; j++) {
                    if (i == j) continue;               // skip loops (main diagonal)
                    if (j == vertex) continue;          // skip self
                    if (matrix[i][j] == 0) continue;    // skip empty

                    if ((paths[i] + matrix[i][j]) < paths[j]) paths[j] = paths[i] + matrix[i][j];
                }
            }
        }
        return paths;
    }

    public int getSize() {return size;}
}
