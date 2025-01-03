package presents.Matrices;

import presents.Implements.GraphPresent;

import java.util.Arrays;


/**
 * Структура данных для представления графа.
 * Представляется граф в виде структуры с оглавлением.
 */

public class GraphIndexedStructure  implements GraphPresent {

    int[] verticesIndex;
    int[] adjacentVertices;

    @Override
    public void buildFromAdjacencyMatrix(int[][] aMatrix) {
        int size = aMatrix.length;
        verticesIndex = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (aMatrix[j][i] == 1) {
                    addVertex(j + 1);
                }
            }
            if (i < verticesIndex.length - 1) verticesIndex[i + 1] = adjacentVertices.length;
        }
    }

    private void addVertex(int number) {
        if (adjacentVertices == null) {
            adjacentVertices = new int[]{number};
            return;
        }

        int lim = adjacentVertices.length;
        int[] tmp = new int[adjacentVertices.length + 1];

        for (int i = 0; i < adjacentVertices.length; i++) tmp[i] = adjacentVertices[i];

        tmp[adjacentVertices.length] = number;
        adjacentVertices = tmp;
    }


    @Override
    public void printSelf() {
        System.out.print("Index: ");
        Arrays.stream(verticesIndex).forEach(v -> System.out.printf("%d ", v));
        System.out.print("\nStructure: ");
        Arrays.stream(adjacentVertices).forEach(v -> System.out.printf("%d ", v));
        System.out.println("\n");

        int j = 0, i =0;
        for (; i < verticesIndex.length - 1; i++) {

            System.out.printf("%d: ", i + 1);
            for (j = verticesIndex[i]; j < verticesIndex[i+1]; j++)
                System.out.printf("%d, ", adjacentVertices[j]);
            System.out.println();
        }

        System.out.printf("%d: ", i + 1);
        for (; j < adjacentVertices.length; j++) System.out.printf("%d, ", adjacentVertices[j]);

        System.out.println();
    }


}
