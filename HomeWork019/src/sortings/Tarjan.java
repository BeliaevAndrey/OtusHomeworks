package sortings;

import graphs.Graph;
import structures.CustomLinkedList;
import structures.CustomStack;

public class Tarjan {

    private enum State {none, seen, cplt}

    private final Graph graph;
    private final int size;
    private final int[][] aMatrix;                      // adjacent matrix
    private final CustomLinkedList<Integer> path;       // path through topologically sorted vertices

    private final State[] vertexStates;

    public Tarjan(Graph graph) {
        this.graph = graph;
        this.aMatrix = graph.getMatrixAdj();
        this.size = graph.getSize();
        path = new CustomLinkedList<>();
        vertexStates = new State[size];
    }

    private void reset() {for (int i = 0; i < size; i++) vertexStates[i] = State.none;}

    public boolean tplSort() {
        path.clear();
        reset();
        CustomStack<Integer> localStack = new CustomStack<>();
        for (int i = 0; i < size; i++) {
            if (vertexStates[i] == State.none) {
                if (!dfs(i, localStack)) return false;
            }
        }
        System.out.println(localStack.size());
        while (!localStack.isEmpty()) path.add(localStack.pop());
        System.out.println(path.size());
        return true;
    }

    private boolean dfs(int vrtInd, CustomStack<Integer> stack) {
        vertexStates[vrtInd] = State.seen;
        for (int i = 0; i < size; i++) {
            if (aMatrix[vrtInd][i] == 0) continue;
            if (vertexStates[i] == State.none) {
                if (!dfs(i, stack)) return false;
            }
        }
        vertexStates[vrtInd] = State.cplt;
        stack.push(vrtInd + 1);
        return true;
    }

    // region setters/getters

    public Graph getGraph() {return graph;}

    public int getSize() {return size;}

    public int[][] getaMatrix() {return aMatrix;}

    public CustomLinkedList<Integer> getPath() {
        return path;
    }


    // endregion

}
