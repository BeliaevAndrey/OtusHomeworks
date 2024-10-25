package spanningTree;

import graph.Edge;
import graph.Graph;
import structures.CustomLinkedList;
import structures.CustomQueue;

public class Prim {


    // region privates
    enum State {none, seen, cplt}

    private final int[][] matrix;

    private final int size;

    private final State[] vertexStates;

    private final CustomLinkedList<Edge> edges;

    private final CustomQueue<Edge> edgesQ;

    private int totalWeight = 0;

    private int minWeight = 0;

    // endregion


    public Prim(Graph graph) {
        matrix = graph.getMatrixAdj();
        size = graph.getSize();
        edges = new CustomLinkedList<>();
        edgesQ = new CustomQueue<>();
        vertexStates = new State[size];
        reset();
    }

    private void reset() {for (int i = 0; i < size; i++) vertexStates[i] = State.none;}

    public void spanningTree(int begin) {
        vertexStates[begin] = State.seen;                   // begin is visited
        int count = 1;
        totalWeight = findTotalWeight();

        while (count++ < size) {
            Edge edge = searchMin();
            edgesQ.enqueue(edge);
            vertexStates[edge.getEnd()] = State.seen;       // end is visited
        }

        while (!edgesQ.isEmpty()) {
            edges.add(edgesQ.dequeue());
        }
    }

    private Edge searchMin() {
        int minWgt = totalWeight;   // local min weight
        int begin = 0;
        int end = 0;
        for (int i = 0; i < size; i++) {
            if (vertexStates[i] == State.none) continue;        // 'begin' must be 'seen'
            for (int j = 0; j < size; j++) {
                if (vertexStates[j] != State.none) continue;    // 'end' must be non-visited
                if (matrix[i][j] == 0) continue;                // zero weight => skip
                if (matrix[i][j] < minWgt) {
                    minWgt = matrix[i][j];
                    begin = i;
                    end = j;
                }
            }
        }

        minWeight += minWgt;    // count weight in total minWeight

        return new Edge(begin, end, minWgt);
    }


    public int findTotalWeight() {
        int weight = 0;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                weight += matrix[i][j];

        return weight;
    }

    // region getters

    public int[][] getMatrix() {return matrix;}

    public int getSize() {return size;}

    public CustomLinkedList<Edge> getEdges() {return edges;}

    public int getMinWeight() {return minWeight;}

    // endregion
}
