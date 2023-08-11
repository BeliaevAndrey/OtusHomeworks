public class Powering {
    public static void main(String[] args) {
        Powering pwr = new Powering();

        System.out.printf("%16s %16s %16s %16s %16s %16s %16s\n", "Power", "Value 1", "time 1", "Value 2", "time 2", "Value 3", "time 3");
        for (long power = 1000; power <= 1e11; power *= 10) {
            double number = 1 + 1 / (double) power;

            double start0 = System.nanoTime();
            double res0 = pwr.getDegreeB(number, power);
            double end0 = (System.nanoTime() - start0) / 1e9;

            double start1 = System.nanoTime();
            double res1 = pwr.getDegreeBinIter(number, power);
            double end1 = (System.nanoTime() - start1) / 1e9;

            if (Long.MAX_VALUE - power > 0) {
                double start2 = System.nanoTime();
                double res2 = pwr.getDegreeIter(number, power);
                double end2 = (System.nanoTime() - start2) / 1e9;
                System.out.printf("%16d %16.11f %16e %16.11f %16e %16.11f %16e\n",
                        power, res0, end0, res1, end1, res2, end2);

            } else {
                System.out.printf("%16d %12f %12f %12f %12f %12s %12s\n",
                        power, res0, end0, res1, end1, " -- ", " -- ");
            }
        }

    }

    double getDegreeIter(double number, long power) {
        if (power == 0) return 1D;
        double res = 1;
        for (long i = 0; i < power; i++) {
            res *= number;
        }
        return res;
    }

    double getDegreeB(double number, long power) {
        if (power == 0) return 1.0;
        if (power == 1) return number;
        if (power % 2 == 0) {
            return getDegreeB(number * number, power / 2);
        } else {
            return number * getDegreeB(number, power - 1);
        }
    }

    double getDegreeBinIter(double number, long power) {
        double result = 1;
        while (power > 0) {
            if ((power & 1) == 1) result *= number;     // bitwise 'and'; same to: power % 2
            power >>= 1;                                // bitwise 'shift'; same to: (long) power / (2 ^ 1)
            number *= number;
        }
        return result;
    }
}