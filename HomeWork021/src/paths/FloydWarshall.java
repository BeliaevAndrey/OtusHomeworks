package paths;

import graph.Graph;


public class FloydWarshall {


    // region privates

    private final int size;
    private final int[][] matrix;

    private final int totalWeight;

    // endregion


    public FloydWarshall(Graph graph) {
        this.matrix = graph.getMatrixAdj();
        this.size = graph.getSize();
        totalWeight = graph.findTotalWeight();
    }

    public int[][] findPaths() {

        int[][] ways;
        ways = new int[size][size];                 // matrix of path costs

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (i == j) ways[i][j] = 0;         // loop (main diagonal)

                else if (matrix[i][j] == 0)
                    ways[i][j] = totalWeight;       // infinity substitution
                else
                    ways[i][j] = matrix[i][j];      // weight of edge
            }

        // Complexity: O(V^3)
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                if (k == i) continue;               // skip loop
                for (int j = 0; j < size; j++) {
                    if (k == j) continue;           // skip loop
                    if (i == j) continue;           // skip loop

                    if ((ways[i][k] + ways[k][j]) < ways[i][j])  {  // if way through vertex 'k' cheaper
                        ways[i][j] = ways[i][k] + ways[k][j];       // change way from straight
                                                                    // ('infinity' is always off)
                    }
                }
            }
        }

        return ways;
    }

    public int getSize() { return size; }
}
