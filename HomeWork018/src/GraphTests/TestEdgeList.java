package GraphTests;

import presents.VerticesEdgesLists.GraphEdgeList;

public class TestEdgeList {

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

        GraphEdgeList gel = new GraphEdgeList();
        gel.buildFromAdjacencyMatrix(adjMatrix);
        gel.printEdgesList();
    }
}