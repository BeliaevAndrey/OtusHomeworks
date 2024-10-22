package Kosaraju;

import structures.CustomLinkedList;
import structures.CustomStack;

public class Kosaraju {

    private enum State {none, seen, cplt}
    private final int[][] aMatrix;
    private final int size;

    State[] vertexStates;

    private final CustomLinkedList<Integer> components;
    private final CustomLinkedList<CustomLinkedList<Integer>> compAll;



    public CustomLinkedList<CustomLinkedList<Integer>> getCompAll() {
        return compAll;
    }

    public Kosaraju(int[][] aMatrix) {
        this.aMatrix = aMatrix;
        size = aMatrix.length;
        vertexStates  = new State[size];
        components = new CustomLinkedList<>();
        compAll = new CustomLinkedList<>();
        resetStates();
    }

    public void kosarajuSearch() {

        int[][] reverse = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = i; j < size; j++) {
                reverse[i][j] = aMatrix[j][i];
                reverse[j][i] = aMatrix[i][j];
            }

        CustomStack<Integer> vertNumStack = new CustomStack<>();
        resetStates();                      // reset array of completed vertices

        for (int i = 0; i < size; i++)
            dfs(reverse, i, vertNumStack);  // search for vertices of strong connected component in reversed graph

        resetStates();                      // reset array of completed vertices
        components.clear();                 // clear components of graph with reversed connections
        CustomStack<Integer> stack = new CustomStack<>();

        while (!vertNumStack.isEmpty()) {
            int z = vertNumStack.pop();

            dfs(aMatrix, z, stack);         // search for vertices of strong connected component in origin graph

            CustomLinkedList<Integer> list = new CustomLinkedList<>();  // component vertices list

            while (!stack.isEmpty()) list.add(stack.pop() + 1);

            if (list.size() > 0) compAll.add(list);         // put strong connected components to list

        }
    }

    private void dfs(int[][] matrix, int vertex, CustomStack<Integer> stack) {

        if (vertexStates[vertex] != State.none) return;

        vertexStates[vertex] = State.cplt;          // mark vertex completed

        for (int i = 0; i < size; i++) {
            if (matrix[vertex][i] == 0) continue;
            dfs(matrix, i, stack);                  // recursive call with next vertex
        }

        stack.push(vertex);                         // put vertex to stack
    }


    private void resetStates() {
        for (int i = 0; i < size; i++) vertexStates[i] = State.none;
    }

}
