package stageA;

import util.ReadFile;

public class Peas {

    static final String taskName = "peas";

    public static void main(String[] args) {
        Peas p = new Peas();
        ReadFile rf = new ReadFile(taskName);
        p.runTests(rf);
    }

    private void runTests(ReadFile rf) {
        int testsAmt = rf.getTestAmt();

        for (int i = 0; i < testsAmt; i++) {
            String[] testData = rf.getTest(i);
            String result = countPeas(testData[0]);
            System.out.printf("test-%d. Question: %s; Answer: %s; Result: %s; Correct: %s\n",
                    i, testData[0], testData[1], result, result.equals(testData[1]));
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
