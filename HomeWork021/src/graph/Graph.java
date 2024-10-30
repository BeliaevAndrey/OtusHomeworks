package graph;

import structures.CustomLinkedList;
import structures.CustomQueue;

public class Graph {

    // region privates

    private enum State {none, seen, cplt}

    private final int[][] matrixAdj;

    private final int size;

    private final State[] vertexStates;

    private final CustomQueue<Edge> edges;

    private final CustomLinkedList<Integer> path;

    // endregion


    public Graph(int[][] matrixAdj, int size) {
        this.matrixAdj = matrixAdj;
        this.size = size;

        path = new CustomLinkedList<>();
        edges = new CustomQueue<>();

        vertexStates = new State[size];

        reset();
    }



    /**
     * Find total of edge weights
     * @return sum af all weights: int
     */
    public int findTotalWeight() {
        int ttlWt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                ttlWt += matrixAdj[i][j] > 0 ? matrixAdj[i][j] : -matrixAdj[i][j];
                ttlWt += matrixAdj[j][i] > 0 ? matrixAdj[j][i] : -matrixAdj[j][i];
            }
        }
        return ttlWt;
    }


    private void reset() {for (int i = 0; i < size; i++) vertexStates[i] = State.none;}

    // region setters/getters

    public int[][] getMatrixAdj() { return matrixAdj; }

    public int getSize() { return size; }

    public State[] getVertexStates() { return vertexStates; }

    public CustomQueue<Edge> getEdges() { return edges; }

    public CustomLinkedList<Integer> getPath() { return path; }


    // endregion

}
