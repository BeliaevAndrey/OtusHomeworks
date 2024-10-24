package presents.Lists;

import java.util.Arrays;

/**
 * Структура данных для представления графа.
 * Граф представляется в виде множества (массива, списка) вершин и множества рёбер.
 */

public class GraphVerticesEdgesLists {

    Vertex[] vertices;
    Edge[] edges;

    public GraphVerticesEdgesLists(Vertex[] vertices, Edge[] edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Vertex[] getVertices() {return vertices;}

    public Edge[] getEdges() {return edges;}

    @Override
    public String toString() {
        return String.format("V = { %s }\nE = { %s }",
                        Arrays.toString(getVertices()),
                        Arrays.toString(getEdges()))
                .replaceAll("]", "")
                .replaceAll("\\[", "");
    }

    public void printSelf() {
        System.out.println(this);
    }
}
















