package tests;

import graphs.Graph;
import sortings.Kahn;

public class KahnTest {
    public static void main(String[] args) {

        int[][] matrix = new int[][]
                {
                        {0, 1, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0},
                };
        int size = matrix.length;
        Graph graph = new Graph(matrix, size);

        Kahn kahn = new Kahn(graph);

        System.out.println("Kahn algorithm: ");
        if (kahn.tplSort())
        {
            System.out.println("Topological sort: ");
            kahn.printPath();
            System.out.println();
        }
        else
            System.out.println("Topological sort not available!");

    }
}
