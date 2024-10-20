package presents.Matrices;

/**
 * Структура данных для представления графа.
 * Представляется граф в виде матрицы смежности.
 */
public class GraphAdjacencyMatrix {

    private int[][] aMatrix;

    private int size;

    public void setMatrix(int[][] aMatrix, int size) {
        this.aMatrix = aMatrix;
        this.size = size;
    }

    public void buildFromAdjacencyVector(int[][] vector) {
        this.size = vector.length;
        aMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < vector[i].length; j++) {
                if (vector[i][j] != 0) {
                    aMatrix[i][vector[i][j] - 1]= 1;
                }
            }
        }
    }

    public int[][] getaMatrix() { return aMatrix; }

    public int getSize() { return size; }

    public void printAMatrix() {
        System.out.print("   |");
        for (int i = 0; i < size; i++) {
            System.out.printf("%3d", i + 1);
        }
        System.out.println("\n" + "-".repeat(size * 4));

        for (int i = 0; i < size; i++) {
            System.out.printf("%-3d|", i + 1);

            for (int j = 0; j < aMatrix[i].length; j++) {
                System.out.printf("%3d", aMatrix[i][j]);
            }
            System.out.println();
        }
    }


}
