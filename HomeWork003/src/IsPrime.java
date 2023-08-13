import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class IsPrime {
    public static void main(String[] args) {
        IsPrime prm = new IsPrime();
//        long number = 1_0007L;
        long number = 123_456_789L;
//        long number = 1_000_000L;
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

        long start1 = System.nanoTime();
        int res1 = prm.countPrimesOSqtrN(number);
        double end1 = (System.nanoTime() - start1) / 1e9;
        System.out.printf("countPrimes Sqrt:\t%d  %d\n", number, res1);
        System.out.println(end1);

        long start = System.nanoTime();
        int res = prm.countPrimes(number);
        double end = (System.nanoTime() - start) / 1e9;
        System.out.printf("countPrimes:\t%d  %d\n", number, res);
        System.out.println(end);
    }


    /*Реализовать алгоритм поиска количества простых чисел через
    перебор делителей, O(N^2).*/
    int countPrimes(long num) {
        int count = 1;
        for (int i = 3; i <= num; i++) {
            count++;
            for (int div = 2; div < i; div++)
                if (i % div == 0) {
                    count--;
                    break;
                }
        }
        return count;
    }

    /*Реализовать алгоритм поиска простых чисел с оптимизациями поиска и
      делением только на простые числа, O(N * Sqrt(N) / Ln (N)).*/
    int countPrimesOSqtrN(long num) {
        int count = 1;
        for (int i = 3; i <= num; i += 2) {
            count++;
            int lim = (int) Math.sqrt(i) + 1;
            for (int div = 3; div < lim; div += 2) {
                if (i % div == 0) {
                    count--;
                    break;
                }
            }
        }
        return count;
    }


    /*Реализовать алгоритм "Решето Эратосфена" для быстрого поиска
    простых чисел O(N Log Log N).*/
    int eratosthenes(long num) {
        boolean[] divs = new boolean[(int) num + 1];
        int count = 0;
        int sqrt = (int) Math.sqrt(num);
        for (int p = 2; p <= num; p++) {
            if (!divs[p]) {
                count++;
                if (p <= sqrt)
                    for (int i = p * p; i <= num; i += p) {
                        divs[i] = true;
                    }
            }
        }
        return count;
    }

    /*Решето Эратосфена со сложностью O(n), см. дополнительный материал.
      sources: seminar,  https://habr.com/ru/articles/452388/
     * */
    int eratosthenesON(long num) {
        int count = 0;
        int[] lp = new int[(int) num + 1];
//        int[] pr = new int[(int) num + 1];
        for (int i = 2; i <= num; i++) {
            if (lp[i] == 0) {
                lp[i] = i;          // prime dividers
//                pr[i] = i;          // primes
                count++;
            }
            for (int p = 2; p <= lp[i] && p * i < (int) num + 1; p++) {
                lp[p * i] = p;      // placing to a composite-numbered cell prime divider
            }
        }
        return count;
    }

}
