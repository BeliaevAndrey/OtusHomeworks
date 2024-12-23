package sortings;

import graphs.Graph;
import structures.CustomLinkedList;
import structures.CustomQueue;

import java.util.Arrays;

public class Demukron5 {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0}
        };

        int[][] matrix2 = {
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
        Demukron5 demukron2 = new Demukron5(g);
        if (!demukron2.tplSort())
            System.out.println("Fail");
        else {
            for (int i = 0; i < demukron2.path.size(); i++) {
                System.out.printf("%d ", demukron2.path.get(i));
            }
            System.out.println();
            demukron2.printLevels();
        }

        for (int i = 0; i < demukron2.k; i++) {
            System.out.printf("\n%d: ", i);
            for (int j = 0; j < demukron2.lvl[i].length; j++) {
                if (demukron2.lvl[i][j] != 0) System.out.printf("%d ", demukron2.lvl[i][j]);
            }
        }
        System.out.println();

    }

    //    private final Graph graph;
    private final int size;
    private final int[][] matrix;

    CustomLinkedList<Integer> path;

    int levelsAmt;
    int[][] level;

    public Demukron5(Graph graph) {
//        this.graph = graph;
        this.size = graph.getSize();
        this.matrix = graph.getMatrixAdj();
        path = new CustomLinkedList<>();
    }


    public boolean tplSort() {
        int k = 0;
        int[] indegrees = new int[size];
        int total = getSumsOverColumns(indegrees);
        if (total == -1) return false;
        int[] vertices = new int[size];
        for (int i = 0; i < size; i++) vertices[i] = i + 1;
        printIndegrees(vertices, indegrees, k);

        while (checkArr(vertices, indegrees) /*&& total + 2 > 0 && count > 0*/) {
            total = stRow(vertices, indegrees, total);
        }


        return true;
    }

    int stRow(int[] v, int[] d, int total) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (v[i] < 0) continue;
            if (d[i] > 0) continue;
            walkInDgr(v, d);
            v[i] = -1;
            path.add(i + 1);
            found = true;
            for (int j = 0; j < size; j++) {
                d[j] -= matrix[i][j];
                total -= matrix[i][j];
            }
            break;
        }
        if (!found) return -1;
        return total;
    }

    int k = 0;
    int[][] lvl = new int[14][14];
    void walkInDgr(int[] v, int[] d) {

        for (int i = 0; i < size; i++) {
            if (d[i] == 0) {
                lvl[k][i] = v[i];
                d[i] = -1;
            }
        }
        k++;
    }

    private void printIndegrees(int[] v, int[] d, int flag) {
        if (flag == 0) {
            Arrays.stream(v).forEach(x -> System.out.printf("%-4d", x));
            System.out.println();
        }
        Arrays.stream(d).forEach(x -> {
            if (x > -1) System.out.printf("%-4d", x);
            else System.out.printf("%-4s", "-");
        });
        System.out.println();
    }

    boolean checkArr(int[] v, int[] d) {
        printIndegrees(v, d, 1);
        for (int j : v) if (j >= 0) return true;
        return false;
    }


    int getSumsOverColumns(int[] sums) {
        int total = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sums[i] += matrix[j][i];
            }
            total += sums[i];
        }
        return total;
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
}
