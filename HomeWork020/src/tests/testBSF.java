package tests;

import graph.Edge;
import graph.Graph;
import spanningTree.SpanningTree;
import structures.CustomLinkedList;
import util.GraphUtl;

public class testBSF {

    /*
    1 | 2 3 5 7
    2 | 1 3 5 0
    3 | 1 2 0 0
    4 | 2 5 0 0
    5 | 1 4 6 0
    6 | 5 7 0 0
    7 | 1 6 0 0
    */


    public static void main(String[] args) {

        int[][] adjacencyVector = {
                {2, 3, 5, 7},
                {1, 3, 4, 0},
                {1, 2, 0, 0},
                {2, 5, 0, 0},
                {1, 4, 6, 0},
                {5, 7, 0, 0},
                {1, 6, 0, 0}
        };

        GraphUtl utl = new GraphUtl();
        int[][] adjacencyMatrix = utl.vectorAjcToMatrixAjc(adjacencyVector);

        Graph graph = new Graph(adjacencyMatrix, adjacencyMatrix.length);

        utl.printMatrix(adjacencyMatrix);
        System.out.println();
        SpanningTree st = new SpanningTree(graph);

        st.bfs(0);

        System.out.println("Spanning tree: ");
        CustomLinkedList<Edge> edges = st.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            System.out.printf("%d - %d\n", e.begin + 1, e.end + 1);
        }
        System.out.println();
    }

}
