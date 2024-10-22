package tests;

import graphs.Graph;
import sortings.Demukron;
import sortings.Kahn;
import sortings.Tarjan;
import util.GraphUtl;

public class AllTests {

    public static void main(String[] args) {
        AllTests allTests = new AllTests();

        allTests.testDigraph1();
        System.out.println("=".repeat(40));
        allTests.testDigraph2();

    }

    public void testDigraph1() {

        GraphUtl utl = new GraphUtl();

        int[][] vecAdj = {
                {2, 4},
                {6, 0},
                {2, 0},
                {6, 0},
                {0, 0},
                {5, 0}
        };

        int[][] matrixAdj = utl.vectorAjcToMatrixAjc(vecAdj);   // convert vector to adjacency matrix
//        utl.printMatrix(matrixAdj);
        Graph graph = new Graph(matrixAdj, matrixAdj.length);

        // Kahn test 1

        Kahn kahn = new Kahn(graph);

        System.out.println("Kahn algorithm: ");
        if (kahn.tplSort()) {
            System.out.println("Topological sort: ");
            kahn.printPath();
            System.out.println();
        } else {
            System.out.println("Topological sort not available!");
        }

        // Demukron test 1

        Demukron demukron = new Demukron(graph);

        System.out.println("\nDemukron algorithm: ");
        if (demukron.tplSort())
        {
            System.out.println("Topological sort: ");
            demukron.printPath();
            System.out.println();
        }
        else
            System.out.println("Topological sort not available!");


        // Tarjan test 1

        Tarjan tarjan = new Tarjan(graph);

        System.out.println("\nTarjan algorithm: ");
        if (tarjan.tplSort())
        {
            System.out.println("Topological sort: ");
            tarjan.printPath();
            System.out.println();
        }
        else
            System.out.println("Topological sort not available!");

    }

    public void testDigraph2() {

            /* Digraph from conspectus
            2  | 0  0  0          // 1 | 0  0  0
            3  | 8  10 0          // 2 | 5  7  0
            5  | 11 0  0          // 3 | 8  0  0
            7  | 11 8  0          // 4 | 8  5  0
            8  | 9  0  0          // 5 | 6  0  0
            9  | 0  0  0          // 6 | 0  0  0
            10 | 0  0  0          // 7 | 0  0  0
            11 | 2  9  10         // 8 | 1  6  7
            */

        GraphUtl utl = new GraphUtl();

        int[][] vecAdj2 = {
                {0,  0,  0},
                {5,  7,  0},
                {8,  0,  0},
                {8,  5,  0},
                {6,  0,  0},
                {0,  0,  0},
                {0,  0,  0},
                {1,  6,  7}
        };

        int[][] matrixAdj = utl.vectorAjcToMatrixAjc(vecAdj2);
//        utl.printMatrix(matrixAdj);
        Graph graph = new Graph(matrixAdj, matrixAdj.length);

        int[] payload = new int[] { 2, 3, 5, 7, 8, 9, 10, 11 };

        // Kahn test 2

        Kahn kahn = new Kahn(graph);

        System.out.println("Kahn algorithm: ");
        if (kahn.tplSort()) {
            System.out.println("Topological sort: ");
//            kahn.printPath();
            for (int i = 0; i < kahn.getPath().size(); i++) {
                System.out.printf("%d, ", payload[kahn.getPath().get(i) - 1]);
            }
            System.out.println();
        } else {
            System.out.println("Topological sort not available!");
        }
        // Demukron test 2
        Demukron demukron = new Demukron(graph);

        System.out.println("\nDemukron algorithm: ");
        if (demukron.tplSort())
        {
            System.out.println("Topological sort: ");
//            demukron.printPath();
            for (int i = 0; i < demukron.getPath().size(); i++) {
                System.out.printf("%d, ", payload[demukron.getPath().get(i) - 1]);
            }
            System.out.println();
        }
        else
            System.out.println("Topological sort not available!");


        // Tarjan test 2

        Tarjan tarjan = new Tarjan(graph);

        System.out.println("\nTarjan algorithm: ");
        if (tarjan.tplSort())
        {
            System.out.println("Topological sort: ");
//            tarjan.printPath();
            for (int i = 0; i < tarjan.getPath().size(); i++) {
                System.out.printf("%d, ", payload[tarjan.getPath().get(i) - 1]);
            }
            System.out.println();
        }
        else
            System.out.println("Topological sort not available!");
    }

}
