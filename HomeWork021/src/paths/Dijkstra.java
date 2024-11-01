package paths;

import graph.Edge;
import graph.Graph;
import structures.CustomStack;

public class Dijkstra {


    // region privates

    private final int[][] matrix;
    private final int size;
    private final int totalWeight;

    // endregion


    public Dijkstra(Graph graph) {
        checkMatrix(graph.getMatrixAdj());
        this.matrix = graph.getMatrixAdj();
        this.size = graph.getSize();
        this.totalWeight = graph.findTotalWeight();
    }


    public int[][] findAllPathCosts() {
        int[][] allCosts = new int[size][size];
        for (int i = 0; i < size; i++) {
            findPathFromTo(allCosts[i], i, size-1);
        }
        return allCosts;
    }

    public Edge[] findPath(int start, int end) {
        int[] costs = new int[size];
        return findPathFromTo(costs, start, end);
    }

    private Edge[] findPathFromTo(int[] paths, int vertex, int goal) {

        boolean[] visited = new boolean[size];
        int[] parent;

        parent = new int[size];
        for (int i = 0; i < size; i++) {
            paths[i] = totalWeight;             // fill max weights instead if infinity
            parent[i] = -1;                     // fill parents
        }
        paths[vertex] = 0;                      // start vertex
        int min = 0;

        for (int i = 0; i < size; i++) {
            min = getMinVert(paths, visited);
            visited[min] = true;
            for (int j = 0; j < size; j++) {
                if (visited[j]) continue;                       // skip visited
                if (matrix[min][j] == 0) continue;              // skip absents/loops

                int weight = paths[min] + matrix[min][j];
                if (weight < paths[j]) {
                    paths[j] = weight;
                    parent[j] = min;
                }
            }
        }

        return restorePath(parent, vertex, goal);
    }

    private Edge[] restorePath(int[] parent, int vertex, int goal) {
        CustomStack<Edge> stack = new CustomStack<>();
        int v = goal;
        while (v != vertex) {
            stack.push(new Edge(parent[v], v, matrix[parent[v]][v]));
            v = parent[v];
        }

        Edge[] path = new Edge[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) { path[i++] = stack.pop(); }
        return path;
    }




    private int getMinVert(int[] weights, boolean[] visited) {
        int min = -1;
        for (int i = 0; i < weights.length; i++) {
            if (visited[i]) continue;
            if (min == -1) min = i;
            else if (weights[i] < weights[min]) min = i;
        }
        return min;
    }


    public int getSize() {return size;}


    void checkMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = i; j < matrix.length; j++)
                if (matrix[i][j] < 0 || matrix[j][i] < 0)
                    throw new IllegalArgumentException("Adjacency matrix must not contain negative weights");
    }

}
