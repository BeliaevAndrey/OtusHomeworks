package stageA;

import util.ReadFile;
import util.WriteResults;

import java.util.ArrayList;


public class Spruce {
    static final String taskName = "spruce";

    ArrayList<String> testResults;

    public static void main(String[] args) {
        Spruce spr = new Spruce();
        spr.testResults = new ArrayList<>();

        ReadFile rf = new ReadFile(taskName);
        spr.runTests(rf);

        WriteResults wr = new WriteResults(taskName);
        wr.writeFile(spr.testResults);
    }


    private void runTests(ReadFile rf) {
        int testsAmt = rf.getTestAmt();
        for (int i = 0; i < testsAmt; i++) {
            String[] test = rf.getTest(i, true);
            int answer = -1;

            try {
                answer = Integer.parseInt(test[1]);
            } catch (NumberFormatException e) { e.printStackTrace(); }

            int result = decorateSpruce(test[0]);

            String lineOut = String.format(
                    "test-%d. Answer: %s; Result: %s; Correct: %s\n",
                    i, answer, result, result == answer);
            testResults.add(lineOut);
            System.out.print(lineOut);
        }
    }

    int maxPair(int a, int b, int c) { return (a + c) > (b + c) ? a + c : b + c; }

    private int decorateSpruce(String spruceData) {
        int[][] spruce = parseSpruce(spruceData);

        int height = spruce.length;
        int[][] sums = new int[height][];

        sums[height - 1] = new int[spruce[height - 1].length];
        for (int i = 0; i < spruce[height - 1].length; i++)
            sums[height - 1][i] = spruce[height - 1][i];

        for (int i = height - 1; i > 0; i--) {
            sums[i - 1] = new int[i];
            for (int j = 0; j < spruce[i].length - 1; j++) {
                sums[i-1][j] = maxPair(sums[i][j], sums[i][j + 1],  spruce[i-1][j]);
            }
        }
        return sums[0][0];
    }


    private int[][] parseSpruce(String spruce) {
        String[] rows = spruce.split("\\n");
        int rowsAmt = rows.length;
        int[][] nums = new int[rowsAmt][];
        for (int i = 0; i < rowsAmt; i++) {

            nums[i] = new int[i + 1];
            int j = 0;

            for (String num : rows[i].strip().split(" "))
                try { nums[i][j++] = Integer.parseInt(num);
                } catch (NumberFormatException e) {e.printStackTrace();}
        }
        return nums;
    }

}
