package tests;

import graph.Edge;
import graph.Graph;
import spanningTree.KruskalSkiena;
import util.GraphUtl;

public class TestKruskalSkiena {

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                {0, 2, 2, 0, 1, 0, 3},
                {2, 0, 3, 3, 0, 0, 0},
                {2, 3, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 2, 0, 0},
                {1, 0, 0, 2, 0, 4, 0},
                {0, 0, 0, 0, 4, 0, 2},
                {3, 0, 0, 0, 0, 2, 0}
        };

        GraphUtl utl = new GraphUtl();
        utl.printMatrix(adjacencyMatrix);

        Graph g = new Graph(adjacencyMatrix, adjacencyMatrix.length);

        KruskalSkiena kruskal = new KruskalSkiena(g);

        kruskal.spanningTree();

        System.out.println("Kruskal algorithm.");
        System.out.println("Spanning tree: ");
        Edge[] minSTree = kruskal.getMinWeightTree();
        for (Edge edge : minSTree) {
            System.out.println(edge);
        }
        System.out.printf("Spanning tree weight: %d\n", kruskal.getMinWeight());

    }
}
