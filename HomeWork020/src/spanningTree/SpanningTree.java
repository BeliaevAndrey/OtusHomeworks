package spanningTree;

import graph.Edge;
import graph.Graph;
import structures.CustomLinkedList;
import structures.CustomQueue;

public class SpanningTree {

    // region

    private final int[][] matrix;

    private final int size;

    private final State[] vertexStates;

    private final CustomLinkedList<Edge> edges;

    private enum State {none, seen, cplt}

    // endregion



    public SpanningTree(Graph graph) {
        this.matrix = graph.getMatrixAdj();
        this.size = graph.getSize();

        edges = new CustomLinkedList<>();
        vertexStates = new State[size];
        reset();
    }

    private void reset() {for (int i = 0; i < size; i++) vertexStates[i] = State.none;}

    public void bfs(int begin) {
        CustomQueue<Integer> queue = new CustomQueue<>();
        queue.enqueue(begin);
        while (!queue.isEmpty()) {
            int z = queue.dequeue();
            vertexStates[z] = State.cplt;

            for (int i = 0; i < size; i++) {
                if (matrix[z][i] == 0) continue;
                if (vertexStates[i] != State.none) continue;

                Edge edge = new Edge(z, i);
                edges.add(edge);
                queue.enqueue(i);
                vertexStates[i] = State.seen;
            }
        }
    }

    // region setters

    public int getSize() { return size; }

    public CustomLinkedList<Edge> getEdges() { return edges; }

    // endregion
}
