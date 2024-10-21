package GraphTests;

import presents.Lists.Edge;
import presents.Lists.GraphVerticesEdgesLists;
import presents.Lists.Vertex;
import structures.CustomLinkedList;


public class TestGraphVerticesEdgesLists {

    public static void main(String[] args) {

        int[][] adjMatrix = {
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0}
        };

        TestGraphVerticesEdgesLists tst = new TestGraphVerticesEdgesLists();
        GraphVerticesEdgesLists graph = tst.buildGraph(adjMatrix);
        System.out.println(graph);

        for (Vertex v : graph.getVertices()) {
            System.out.print(v.getId() + ": ");
            for (Edge e : v.getEdges()) {
                System.out.print(e + ", ");
            }
            System.out.println();
        }
    }

    /**
     * Заполнение графа, представленного в виде списка вершин и списка рёбер,
     * основываясь на матрице смежности.
     * @param matrix матрица смежности
     * @return GraphVerticesEdgesLists
     */
    GraphVerticesEdgesLists buildGraph(int[][] matrix) {
        int size = matrix.length;
        Vertex[] vertices = new Vertex[size];
        CustomLinkedList<Edge> edges = new CustomLinkedList<>();

        for (int i = 0; i < size; i++) { vertices[i] = new Vertex(i + 1); }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (vertices[j].isVisited()) continue;
                if (matrix[j][i] == 1) {
                    Edge e = new Edge(vertices[i], vertices[j]);
                    vertices[i].addEdge(e);
                    edges.add(e);
                    vertices[j].addEdge(new Edge(vertices[j], vertices[i]));
                    vertices[i].setVisited(true);
                    vertices[j].setVisited(true);
                }
            }
        }
        Edge[] edgesFin = new Edge[edges.size()];
        int i = 0;
        for (Object o : edges.toArray()) { edgesFin[i++] = (Edge) o; }

        return new GraphVerticesEdgesLists(vertices, edgesFin);

    }

}
