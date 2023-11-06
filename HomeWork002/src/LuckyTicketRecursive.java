import java.util.Arrays;

public class LuckyTicketRecursive {

    final int deltaWidth = 9;
    final int height = 10;

    public static void main(String[] args) {
        LuckyTicketRecursive luckyTicketV2 = new LuckyTicketRecursive();
        for (int i = 1; i < 11; i++) {
            System.out.printf("%3d: %-25.0f%n", i, luckyTicketV2.findAmountVer2(i));
        }
    }

    double findAmountVer2(int len_num) {
        if (len_num <= 0)
            return 0L;
        int[] b = new int[]{1};
        b = tabulatorVerRec(b, len_num);
        return Arrays.stream(b).mapToDouble(x -> Math.pow(x, 2)).sum();
    }

    int[] tabulatorVerRec(int[] sums, int len_num) {
        if (len_num == 0) return sums;
        int[] sumsNext = new int[sums.length + deltaWidth];
        for (int i = 0; i < height; i++)
            for (int j = i; j < i + sums.length; j++) {
                sumsNext[j] += sums[j - i];
            }
        return tabulatorVerRec(sumsNext, len_num - 1);
    }

}
