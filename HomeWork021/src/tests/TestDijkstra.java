package tests;

import graph.Edge;
import graph.Graph;
import paths.Dijkstra;
import util.GraphUtl;

public class TestDijkstra {

    public static void main(String[] args) {


        int[][] aMatrix = {
                {0, 7, 9, 0, 0, 14},
                {7, 0, 10, 15, 0, 0},
                {9, 10, 0, 11, 0, 2},
                {0, 15, 11, 0, 6, 0},
                {0, 0, 0, 6, 0, 9},
                {14, 0, 2, 0, 9, 0}
        };

        int[][] aMatrix2 = {
                {0, 7, 7, 0, 0, 0, 0, 0},
                {7, 0, 1, 4, 0, 0, 0, 0},
                {7, 1, 0, 0, 5, 0, 9, 0},
                {0, 4, 0, 0, 0, 7, 0, 5},
                {0, 0, 5, 0, 0, 0, 1, 0},
                {0, 0, 0, 7, 0, 0, 2, 0},
                {0, 0, 9, 0, 1, 2, 0, 3},
                {0, 0, 0, 5, 0, 0, 3, 0},
        };

        testDijkstra(aMatrix, 1, 4);
        System.out.println("=".repeat(80));
        System.out.println("=".repeat(80));
        testDijkstra(aMatrix2, 0, 7);

    }


    static void testDijkstra(int[][] aMatrix, int start, int end) {
        Graph graph = new Graph(aMatrix, aMatrix.length);
        Dijkstra dijkstra = new Dijkstra(graph);

        GraphUtl utl = new GraphUtl();
        utl.printMatrix(aMatrix);

        int[][] costs = dijkstra.findAllPathCosts();


        System.out.println("Costs for all vertices:\nStart  | Paths\nvertex | costs");
        System.out.println("-".repeat(5 + dijkstra.getSize() * 5));
        System.out.print(" ".repeat(7));
        for (int i = 0; i < dijkstra.getSize(); i++) {
            System.out.printf("%4d", i);
        }
        System.out.println("\n" + "-".repeat(5 + dijkstra.getSize() * 5));

        for (int i = 0; i < costs.length; i++) {
            System.out.printf(" %3d   |", i);
            for (int j = 0; j < costs[i].length; j++) {
                System.out.printf("%3d ", costs[i][j]);
            }
            System.out.println();
        }


//        int start = 1, end = 4;
        Edge[] path = dijkstra.findPath(start, end);

        System.out.printf("\nPath form %d to %d:\n", start, end);
        for (Edge e : path) {
            System.out.println(e);
        }




    }
}
