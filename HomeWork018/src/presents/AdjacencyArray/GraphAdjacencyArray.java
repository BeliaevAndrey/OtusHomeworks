package presents.AdjacencyArray;

import presents.Implements.GraphPresent;

/**
 * Структура данных для представления графа.
 * Граф представлен в виде массива смежности (зубчатый массив).
 */

public class GraphAdjacencyArray implements GraphPresent {

    int[][] adjArray;

    int vertAmt;

    @Override
    public void buildFromAdjacencyMatrix(int[][] aMatrix) {
        vertAmt = aMatrix.length;
        adjArray = new int[vertAmt][];
        for (int i = 0; i < vertAmt; i++) {
            int width = 0, k = 0;
            for (int j = 0; j < vertAmt; j++) width += (aMatrix[i][j] == 1 ? 1 : 0);
            adjArray[i] = new int[width];
            for (int j = 0; j < vertAmt; j++)
                if (aMatrix[j][i] == 1) adjArray[i][k++] = (j + 1);
        }

    }

    public int[][] getAdjArray() { return adjArray; }

    @Override
    public void printSelf() {
        for (int i = 0; i < vertAmt; i++) {
            System.out.printf("%-3d|", i + 1);
            for (int j = 0; j < adjArray[i].length; j++) {
                System.out.printf("%3d", adjArray[i][j]);
            }
            System.out.println();
        }
    }


}
