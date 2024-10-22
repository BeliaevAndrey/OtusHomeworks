package tests;

import graphs.Graph;
import util.GraphUtl;

public class GraphTest {

    /*


     (6)----------------(5)           1 | 2 3 7
      |                  |            2 | 1 3 4
      |   ,-----(1)--.   |            3 | 1 2 0
      |   |      |   |  (4)           4 | 2 5 0
      |   |      |   |   |            5 | 4 6 0
     (7)--'      |   |   |            6 | 5 7 0
                (3)  '--(2)           7 | 1 6 0
                  '-----'
 */
    public static void main(String[] args) {

        GraphTest graphTest = new GraphTest();
        GraphUtl utl = new GraphUtl();

        int[][] vecAdj = new int[][]{
                {2, 3, 7},
                {1, 3, 4},
                {1, 2, 0},
                {2, 5, 0},
                {4, 6, 0},
                {5, 7, 0},
                {1, 6, 0}};

        int[][] matrixAdj = utl.vectorAjcToMatrixAjc(vecAdj);

        Graph graph = new Graph(matrixAdj, matrixAdj.length);

        if (graph.dfs(1 - 1, 6 - 1, true))
        {
            System.out.println("Path found: ");
            int pathLen = graph.getPath().size();
            for (int i = 0; i < pathLen; i++) {
                System.out.printf("%d ", graph.getPath().get(i));
            }
            System.out.println();
        }
        else
            System.out.println("Path not found!");

        if (graph.bfs(1 - 1, 6 - 1))
        {
            System.out.println("Path found: ");
            int pathLen = graph.getPath().size();
            for (int i = 0; i < pathLen; i++) {
                System.out.printf("%d ", graph.getPath().get(i));
            }
            System.out.println();
        }
        else
            System.out.println("Path not found!");


    }

}

