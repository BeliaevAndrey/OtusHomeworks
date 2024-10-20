package GraphTests;

import presents.Matrices.GraphIncidenceMatrix;

public class TestIncidenceMatrix {

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

        GraphIncidenceMatrix gim = new GraphIncidenceMatrix();
        gim.buildFromAdjacentMatrix(adjMatrix);
        gim.printIncidenceMatrix();
    }
}
