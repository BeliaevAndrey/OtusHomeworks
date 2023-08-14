import view.MatrixPrint;

import java.util.Arrays;

public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        String outTemplate = "%-40s %.0f\n";
        int position = 10000000;

        double start1 = System.nanoTime();
        double res1 = fib.iterFibonacci(position);
        double end1 = (System.nanoTime() - start1) / 1e9;

        double start4 = System.nanoTime();
        double res4 = fib.goldenSectionFib(position);
        double end4 = (System.nanoTime() - start4) / 1e9;

        double start5 = System.nanoTime();
        double res5 = fib.matMulFib(position);
        double end5 = (System.nanoTime() - start5) / 1e9;

        double start6 = System.nanoTime();
        double res6 = fib.matMulFibBin(position);
        double end6 = (System.nanoTime() - start6) / 1e9;


        System.out.printf(outTemplate, "Iterations:", res1);
        System.out.printf(outTemplate, "Golden Section:", res4);
        System.out.printf(outTemplate, "Matrix powering:", res5);
        System.out.printf(outTemplate, "Matrix multiply binary decomposition:", res6);
        System.out.println("=".repeat(60));
        System.out.printf("Iterations: %.3e\nGolden Section: %.3e\n", end1, end4);
        System.out.printf("Matrix powering: %.3e\nMatrix multiply binary decomposition: %.3e\n", end5, end6);

    }

    /* Реализовать рекурсивный O(2^N) и итеративный O(N) алгоритмы поиска чисел Фибоначчи.*/
    double iterFibonacci(int position) {
        double fib1 = 0;
        double fib2 = 1;
        for (int i = 0; i <= position; i++) {
            double tmp = fib1 + fib2;
            fib2 = fib1;
            fib1 = tmp;
        }
        return fib2;
    }

    double recFibonacci(int position) {
        if (position <= 2) return 1;
        return recFibonacci(position - 1) + recFibonacci(position - 2);
    }

    /*Реализовать алгоритм поиска чисел Фибоначчи по формуле золотого сечения.*/
    double goldenSectionFib(long position) {
        double gldConst = (1 + Math.sqrt(5)) / 2;
        double fiNFwd = Math.pow(gldConst, position);
        double fiNBck = 1 / Math.pow((-gldConst), position);
        return (fiNFwd - fiNBck) / (2 * gldConst - 1);
    }

    double matMulFib(int position) {
        double[][] trnMtr = new double[][]{{1, 1}, {1, 0}};
        double[][] fibMtr = new double[][]{{1, 0}, {0, 1}};
        while (position > 0) {
            fibMtr = MatrixMultiply.matrixMult(fibMtr, trnMtr);
            position -= 1;
        }
        return fibMtr[1][0];
    }

    /*реализовать алгоритм возведения матрицы в степень через двоичное разложение
      показателя степени, реализовать алгоритм поиска чисел Фибоначчи O(LogN)
      через умножение матриц, используя созданный класс.*/
    double matMulFibBin(int position) {
        double[][] trnMtr = new double[][]{{1, 1}, {1, 0}};
        double[][] fibMtr = new double[][]{{1, 0}, {0, 1}};
        while (position > 0) {
            if ((position & 1) == 1) fibMtr = MatrixMultiply.matrixMult(fibMtr, trnMtr);
            trnMtr = MatrixMultiply.matrixMult(trnMtr, trnMtr);
            position >>= 1;
        }
        return fibMtr[1][0];
    }

}

class MatrixMultiply {
    /*Написать класс умножения матриц,*/

    static double[][] matrixMult(double[][] left, double[][] right) {
        int newHeight = left.length;
        int newWidth = right[0].length;
        int common = right.length;
        double[][] result = new double[newHeight][newWidth];

        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                for (int k = 0; k < common; k++) {
                    result[i][j] += left[i][k] * right[k][j];
                }
            }
        }
        return result;
    }

}