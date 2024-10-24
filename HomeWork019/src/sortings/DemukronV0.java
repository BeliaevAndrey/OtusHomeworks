package sortings;

import graphs.Graph;
import structures.CustomLinkedList;


public class DemukronV0 {

    private final int gSize;
    private final int[][] aMatrix;              // adjacent matrix



    private final CustomLinkedList<Integer> path;     // path through topologically sorted vertices

    public DemukronV0(Graph graph) {
        this.gSize = graph.getSize();
        this.aMatrix = graph.getMatrixAdj();
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
                inDegrees[i] += aMatrix[j][i];          // count in-degrees of vertices
                sum += aMatrix[j][i];                   // sum over matrix
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
                    inDegrees[j] -= aMatrix[i][j];      // subtraction of 0-indegree part
                    sum -= aMatrix[i][j];               // subtract from total
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


    public CustomLinkedList<Integer> getPath() {return path;}

    public void printPath() {
        int len = path.size();
        for (int i = 0; i < len; i++) {
            System.out.printf((i < len - 1 ? "%d, " : "%d\n"), path.get(i));
        }
    }




}
