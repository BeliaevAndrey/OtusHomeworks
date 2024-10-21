package GraphTests;

import presents.AdjcencyArray.GraphAdjacencyArray;
import presents.AdjcencyArray.GraphAdjacencyVector;

public class TestAdjacencyVector {

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

        GraphAdjacencyVector gaVec = new GraphAdjacencyVector();
        gaVec.buildFromAdjacencyMatrix(adjMatrix);
        gaVec.printSelf();

    }
}
