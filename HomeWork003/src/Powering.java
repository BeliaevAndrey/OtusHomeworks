public class Powering {
    public static void main(String[] args) {
        Powering pwr = new Powering();

    }

    double getDegreeIter(double number, double power) {
        if (power == 0) return 1D;
        double res = 1;
        for (int i = 0; i < power; i++) {
            res *= number;
        }
        return res;
    }

    double getDegreeB(double number, double power) {
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
            if ((power & 1) == 1) result *= number;     // bitwise and; same to: power % 2
            power >>= 1;                                // bitwise shift; same to: (long) power / (2 ^ 1)
            number *= number;
        }
        return result;
    }
}