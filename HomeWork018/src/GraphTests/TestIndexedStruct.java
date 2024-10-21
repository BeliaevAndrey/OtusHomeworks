package GraphTests;

import presents.Matrices.GraphIndexedStructure;

import java.util.Arrays;

public class TestIndexedStruct {

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

        TestIndexedStruct tis = new TestIndexedStruct();
        tis.printMatrix(adjMatrix);

        GraphIndexedStructure gis = new GraphIndexedStructure();
        gis.buildFromAdjacencyMatrix(adjMatrix);
        gis.printSelf();

    }

    void printMatrix(int[][] m) {
        for (int [] row : m) {
            Arrays.stream(row).forEach(v -> System.out.printf("%d\t", v));
            System.out.println();
        }
        System.out.println("\n");
    }
}
