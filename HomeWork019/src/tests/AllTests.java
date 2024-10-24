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
        utl.printMatrix(matrixAdj);
        System.out.println();
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

        int[][] vecAdj = {
                {  3  ,  13 ,  0  , 0 },
                {  13 ,  0  ,  0  , 0 },
                {  0  ,  0  ,  0  , 0 },
                {  3  ,  0  ,  0  , 0 },
                {  3  ,  9  ,  10 , 0 },
                {  4  ,  11 ,  12 , 13},
                {  11 ,  0  ,  0  , 0 },
                {  2  ,  4  ,  6  , 7 },
                {  1  ,  14 ,  0  , 0 },
                {  1  ,  7  ,  12 , 0 },
                {  3  ,  0  ,  0  , 0 },
                {  0  ,  0  ,  0  , 0 },
                {  3  ,  0  ,  0  , 0 },
                {  6  ,  0  ,  0  , 0 }};

        GraphUtl utl = new GraphUtl();

        int[][] matrixAdj = utl.vectorAjcToMatrixAjc(vecAdj);
        utl.printMatrix(matrixAdj);
        System.out.println();
        Graph graph = new Graph(matrixAdj, matrixAdj.length);

        int[] payload = new int[] { 2, 3, 5, 7, 8, 9, 10, 11 };

        // Kahn test 2

        Kahn kahn = new Kahn(graph);

        System.out.println("Kahn algorithm: ");
        if (kahn.tplSort()) {
            System.out.println("Topological sort: ");
            kahn.printPath();

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
            demukron.printPath();

        }
        else
            System.out.println("Topological sort not available!");


        // Tarjan test 2

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

}
