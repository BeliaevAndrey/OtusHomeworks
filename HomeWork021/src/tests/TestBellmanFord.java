package tests;

import graph.Graph;
import paths.BellmanFord;

public class TestBellmanFord {


    /**
     * Simple test
     */
    public static void main(String[] args) {

        int[][] aMatrix = {
                {0, -2, 5, 7},
                {0, 0, 6, 8},
                {-1, 0, 0, 0},
                {0, 3, -4, 0}};

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

        testBellmanFord(aMatrix);
        System.out.println("=".repeat(80));
        System.out.println("=".repeat(80));
        testBellmanFord(aMatrix2);

    }

    static void testBellmanFord(int[][] aMatrix) {

        Graph graph = new Graph(aMatrix, aMatrix.length);
        BellmanFord bf = new BellmanFord(graph);


        System.out.println("Bellman-Ford algorithm.");
        System.out.println("Minimal paths:");
        System.out.print("\nStart\t| Path\nvertex\t| costs\n");
        System.out.println("-".repeat(30));
        for (int vertex = 0; vertex < bf.getSize(); vertex++) {
            int[] ways = bf.findPaths(vertex);
            System.out.printf("%4d \t|", vertex + 1);
            for (int i = 0; i < bf.getSize(); i++)
                System.out.printf("%3d", ways[i]);
            System.out.println();
        }

    }


}
