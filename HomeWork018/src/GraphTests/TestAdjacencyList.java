package GraphTests;

import presents.VerticesEdgesLists.GraphAdjacencyList;


public class TestAdjacencyList {

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

        GraphAdjacencyList gal = new GraphAdjacencyList();
        gal.buildFromAdjacencyMatrix(adjMatrix);
        gal.printSelf();
    }
}
