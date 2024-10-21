package presents.AdjacencyArray;


import presents.Implements.GraphPresent;

/**
 * Структура данных для представления графа.
 * Граф представлен в виде вектора смежности.
 */

public class GraphAdjacencyVector implements GraphPresent {

    private int[][] adjVector;

    private int vertAmt;
    private int maxDegree;

    @Override
    public void buildFromAdjacencyMatrix(int[][] aMatrix) {
        vertAmt = aMatrix.length;

        for (int i = 0; i < vertAmt; i++) {
            int width = 0;
            for (int j = 0; j < vertAmt; j++) width += (aMatrix[i][j] == 1 ? 1 : 0);
            if (width > maxDegree) maxDegree = width;
        }

        adjVector = new int[vertAmt][maxDegree];

        for (int i = 0; i < vertAmt; i++) {
            int k = 0;
            for (int j = 0; j < vertAmt; j++)
                if (aMatrix[j][i] == 1) adjVector[i][k++] = (j + 1);
        }

    }

    public int[][] getAdjVector() { return adjVector; }

    @Override
    public void printSelf() {
        for (int i = 0; i < vertAmt; i++) {
            System.out.printf("%-3d|", i + 1);
            for (int j = 0; j < maxDegree; j++) {
                System.out.printf("%3d", adjVector[i][j]);
            }
            System.out.println();
        }
    }


}
