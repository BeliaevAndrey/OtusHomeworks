import java.util.Arrays;

public class LuckyTicketV2 {

    final int deltaWidth = 9;
    final int height = 10;

    public static void main(String[] args) {
        LuckyTicketV2 luckyTicketV2 = new LuckyTicketV2();
        for (int i = 1; i < 11; i++) {
            System.out.printf("%3d: %-25.0f%n", i, luckyTicketV2.findAmountVer2(i));
        }
    }

    double findAmountVer2(int lenNum) {
        if (lenNum <= 0)
            return 0L;
        int[] b = new int[]{1};
        for (int n = 0; n < lenNum; n++) {
            b = tabulatorVer2(b);
        }
        return Arrays.stream(b).mapToDouble(x -> Math.pow(x, 2)).sum();
    }

    int[] tabulatorVer2(int[] sums) {
        int[] sumsNext = new int[sums.length + deltaWidth];
        for (int i = 0; i < height; i++)
            for (int j = i; j < i + sums.length; j++) {
                sumsNext[j] += sums[j - i];
            }
        return sumsNext;
    }

}
