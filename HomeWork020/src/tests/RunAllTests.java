package tests;

import graph.Edge;
import graph.Graph;
import spanningTree.Boruvka;
import spanningTree.Kruskal;
import spanningTree.Prim;
import structures.CustomLinkedList;
import util.GraphUtl;

public class RunAllTests {

    public static void main(String[] args) {

        RunAllTests at = new RunAllTests();

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

        at.testKruskal(graph);
        at.testPrim(graph);
        at.testBoruvka(graph);


    }

    void testKruskal(Graph graph) {
        Kruskal kruskal = new Kruskal(graph);

        kruskal.spanningTree();

        System.out.println("Kruskal algorithm.");
        System.out.println("Spanning tree: ");
        CustomLinkedList<Edge> edges = kruskal.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i));
        }
        System.out.printf("Spanning tree weight: %d\n\n", kruskal.getMinWeight());
    }

    void testPrim(Graph graph) {
        Prim prim = new Prim(graph);

        prim.spanningTree(0);

        System.out.println("Prim algorithm.");
        System.out.println("Spanning tree: ");
        CustomLinkedList<Edge> edges = prim.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i));
        }
        System.out.printf("Spanning tree weight: %d\n\n", prim.getMinWeight());


    }

    void testBoruvka(Graph graph) {

        Boruvka boruvka = new Boruvka(graph);

        boruvka.spanningTree();

        System.out.println("Boruvka algorithm.");
        System.out.println("Spanning tree: ");
        CustomLinkedList<Edge> edges = boruvka.getMinWeightTree();
        for (int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i));
        }
        System.out.printf("Spanning tree weight: %d\n\n", boruvka.getMinWeight());

    }

}
