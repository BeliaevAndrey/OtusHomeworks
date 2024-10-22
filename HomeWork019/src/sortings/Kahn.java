package sortings;

import graphs.Graph;
import structures.CustomLinkedList;

public class Kahn {

    private final Graph graph;
    private final int gSize;
    private final int[][] gMatrix;


    private enum State {none, seen, cplt}

    private final State[] vertexStates;
    private CustomLinkedList<Integer> path;

    public Kahn(Graph graph) {
        this.graph = graph;
        this.gMatrix = graph.getMatrixAdj();
        this.gSize = graph.getSize();
        path = new CustomLinkedList<>();
        vertexStates = new State[gSize];

    }

    public boolean tplSort() {

        reset();                    // set vertex states to State.none
        path.clear();
        boolean isAny;              // vertices with non-0 indegree
        int dltAmt;                 // deleted vertices amount
        do {
            isAny = false;
            dltAmt = 0;
            for (int i = 0; i < gSize; i++) {
                int sum = 0;
                if (vertexStates[i] != State.none) {
                    dltAmt++;
                    continue;
                }
                for (int j = 0; j < gSize; j++) {
                    if (vertexStates[j] != State.cplt)
                        sum += gMatrix[j][i];           // sum over column
                }
                if (sum == 0) {
                    vertexStates[i] = State.cplt;       // vertex complete
                    path.add(i + 1);                    // add vertex num to path
                    isAny = true;
                    break;
                }
            }
        } while ((isAny) && (dltAmt < gSize));

        return dltAmt == gSize;
    }

    private void reset() {for (int i = 0; i < gSize; i++) vertexStates[i] = State.none;}

    public CustomLinkedList<Integer> getPath() { return path; }

    public void printPath() {
        int len = path.size();
        for (int i = 0; i < len; i++) {
            System.out.printf((i < len - 1 ? "%d, " : "%d\n"),  path.get(i));
        }
    }

}
