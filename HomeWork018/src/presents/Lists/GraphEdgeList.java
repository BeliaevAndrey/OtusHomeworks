package presents.Lists;


import presents.Implements.GraphPresent;

/**
 * Структура данных для представления графа.
 * Граф представлен в виде перечня рёбер.
 */

public class GraphEdgeList  implements GraphPresent {

    private Edge[] edges;
    private int edgeAmt;

    @Override
    public void buildFromAdjacencyMatrix(int[][] aMatrix) {
        for (int i = 0; i < aMatrix.length; i++)
            for (int j = i; j < aMatrix.length; j++)
                if (aMatrix[i][j] == 1) edgeAmt++;

        boolean[] visited = new boolean[aMatrix.length];
        edges = new Edge[edgeAmt];
        int edgeNum = 0;
        for (int i = 0; i < aMatrix.length; i++) {
            for (int j = 0; j < aMatrix.length; j++) {
                if (visited[j]) continue;
                if (aMatrix[j][i] == 1) {
                    edges[edgeNum++] = new Edge(new Vertex(i + 1), new Vertex(j + 1));
                    visited[i] = true;
                    visited[j] = true;
                }
            }
        }
    }

    @Override
    public void printSelf() {
        for (Edge e : edges) {
            System.out.println(e);
        }
    }
    
}
