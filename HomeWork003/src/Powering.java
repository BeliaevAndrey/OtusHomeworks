public class Powering {
    public static void main(String[] args) {
        Powering pwr = new Powering();

        System.out.printf("%16s %18s %18s %18s %18s %18s %18s %18s %18s\n",
                "Power", "Iterative", "time iter", "Binary recursive", "time bin. rec.",
                "Binary iter.", "time bin. iter.", "Binary iter. mlt.", "time bin. it. ml.");
        for (long power = 1000; power <= 1e11; power *= 10) {
            double number = 1 + 1 / (double) power;

            double start1 = System.nanoTime();
            double res1 = pwr.getDegreeB(number, power);
            double end1 = (System.nanoTime() - start1) / 1e9;

            double start2 = System.nanoTime();
            double res2 = pwr.getDegreeBinIter(number, power);
            double end2 = (System.nanoTime() - start2) / 1e9;

            double start3 = System.nanoTime();
            double res3 = pwr.getDegreeBinIter(number, power);
            double end3 = (System.nanoTime() - start3) / 1e9;


            double start0 = System.nanoTime();
            double res0 = pwr.getDegreeIter(number, power);
            double end0 = (System.nanoTime() - start0) / 1e9;

            System.out.printf("%16d %18.11f %18e %18.11f %18e %18.11f %18e %18.11f %18e\n",
                    power, res0, end0, res1, end1, res2, end2, res3, end3);


        }

    }

    /*Реализовать итеративный O(N) алгоритм возведения числа в степень.*/
    double getDegreeIter(double number, long power) {
        if (power == 0) return 1D;
        double res = 1;
        for (long i = 0; i < power; i++) {
            res *= number;
        }
        return res;
    }


    /*Реализовать алгоритм возведения в степень через двоичное
    разложение показателя степени O(2LogN) = O(LogN).*/
    double getDegreeB(double number, long power) {
        if (power == 0) return 1.0;
        if (power == 1) return number;
        if ((power & 1) == 0) {
            return getDegreeB(number * number, power / 2);
        } else {
            return number * getDegreeB(number, power - 1);
        }
    }

    /*Реализовать алгоритм возведения в степень
    через домножение O(N/2+LogN) = O(N)*/
    double powerIterMult(double a, long p) {
        if (p == 0) return 1.0;
        if (p == 1) return a;
        double result = 1;
        while (p > 0) {
            if (p % 2 == 1) {
                result *= a;
            }
            a *= a;
            p /= 2;
        }
        return result;
    }

    double getDegreeBinIter(double number, long power) {
        double result = 1;
        while (power > 0) {
            if ((power & 1) == 1) result *= number;     // bitwise 'and'; same to: power % 2
            power >>= 1;                                // bitwise 'shift'; same to: power / (2 ^ 1)
            number *= number;
        }
        return result;
    }
}