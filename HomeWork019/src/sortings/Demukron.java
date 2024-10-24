package sortings;

import graphs.Graph;
import structures.CustomLinkedList;
import structures.CustomStack;

import java.util.Arrays;

public class Demukron {


    private final int[][] aMatrix;      // adjacency matrix

    private final int size;             // graph size

    CustomLinkedList<Integer> path;     // list for topologically sorted vertices

    int[][] level;      // resulting array

    int levelsAmt = 0;

    public Demukron(Graph graph) {
        this.size = graph.getSize();
        this.aMatrix = graph.getMatrixAdj();
        path = new CustomLinkedList<>();
    }

    public boolean tplSort() {
        int total = 0;                              // sum over adjacency matrix
        boolean[] visited = new boolean[size];      // visited vertexes array
        int[] indegrees = new int[size];            // indegrees array
        int[] vertices = new int[size];             // vertices array

        for (int i = 0; i < size; i++) {
            vertices[i] = i + 1;
            for (int j = 0; j < size; j++) {
                indegrees[i] += aMatrix[j][i];      // calc. indegrees array
                total += aMatrix[j][i];             // calc. overall sum
            }
        }

        level = new int[size][];
        CustomStack<Integer> lvl = new CustomStack<>();     // level vertex stack
        int k = 0;                                          // level counter

        k = buildLevel(indegrees, vertices, k);
        total = rowSubtraction(indegrees, visited, total);
        total = rowSubtraction(indegrees, visited, total);

        while (total > 0) {

            k = buildLevel(indegrees, vertices, k);             // collect vertices at level; get next level number
            total = rowSubtraction(indegrees, visited, total);  // subtract row from indegrees array

            if (total == -1) return false;
        }

        for (int i = 0; i < size; i++)          // add rest vertices
            if (!visited[i]) {
                lvl.push(i + 1);
                path.add(i + 1);
            }

        k = setLevels(lvl, k);                  // write rest to "level" array
        levelsAmt = k;
        return true;
    }

    private int rowSubtraction(int[] indegrees, boolean[] visited, int total) {

        boolean found = false;

        for (int i = 0; i < size; i++) {
            if (visited[i]) continue;
            if (indegrees[i] > 0) continue;

            visited[i] = true;                      // mark vertex as visited
            found = true;                           // set 0-indegree vertex found
            path.add(i + 1);                        // add to path
            for (int j = 0; j < size; j++) {
                indegrees[j] -= aMatrix[i][j];      // consequent subtraction of a row
                total -= aMatrix[i][j];             // decrease overall counter
            }

            break;      // vertex found => break inner cycle
        }
        return found ? total : -1;
    }

    int buildLevel(int[] indegrees, int[] vertices, int k) {

        CustomStack<Integer> lvl = new CustomStack<>();

        for (int i = 0; i < size; i++) {
            if (indegrees[i] == 0 && vertices[i] > 0) {
                lvl.push(vertices[i]);
                vertices[i] = -1;
                indegrees[i] = -1;
            }
        }

        if (!lvl.isEmpty()) k = setLevels(lvl, k);

        return k;
    }
    private int setLevels(CustomStack<Integer> lvl, int k) {
        level[k] = new int[lvl.size()];
        for (int i = lvl.size(); i > 0; i--) {
            level[k][i - 1] = lvl.pop();
        }
        k++;
        return k;
    }


    public void printLevels() {
        for (int i = 0; i < levelsAmt; i++) {
            System.out.printf("level %d: ", i);
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] > 0) System.out.printf("%d ", level[i][j]);
            }
            System.out.println();
        }
    }


    public CustomLinkedList<Integer> getPath() {return path;}

    public void printPath() {
        int len = path.size();
        for (int i = 0; i < len; i++) {
            System.out.printf((i < len - 1 ? "%d, " : "%d\n"), path.get(i));
        }
    }


}
