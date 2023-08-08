import java.util.Arrays;

public class LuckyTicket {

    final int deltaWidth = 9;
    final int height = 10;

    public static void main(String[] args) {
        LuckyTicket luckyTicket = new LuckyTicket();
        for (int i = 1; i < 11; i++) {
            System.out.printf("%3d: %-25.0f\n", i, luckyTicket.findAmount(i));
        }
    }

    double findAmount(int len_num) {
        if (len_num < 1)
            return 0L;
        int[] b = new int[]{1};
        for (int n = 0; n < len_num; n++) {
            int[][] a = tabulator(b);
            b = summarizer(a);
        }

        return Arrays.stream(b).mapToDouble(x -> Math.pow(x, 2)).sum();
    }

    int[] summarizer(int[][] sums) {
        int[] tmp = new int[sums[0].length];
        for (int i = 0; i < sums.length; i++) {
            for (int j = 0; j < sums[0].length; j++) {
                tmp[j] += sums[i][j];
            }
        }
        return tmp;
    }

    int[][] tabulator(int[] sums) {
        int[][] tmp = new int[height][sums.length + deltaWidth];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < sums.length; j++){
                tmp[i][i + j] = sums[j];
            }

        return tmp;
    }

    void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            Arrays.stream(arr[i]).forEach(x -> System.out.print(x + " "));
            System.out.println();
        }
        System.out.println("=".repeat(40));
    }
}
