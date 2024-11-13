package sortings;

import graphs.Graph;
import structures.CustomLinkedList;
import structures.CustomQueue;

import java.util.Arrays;

public class Demukron2 {

    public static void main(String[] args) {
        test1();
        System.out.println("\n\n");
        test2();
    }

    public static void test1() {
        int[][] matrix = {
//               1  2  3  4  5  6
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0}
        };

        Graph g = new Graph(matrix, matrix.length);
        Demukron2 demukron2 = new Demukron2(g);
        System.out.println("Demukron algorithm: ");
        if (!demukron2.tplSort0())
            System.out.println("Topological sort not available!");
        else {
            System.out.println("\nSorted sequence:");
            int i = 0;
            for (; i < demukron2.path.size() - 1; i++) {
                System.out.printf("%d -> ", demukron2.path.get(i));
            }
            System.out.println(demukron2.path.get(i));
            System.out.println("\nBy levels:");
            demukron2.printLevels();
        }

    }

    public static void test2() {

        int[][] matrix2 = {
//               1  2  3  4  5  6  7  8  9  10 11 12 13 14
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Graph g = new Graph(matrix2, matrix2.length);
        Demukron2 demukron2 = new Demukron2(g);
        System.out.println("Demukron algorithm: ");
        if (!demukron2.tplSort0())
            System.out.println("Topological sort not available!");
        else {
            System.out.println("\nSorted sequence:");
            int i = 0;
            for (; i < demukron2.path.size() - 1; i++) {
                System.out.printf("%d -> ", demukron2.path.get(i));
            }
            System.out.println(demukron2.path.get(i));
            System.out.println("\nBy levels:");
            demukron2.printLevels();
        }

    }

    private final Graph graph;
    private final int gSize;
    private final int[][] gMatrix;

    CustomLinkedList<Integer> path;

    int levelsAmt;
    int[][] level;

    public Demukron2(Graph graph) {
        this.graph = graph;
        this.gSize = graph.getSize();
        this.gMatrix = graph.getMatrixAdj();
        path = new CustomLinkedList<>();
    }

    public void printLevels() {
        for (int i = 0; i < levelsAmt; i++) {
            System.out.printf("level %d: ", i);
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] > 0) System.out.printf("%d, ", level[i][j]);
            }
            System.out.println();
        }
    }


    public boolean tplSort0() {

        int k = 0;
        int[] indegrees = new int[gSize];
        int total = getSumsOverColumns(indegrees);

        int[] vertices = new int[gSize];
        for (int i = 0; i < gSize; i++) vertices[i] = i + 1;

        boolean[] visited = new boolean[gSize];
        level = new int[gSize * (gSize - 1)][];
        k = buildLevel(vertices, indegrees, k);
        total = rowSubtract(indegrees, visited, total, vertices);

        while (total > 0) {
            k = buildLevel(vertices, indegrees, k);
            total = rowSubtract(indegrees, visited, total, vertices);
            if (total == -1) return false;
        }

        CustomQueue<Integer> lvl = new CustomQueue<>();
        for (int i = 0; i < gSize; i++) {
            if (!visited[i]) {
                path.add(i + 1);
                lvl.enqueue(i + 1);
            }
        }
        k = setLevels(lvl, k);                  // write rest to "level" array
        levelsAmt = k;
        return true;
    }


    int rowSubtract(int[] indegrees, boolean[] visited, int ttl, int[] vertices) {
        boolean found = false;
        for (int i = 0; i < gSize; i++) {
            if (visited[i]) continue;
            if (indegrees[i] > 0) continue;
            visited[i] = true;
            found = true;
            path.add(i + 1);
            for (int j = 0; j < gSize; j++) {
                indegrees[j] -= gMatrix[i][j];
                ttl -= gMatrix[i][j];
            }
            break;
        }
        return found ? ttl : -1;
    }

    int buildLevel(int[] vertices, int[] indegrees, int k) {
        CustomQueue<Integer> lvl = new CustomQueue<>();

        if (k == 0) {
            System.out.print("     | ");
            Arrays.stream(vertices).forEach(x -> System.out.printf("%4d", x));
            System.out.println("\n" + "-".repeat(65));
        }
        System.out.printf("k: %d | ", k);
        Arrays.stream(indegrees).forEach(x -> {
            if (x < 0) System.out.printf("%4s", "-");
            else System.out.printf("%4d", x);
        });
        System.out.println();

        for (int i = 0; i < gSize; i++) {
            if (vertices[i] > 0 && indegrees[i] == 0) {
                lvl.enqueue(vertices[i]);
                vertices[i] = -1;
                indegrees[i] = -1;
            }
        }
        if (!lvl.isEmpty()) k = setLevels(lvl, k);

        return k;
    }

    private int setLevels(CustomQueue<Integer> lvl, int k) {
        level[k] = new int[lvl.size()];
        int len = lvl.size();
        for (int i = 0; i < len; i++) {
            level[k][i] = lvl.dequeue();
        }
        k++;
        return k;
    }

    int getSumsOverColumns(int[] sums) {
        int total = 0;
        for (int i = 0; i < gSize; i++) {
            for (int j = 0; j < gSize; j++) {
                sums[i] += gMatrix[j][i];
            }
            total += sums[i];
        }
        return total;
    }

    int getTotal() {
        int total = 0;
        for (int i = 0; i < gSize; i++) {
            for (int j = i; j < gSize; j++) {
                total += gMatrix[i][j];
                total += gMatrix[j][i];
            }
        }
        return total;
    }

}
