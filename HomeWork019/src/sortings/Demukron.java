package sortings;

import graphs.Graph;
import structures.CustomLinkedList;
import structures.CustomStack;

import java.util.Arrays;

public class Demukron {

    enum State {none, seen, cplt}

    private final int[][] aMatrix;

    private final int size;

    CustomLinkedList<Integer> path;

    int[][] level;
    int[][] levelZ;
    int levelsAmt = 0;

    public Demukron(Graph graph) {
        this.size = graph.getSize();
        this.aMatrix = graph.getMatrixAdj();
        path = new CustomLinkedList<>();
    }

    public boolean tplSort() {
        int total = 0;
        boolean[] visited = new boolean[size];
        int[] indegrees = new int[size];
        int[] vertices = new int[size];

        for (int i = 0; i < size; i++) {
            vertices[i] = i + 1;
            for (int j = 0; j < size; j++) {
                indegrees[i] += aMatrix[j][i];
                total += aMatrix[j][i];
            }
        }

        level = new int[size][];
        CustomStack<Integer> lvl = new CustomStack<>();
        int k = 0;

        k = buildLevel(indegrees, vertices, k);
        total = rowSubtraction(indegrees, visited, total);
        total = rowSubtraction(indegrees, visited, total);


        while (total > 0) {

            k = buildLevel(indegrees, vertices, k);
            total = rowSubtraction(indegrees, visited, total);

            if (total == -1) return false;
        }

        for (int i = 0; i < size; i++)
            if (!visited[i]) {
                lvl.push(i + 1);
                path.add(i + 1);
            }

        k = setLevels(lvl, k);
        levelsAmt = k;
        return true;
    }

    private int rowSubtraction(int[] indegrees, boolean[] visited, int total) {

        boolean found = false;

        for (int i = 0; i < size; i++) {
            if (visited[i]) continue;
            if (indegrees[i] > 0) continue;

            visited[i] = true;
            found = true;
            path.add(i + 1);
            for (int j = 0; j < size; j++) {
                indegrees[j] -= aMatrix[i][j];
                total -= aMatrix[i][j];
            }

            break;
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
