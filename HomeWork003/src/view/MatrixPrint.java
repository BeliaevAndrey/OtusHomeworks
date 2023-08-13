package view;

import java.util.Arrays;

public class MatrixPrint {

    public static void printMtr(double[][] arr) {
        for (double[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("=".repeat(20));
    }

}
