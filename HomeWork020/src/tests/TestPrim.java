package tests;

import graph.Edge;
import graph.Graph;
import spanningTree.Prim;
import structures.CustomLinkedList;
import util.GraphUtl;

public class TestPrim {



    public static void main(String[] args) {

        int[][] adjacencyMatrix = {
                { 0, 2, 2, 0, 1, 0, 3 },
                { 2, 0, 3, 3, 0, 0, 0 },
                { 2, 3, 0, 0, 0, 0, 0 },
                { 0, 3, 0, 0, 2, 0, 0 },
                { 1, 0, 0, 2, 0, 4, 0 },
                { 0, 0, 0, 0, 4, 0, 2 },
                { 3, 0, 0, 0, 0, 2, 0 }
        };

        GraphUtl utl = new GraphUtl();


        Graph graph = new Graph(adjacencyMatrix, adjacencyMatrix.length);

        utl.printMatrix(adjacencyMatrix);
        System.out.println();
        Prim prim = new Prim(graph);

        prim.spanningTree(0);

        System.out.println("Prim algorithm.");
        System.out.println("Spanning tree: ");
        CustomLinkedList<Edge> edges = prim.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i));
        }
        System.out.printf("Spanning tree weight: %d\n", prim.getMinWeight());
    }

}
