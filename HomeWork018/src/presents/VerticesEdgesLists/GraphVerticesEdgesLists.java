/**
 * Структура данных для представления графа.
 * Граф представляется в виде множества (массива, списка) вершин и множества рёбер.
 */
package presents.VerticesEdgesLists;

import java.util.Arrays;

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
}

















