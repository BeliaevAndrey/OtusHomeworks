import java.util.Arrays;

public class IsPrime {
    public static void main(String[] args) {
        IsPrime prm = new IsPrime();
        long number = 1_000L;
//        long number = 1_000_000_000L;

        long start2 = System.nanoTime();
        int resE = prm.eratosthenes((int) number);
        double end2 = (System.nanoTime() - start2) / 1e9;
        System.out.printf("eratosthenes:\t%d  %d\n", number, resE);
        System.out.println(end2);

        long start3 = System.nanoTime();
        int resE2 = prm.eratosthenesON((int) number);
        double end3 = (System.nanoTime() - start3) / 1e9;
        System.out.printf("eratosthenesON:\t%d  %d\n", number, resE2);
        System.out.println(end3);

        long start = System.nanoTime();
        int res = prm.countPrimes(number);
        double end = (System.nanoTime() - start) / 1e9;
        System.out.printf("countPrimes:\t%d  %d\n", number, res);
        System.out.println(end);
    }


    int eratosthenesON(long N) {                // TODO: under construction
        int[] lp = new int[(int) N + 1];
        int[] pr = new int[(int) N + 1];
        for (int i = 2; i <= N; i++) {
            if (lp[i] == 0) {
                lp[i] = i;
                pr[i] = i;
            }
            for (int p = 0; p <= lp[i]; p++) {
                lp[p * i] = p;
            }
        }
        int count = 0;
        for (int i = 0; i <= N; i++)
            if (pr[i] != 0) count++;

        return count;
    }

    int countPrimes(long N) {
        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrimeOSqtrN(i))
                count++;
        }
        return count;
    }

    /*a*/
    boolean isPrimeOSqtrN(long num) {
        if (num == 2) return true;
        if (num < 2 || num % 2 == 0) return false;
        int lim = (int) Math.sqrt(num) + 1;
        for (int div = 3; div < lim; div += 2)
            if (num % div == 0) return false;
        return true;
    }

    /*b*/
    boolean isPrimeOSqtrN2(long num) {
        if (num == 2) return true;
        if (num < 2 || num % 2 == 0) return false;
        for (int div = 3; div <= Math.sqrt(num) + 1; div += 2)
            if (num % div == 0) return false;
        return true;
    }

    /*c*/
    boolean isPrimeOHalfN(long num) {
        if (num == 2) return true;
        if (num < 2 || num % 2 == 0) return false;
        long lim = num / 2 + 1;
        for (int div = 3; div <= lim; div += 2)
            if (num % div == 0) return false;
        return true;
    }


    /*d*/
    boolean isPrimeOHalfNFull(long num) {
        if (num < 2 || num % 2 == 0) return false;
        for (int div = 2; div <= num / 2 + 1; div++)
            if (num % div == 0) return false;
        return true;
    }

    /*e*/
    boolean isPrimeON(long num) {
        if (num == 2) return true;
        if (num < 2 || num % 2 == 0) return false;
        for (int div = 3; div <= num; div += 2)
            if (num % div == 0) return false;
        return true;
    }

    /*f*/
    boolean isPrimeONFull(long num) {
        if (num == 2) return true;
        for (int div = 2; div <= num; div++)
            if (num % div == 0) return false;
        return true;
    }

    int eratosthenes(long N) {
        boolean[] divs = new boolean[(int) N + 1];
        int count = 0;
        int sqrt = (int) Math.sqrt(N);
        for (int p = 2; p <= N; p++) {
            if (!divs[p]) {
                count++;
                if (p <= sqrt)
                    for (int i = p * p; i <= N; i += p) {
                        divs[i] = true;
                    }
            }
        }
        return count;
    }

}
//1: для i := 2, 3, 4, ..., до n:
//        2:  если lp[i] = 0:
//        3:       lp[i] := i
//        4:       pr[] += {i}
//        5:   для p из pr пока p ≤ lp[i] и p*i ≤ n:
//        6:       lp[p*i] := p
//Результат:
//lp - минимальный простой делитель для кажого числа до n
//pr - список всех простых до n.
