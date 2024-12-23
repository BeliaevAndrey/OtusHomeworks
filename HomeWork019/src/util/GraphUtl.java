package util;

public class GraphUtl {

    public int[][] vectorAjcToMatrixAjc(int[][] vector) {
        int size = vector.length;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < vector[i].length; j++) {
                if (vector[i][j] != 0) {
                    matrix[i][vector[i][j] - 1]= 1;
                }
            }
        }
        return matrix;
    }


    public void printMatrix(int[][] aMatrix) {
        System.out.print("   |\t");
        for (int i = 0; i < aMatrix.length; i++) {
            System.out.printf("%d\t", i + 1);
        }
        System.out.println("\n" + "-".repeat(aMatrix.length * 5));
        for (int i = 0; i < aMatrix.length; i++) {
            System.out.printf("%2d |\t", i + 1);
            for (int j = 0; j < aMatrix[i].length; j++) {
                System.out.printf("%d\t", aMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public boolean compareMatrices(int[][] m1, int[][] m2) {
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) return false;
            }
        }
        return true;
    }

}
