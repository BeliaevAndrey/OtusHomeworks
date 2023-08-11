public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        int position = 55;

        double start1 = System.nanoTime();
        double res1 = fib.iterFibonacci(position);
        double end1 = (System.nanoTime() - start1) / 1e9;

        double start2 = System.nanoTime();
        double res2 = fib.matrixFibonacci(position);
        double end2 = (System.nanoTime() - start2) / 1e9;

        double start4 = System.nanoTime();
        double res4 = fib.goldenSectionFib(position);
        double end4 = (System.nanoTime() - start4) / 1e9;


        System.out.printf("%20s: %.0f\n", "Iterations", res1);
        System.out.printf("%20s: %.0f\n", "Matrices", res2);
        System.out.printf("%20s: %.0f\n", "Golden Section", res4);

        System.out.printf("Iterations: %.3e Matrices: %.3e Golden Section: %.3e\n", end1, end2, end4);

        double start3 = System.nanoTime();
        double res3 = fib.recFibonacci(position);
        double end3 = (System.nanoTime() - start3) / 1e9;

        System.out.printf("%20s: %.0f\n", "Recursive", res3);
        System.out.printf("Iterations: %.3e Matrices: %.3e Golden Section: %.3e\nRecursion: %.3e \n",
                end1, end2, end4, end3);

    }

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


    double goldenSectionFib(long position) {
        double gldConst = (1 + Math.sqrt(5)) / 2;
        double fiNFwd = Math.pow(gldConst, position);
        double fiNBck = 1 / Math.pow((-gldConst), position);
        return (fiNFwd - fiNBck) / (2 * gldConst - 1);
    }

    double recFibonacci(int position) {
        if (position <= 2) return 1;
        return recFibonacci(position - 1) + recFibonacci(position - 2);
    }

    double matrixFibonacci(int position) {
        if (position == 0) return 0D;
        if (position == 1) return 1D;

        double[][] trnMatrix = new double[][]{{1, 1}, {1, 0}};
        double[][] fibMatrix = new double[][]{{1, 1}, {1, 0}};

        while (position >= 3) {
            position -= 1;
            double[][] tmp = new double[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int common = 0; common < 2; common++) {
                        tmp[i][j] += fibMatrix[i][common] * trnMatrix[common][j];
                    }
                }
            }
            fibMatrix = tmp;
        }
        return fibMatrix[0][0];
    }
}