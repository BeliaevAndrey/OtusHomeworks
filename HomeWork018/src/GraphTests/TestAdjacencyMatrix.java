package GraphTests;

import presents.Matrices.GraphAdjacencyMatrix;

public class TestAdjacencyMatrix {

    /* Матрица смежности двудольного графа А(3,4) с 5 рёбрами.

        int[][] adjMatrix = {               //     Вектор смежности
                {0, 1, 0, 0, 0, 0, 0},      //     1| 2 0 0
                {1, 0, 1, 0, 0, 0, 1},      //     2| 1 3 7
                {0, 1, 0, 0, 0, 0, 0},      //     3| 2 0 0
                {0, 0, 0, 0, 1, 0, 0},      //     4| 5 0 0
                {0, 0, 0, 1, 0, 1, 0},      //     5| 6 0 0
                {0, 0, 0, 0, 1, 0, 0},      //     6| 5 0 0
                {0, 1, 0, 0, 0, 0, 0}       //     7| 2 0 0
        };
     */

    public static void main(String[] args) {
        int[][] adjacencyVec = new int[][]{
                {2, 0, 0},
                {1, 3, 7},
                {2, 0, 0},
                {5, 0, 0},
                {6, 0, 0},
                {5, 0, 0},
                {2, 0, 0}};

        GraphAdjacencyMatrix gam = new GraphAdjacencyMatrix();
        gam.buildFromAdjacencyVector(adjacencyVec);
        gam.printAMatrix();

    }
}
