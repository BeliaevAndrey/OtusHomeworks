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

    private void reset() {for (int i = 0; i < size; i++) vertexStates[i] = State.none;}

    // region setters/getters

    public int[][] getMatrixAdj() { return matrixAdj; }

    public int getSize() { return size; }

    public State[] getVertexStates() { return vertexStates; }

    public CustomQueue<Edge> getEdges() { return edges; }

    public CustomLinkedList<Integer> getPath() { return path; }


    // endregion

}
