package RLE;

import java.util.Arrays;

public class TestWikiRLE {

    public static void main(String[] args) {
        TestWikiRLE tar = new TestWikiRLE();

        tar.runTests();

    }

    void runTests() {

        String longLine = "WWWWWWWWWBBB" +
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
                "additional string to test edge case" +
                "BWWWWWWWWWWWWWW"
                ;

        String wikiLine2 = "ABCABCABCDDDFFFFFF";

        testStrings(new WikiRLE2(), longLine);
        testStrings(new WikiRLE2(), wikiLine2);

    }


    void testStrings(WikiRLE2 wrl, String line) {

        int lenSrc = line.length();

        System.out.println("Encoding:");
        System.out.printf("Line: %s\n", line);
        System.out.printf("String length: %d\n", lenSrc);

        String encoded1 = wrl.encode(line);
        int lenRst = encoded1.length();

        System.out.printf("Char array encoded:\n%s\n", encoded1);
        System.out.printf("Char array length: %d\n", lenRst);
        System.out.printf("\nCompress ratio: %.3f %%", ((double) lenRst / lenSrc * 100));

        System.out.println("\nDecoding:");
        String result = wrl.decode(encoded1);
        System.out.printf("Result: %s\n", result);
        System.out.printf("Correct: %s\n", result.equals(line));
        System.out.println("=".repeat(80));

    }

}
