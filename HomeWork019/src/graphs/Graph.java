package graphs;

import structures.CustomLinkedList;
import structures.CustomQueue;
import structures.CustomStack;


public class Graph {

    private enum State {none, seen, cplt}


    private final int[][] matrixAdj;
    private final int size;
    private final State[] vertexStates;
    private final CustomStack<Edge> edges;

    private final CustomLinkedList<Integer> path;

    public Graph(int[][] matrixAdj, int size) {
        this.matrixAdj = matrixAdj;
        this.size = size;
        vertexStates = new State[size];
        path = new CustomLinkedList<>();
        edges = new CustomStack<>();
    }

    private void reset() {for (int i = 0; i < size; i++) vertexStates[i] = State.none;}

    /**
     *  Depth-first search starter
     */
    public boolean dfs(int begin, int end, boolean isRecursive) {

        reset();         // set vertex states to State.none
        boolean z;
        if (isRecursive) z = recDFS(begin, end);
        else z = cycleDFS(begin, end);
        if (!z) return false;
        setPath(begin, end);
        return true;

    }


    /**
     *  Depth-first search -- recursive version
     */
    boolean recDFS(int begin, int end) {
        vertexStates[begin] = State.seen;
        if (begin == end)
            return true;
        for (int i = 0; i < size; i++) {
            if (vertexStates[i] != State.none) continue;
            if (matrixAdj[begin][i] == 0) continue;
            Edge edge = new Edge(begin, i);
            edges.push(edge);
            if (recDFS(i, end))
                return true;
        }
        return false;
    }



    /**
     *  Depth-first search -- cyclic version
     */
    boolean cycleDFS(int begin, int end) {
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(begin);
        boolean found = false;
        while (!stack.isEmpty()) {
            int z = stack.pop();
            vertexStates[z] = State.cplt;
            for (int i = size - 1; i >= 0; i--) {
            if (matrixAdj[z][i] == 0) continue;
            if (vertexStates[i] == State.cplt) continue;
            Edge edge = new Edge(z, i);
            edges.push(edge);
            if (i == end) found = true;
            if (vertexStates[i] == State.seen) continue;
            stack.push(i);
            vertexStates[i] = State.seen;
            }
        }
        return found;
    }

    private void setPath(int begin, int end) {
        path.clear();
        if (edges.isEmpty()) return;
        int goal = end;
        path.add(goal + 1);
        while (!edges.isEmpty()) {
            Edge e = edges.pop();
            if (e.getEnd() != goal) continue;
            goal = e.getBegin();
            path.add(goal + 1);
        }
        path.reverse();
    }

    /** Breadth-first search;
     *  appropriate for searching the shortest path
     */
    public boolean bfs(int begin, int end) {
        reset();
        CustomQueue<Integer> queue = new CustomQueue<>();
        queue.enqueue(begin);
        boolean found = false;
        while (!queue.isEmpty())
        {
            int z = queue.dequeue();
            vertexStates[z] = State.cplt;
            for (int i = 0; i < size; i++) {
                if (matrixAdj[z][i] == 0) continue;
                if (vertexStates[i] != State.none) continue;
                Edge edge = new Edge(z, i);
                edges.push(edge);
                if (i == end)
                {
                    found = true;
                    break;
                }
                queue.enqueue(i);
                vertexStates[i] = State.seen;
            }
            if (found) break;
        }
        if (found) {
            setPath(begin, end);
            return true;
        }
        return false;
    }


    // region Getters

    public CustomLinkedList<Integer> getPath() { return path; }

    public int getSize() { return size; }

    public int[][] getMatrixAdj() { return matrixAdj; }

    // endregion

}

class Edge {
    private int begin;
    private int end;

    public Edge(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    // region setters/getters

    public void setBegin(int begin) {this.begin = begin;}

    public int getEnd() {return end;}

    public void setEnd(int end) {this.end = end;}

    public int getBegin() {return begin;}

    // endregion
}
