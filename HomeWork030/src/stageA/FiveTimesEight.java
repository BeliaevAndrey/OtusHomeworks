package stageA;

import util.ReadFile;
import util.WriteResults;

import java.util.ArrayList;

public class FiveTimesEight {

    static final String taskName = "five_times_eight";

    ArrayList<String> testResults;

    private final int limN = 88;
    private final int ROWS = 4;
    private final long[][] cache = new long[ROWS][limN];  // table for amounts combinations of '5' and '8'


    public static void main(String[] args) {

        FiveTimesEight fte = new FiveTimesEight();
        fte.testResults = new ArrayList<>();

        ReadFile rf = new ReadFile(taskName);
        fte.runTests(rf);

        WriteResults wr = new WriteResults(taskName);
        wr.writeFile(fte.testResults);

    }

    private void runTests(ReadFile rf) {
        fillCache();
        int testsAmt = rf.getTestAmt();
        for (int i = 0; i < testsAmt; i++) {
            String[] testData = rf.getTest(i, false);
            long answer = parseTest(testData[1]);
            long result = countAmt(testData[0]);

            String lineOut = String.format("test-%d. Question: %s; Answer: %s; Result: %s; Correct: %s\n",
                    i, testData[0], testData[1], result, result == answer);
            System.out.print(lineOut);
            testResults.add(lineOut);
        }
    }

    long countAmt(String testData) {
        int N = (int) parseTest(testData);
        if (N == -1) return -1;
        return countSums(N);
    }

    long countSums(int N) {
        long sum = 0;
        for (int i = 0; i < 4; i++) sum += cache[i][N - 1];
        return sum;
    }

    private void fillCache() {

        for (int i = 0; i < ROWS; i++) cache[i][0] = (i + 1) % 2; // initial (first column; case 1 digit)

        for (int i = 1; i < limN; i++) {
            cache[0][i] = cache[3][i - 1] + cache[2][i - 1];       // x8 + x88
            cache[1][i] = cache[0][i - 1];                         // x5
            cache[2][i] = cache[0][i - 1] + cache[1][i - 1];       // x5 + x55
            cache[3][i] = cache[2][i - 1];                         // x8
        }

    }

    long parseTest(String testData) {
        try {
            return Long.parseLong(testData);
        } catch (NumberFormatException e) { e.printStackTrace(); }

        return -1;
    }
}