package RLE;

import util.WriteResults;

import java.util.ArrayList;
import java.util.Arrays;

public class TestArrayRLE {

    ArrayList<String> testResults;

    public static void main(String[] args) {
        TestArrayRLE tar = new TestArrayRLE();
        tar.runTests();

    }

    void runTests() {
        testResults = new ArrayList<>();

        String longLine = "WWWWWWWWWBBB" +
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
                "BWWWWWWWWWWWWWW" +
                "1 2 3 5 6 7 8 9 0" +
                "additional string to test edge case" +
                "12345678";

        String wikiLine2 = "ABCABCABCDDDFFFFFF";

        System.out.println("String tests");
        testStrings(new ArrayRLE(), longLine);
        testStrings(new ArrayRLE(), wikiLine2);

        WriteResults wr = new WriteResults("Test_RLE");
        wr.writeFile(testResults);
    }


    private void testStrings(ArrayRLE arl, String line) {

        char[] sample1 = line.toCharArray();
        int lenSrc = sample1.length;

        StringBuilder report = new StringBuilder();

        report.append("Encoding:\n");
        report.append(String.format("Line: %s\n", line));
        report.append(String.format("Char array source:\n%s\n", Arrays.toString(sample1)));
        report.append(String.format("Char array length: %d\n", lenSrc));

        byte[] encoded1 = arl.encode(sample1);
        int lenRst = encoded1.length;

        report.append(String.format("Byte array encoded:\n%s\n", Arrays.toString(encoded1)));
        report.append(String.format("Byte array length: %d\n", lenRst));
        report.append(String.format("Compress ratio: %.3f %%\n", ((double) lenRst / lenSrc * 100)));

        testResults.add(report.toString());
        System.out.println(report);
        report = new StringBuilder();

        report.append(String.format("Decoding:\n"));
        StringBuilder sb = new StringBuilder();
        for (char c : arl.decode(encoded1)) sb.append(c);
        report.append(String.format("Result: %s\n", sb));
        report.append(String.format("Correct: %s\n", sb.toString().equals(line)));
        report.append(String.format("=".repeat(80))).append("\n\n");
        testResults.add(report.toString());
        System.out.println(report);

    }

}
