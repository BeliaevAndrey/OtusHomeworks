package presents.Lists;

import presents.Implements.GraphPresent;
import structures.CustomLinkedList;

import java.util.Arrays;

public class GraphAdjacencyList  implements GraphPresent {

    private CustomLinkedList<Vertex>[] adjList;

    @Override
    public void buildFromAdjacencyMatrix(int[][] aMatrix) {
        int size = aMatrix.length;
        adjList = new CustomLinkedList[size];
        for (int i = 0; i < size; i++) {
            adjList[i] = new CustomLinkedList<>();
            adjList[i].add(new Vertex(i + 1));
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (aMatrix[j][i] == 1) adjList[i].add(new Vertex(j + 1));
            }
        }
    }

    public CustomLinkedList<Vertex>[] getAdjList() {return adjList;}

    public int getSize() {return adjList == null ? 0 : adjList.length;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CustomLinkedList<Vertex> listVert : adjList) {
            sb.append("{");
            sb.append(String.format(
                            "%s", Arrays.toString(listVert.toArray())
                                    .replace('[', ' ')
                                    .replace(']', ' ')
                                    .replaceAll(", ", " -> ")
                    )).append("}\n");
        }
        return sb.toString();
    }

    public void printSelf() { System.out.println(this); }
}
