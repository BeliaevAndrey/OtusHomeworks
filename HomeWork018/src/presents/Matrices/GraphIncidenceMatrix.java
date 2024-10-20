package presents.Matrices;


/**
 * Структура данных для представления графа.
 * Представляется граф в виде матрицы инцидентности.
 */
public class GraphIncidenceMatrix {

    private int[][] incMatrix;

    private int vertAmt = 0;
    private int edgeAmt = 0;

    public GraphIncidenceMatrix() {}


    public void buildFromAdjacentMatrix(int[][] aMatrix) {
        vertAmt = aMatrix.length;

        for (int i = 0; i < vertAmt; i++)
            for (int j = i; j < vertAmt; j++)
                if (aMatrix[j][i] == 1) edgeAmt++;

        incMatrix = new int[vertAmt][edgeAmt];

        int edgeNum = 0;
        for (int i = 0; i < vertAmt; i++) {
            for (int j = i; j < vertAmt; j++) {
                if (aMatrix[j][i] == 1) {
                    incMatrix[i][edgeNum] = 1;
                    incMatrix[j][edgeNum] = 1;
                    edgeNum++;
                }
            }

        }
    }

    public void printIncidenceMatrix() {
        System.out.print("   |");
        for (int i = 0; i < edgeAmt; i++) {
            System.out.printf("%5c", 65 + i);
        }
        System.out.println("\n" + "-".repeat(edgeAmt * 6));
        for (int i = 0; i < vertAmt; i++) {
            System.out.printf("%-3d|", i + 1);

            for (int j = 0; j < edgeAmt; j++) {
                System.out.printf("%5d", incMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public int[][] getIncMatrix() {return incMatrix;}

    public int getVertAmt() {return vertAmt;}

    public int getEdgeAmt() {return edgeAmt;}
}
