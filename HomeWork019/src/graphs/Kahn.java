package graphs;

import structures.CustomLinkedList;

public class Kahn {

    private final Graph graph;
    private final int gSize;
    private final int[][] gMatrix;


    private enum State {none, seen, cplt}

    private final State[] vertex;
    CustomLinkedList<Integer> path;

    public Kahn(Graph graph) {
        this.graph = graph;
        this.gMatrix = graph.getMatrixAdj();
        this.gSize = graph.getSize();
        path = new CustomLinkedList<>();
        vertex = new State[gSize];

    }

    boolean tplSort() {

        reset();            // set vertex states to State.none
        path.clear();
        boolean isAny;      // vertices with non-0 indegree
        int dltAmt;         // deleted amount
        do {
            isAny = false;
            dltAmt = 0;
            for (int i = 0; i < gSize; i++) {
                int sum = 0;
                if (vertex[i] != State.none) {
                    dltAmt++;
                    continue;
                }
                for (int j = 0; j < gSize; j++) {
                    if (vertex[j] != State.cplt)
                        sum += gMatrix[j][i];   // sum over column
                }
                if (sum == 0) {
                    vertex[i] = State.cplt;     // vertex complete
                    path.add(i + 1);            // add vertex num to path
                    isAny = true;
                    break;
                }
            }
        } while ((isAny) && (dltAmt < gSize));

        return dltAmt == gSize;
    }

    private void reset() {for (int i = 0; i < gSize; i++) vertex[i] = State.none;}

}
