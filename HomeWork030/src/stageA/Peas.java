package stageA;

import util.ReadFile;
import util.WriteResults;

import java.util.ArrayList;

public class Peas {

    static final String taskName = "peas";

    ArrayList<String> testResults;

    public static void main(String[] args) {
        Peas p = new Peas();
        p.testResults = new ArrayList<>();

        ReadFile rf = new ReadFile(taskName);
        p.runTests(rf);

        WriteResults wr = new WriteResults(taskName);
        wr.writeFile(p.testResults);
    }

    private void runTests(ReadFile rf) {
        int testsAmt = rf.getTestAmt();

        for (int i = 0; i < testsAmt; i++) {
            String[] testData = rf.getTest(i, false);
            String result = countPeas(testData[0]);

            String lineOut = String.format("test-%d. Question: %s; Answer: %s; Result: %s; Correct: %s\n",
                    i, testData[0], testData[1], result, result.equals(testData[1]));
            System.out.print(lineOut);
            testResults.add(lineOut);
        }

    }


    private String countPeas(String testData) {
        int[] nums = parseTest(testData);

        int upper = nums[0] * nums[3] + nums[2] * nums[1];
        int lower = nums[1] * nums[3];

        int common = gcd(upper, lower);

        int numerator = upper / common;
        int denominator = lower / common;

        return String.format("%s/%s", numerator, denominator);
    }


    private int[] parseTest(String test) {
        int[] nums = new int[4];
        int i = 0;
        for (String num : test.replaceAll("/", ",")
                .replaceAll("\\+", ",")
                .split(",")) {
            try { nums[i++] = Integer.parseInt(num);
            } catch (NumberFormatException e) { e.printStackTrace(); }
        }
        return nums;
    }


    private int gcd(int a, int b) {
        if (b == 0) return a;
        if (a > b) return gcd(b, a % b);
        return gcd(a, b % a);
    }


}
