package RLE;

import java.util.Arrays;

public class TestArrayRLE {

    public static void main(String[] args) {
        TestArrayRLE tar = new TestArrayRLE();

        tar.runTests();

    }

    void runTests() {

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

    }


    private void testStrings(ArrayRLE arl, String line) {

        char[] sample1 = line.toCharArray();
        int lenSrc = sample1.length;

        System.out.println("Encoding:");
        System.out.printf("Line: %s\n", line);
        System.out.printf("Char array source:\n%s\n", Arrays.toString(sample1));
        System.out.printf("Char array length: %d\n", lenSrc);

        byte[] encoded1 = arl.encode(sample1);
        int lenRst = encoded1.length;

        System.out.printf("Byte array encoded:\n%s\n", Arrays.toString(encoded1));
        System.out.printf("Byte array length: %d\n", lenRst);
        System.out.printf("\nCompress ratio: %.3f %%", ((double) lenRst / lenSrc * 100));

        System.out.println("\nDecoding:");
        StringBuilder sb = new StringBuilder();
        for (char c : arl.decode(encoded1)) sb.append(c);
        System.out.printf("Result: %s\n", sb);
        System.out.printf("Correct: %s\n", sb.toString().equals(line));
        System.out.println("=".repeat(80));

    }

}
