package graphs;

import structures.CustomLinkedList;


public class Demukron {

    private final Graph graph;
    private final int gSize;
    private final int[][] gMatrix;      // adjacent matrix

    CustomLinkedList<Integer> path;     // path through topologically sorted vertices

    public Demukron(Graph graph) {
        this.graph = graph;
        this.gSize = graph.getSize();
        this.gMatrix = graph.getMatrixAdj();
        path = new CustomLinkedList<>();
    }


    public boolean tplSort() {
        path.clear();
        int[] inDegrees = new int[gSize];               // in-degrees
        boolean[] visited = new boolean[gSize];         // visited vertices
        int sum = 0;                                    // sum over matrix
        for (int i = 0; i < gSize; i++) {
            inDegrees[i] = 0;
            for (int j = 0; j < gSize; j++) {
                inDegrees[i] += gMatrix[j][i];          // count in-degrees of vertices
                sum += gMatrix[j][i];                   // sum over matrix
            }
        }
        do {
            boolean isAny = false;
            for (int i = 0; i < gSize; i++) {
                if (visited[i]) continue;               // skip vertex (column) if visited
                if (inDegrees[i] > 0) continue;         // skip if non-0 indegree
                visited[i] = true;                      // mark visited
                isAny = true;                           // non-0 indegree vertex
                path.add(i + 1);                        // add to path
                for (int j = 0; j < gSize; j++) {
                    inDegrees[j] -= gMatrix[i][j];
                    sum -= gMatrix[i][j];
                }
                break;                                  // vertex found, breaking cycle
            }
            if (!isAny) return false;                   // non-0 indegree vertices not found => can't be sorted
        } while (sum > 0);
        for (int i = 0; i < gSize; i++) {
            if (!visited[i]) path.add(i + 1);           // add non-visited vertices
        }
        return true;
    }

    public void printPath() {
        for (int i = 0; i < path.size(); i++) {
            System.out.printf("%d <- ",  path.get(i));
        }
    }

}
