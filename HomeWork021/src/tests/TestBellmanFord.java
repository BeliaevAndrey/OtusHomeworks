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

        Graph graph = new Graph(aMatrix, aMatrix.length);
        BellmanFord bf = new BellmanFord(graph);


        System.out.println("Bellman-Ford algorithm.");
        System.out.println("Minimal paths:");
        System.out.print("\nStart\t| Vertices\nvertex\t| sequence\n");
        System.out.println("-".repeat(40));
        for (int vertex = 0; vertex < bf.getSize(); vertex++) {
            int[] ways = bf.findPaths(vertex);
            System.out.printf("%4d \t|", vertex + 1);
            for (int i = 0; i < bf.getSize(); i++)
                System.out.printf("%3d", ways[i]);
            System.out.println();
        }

    }


}
