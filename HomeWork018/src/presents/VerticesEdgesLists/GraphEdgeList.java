package presents.VerticesEdgesLists;


/**
 * Структура данных для представления графа.
 * Граф представлен в виде перечня рёбер.
 */

public class GraphEdgeList {

    private Edge[] edges;
    private int edgeAmt;

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

    public void printEdgesList() {
        for (Edge e : edges) {
            System.out.println(e);
        }
    }
    
}
