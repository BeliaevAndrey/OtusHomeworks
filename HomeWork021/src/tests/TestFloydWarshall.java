package tests;

import graph.Graph;
import paths.FloydWarshall;
import util.GraphUtl;

public class TestFloydWarshall {


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


        testFloydWarshall(aMatrix);
        System.out.println("=".repeat(80));
        System.out.println("=".repeat(80));
        testFloydWarshall(aMatrix2);

    }

    static void testFloydWarshall(int[][] aMatrix) {
        Graph graph = new Graph(aMatrix, aMatrix.length);
        FloydWarshall fw = new FloydWarshall(graph);


        GraphUtl utl = new GraphUtl();
        utl.printMatrix(aMatrix);


        int[][] ways = fw.findPaths();

        System.out.println("Floyd-Warshall algorithm:");
        System.out.print("    | ");
        for (int i = 0; i < fw.getSize(); i++) {
            System.out.printf("%4d", i + 1);
        }

        System.out.println("\n----|--" + "-".repeat(graph.getSize() * 4));

        for (int i = 0; i < fw.getSize(); i++) {
            System.out.printf("%3d | ", i + 1);
            for (int j = 0; j < fw.getSize(); j++) {
                System.out.printf("%4d", ways[i][j]);
            }
            System.out.println();
        }
    }


}
