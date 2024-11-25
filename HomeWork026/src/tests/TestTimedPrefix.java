package tests;

import hw.PrefixFunctions;
import util.TestResultWriter;

import java.util.ArrayList;
import java.util.Locale;

public class TestTimedPrefix {


    static {
        Locale.setDefault(Locale.US);
    }

    static ArrayList<String> results;

    static String pattern = "REAL";

    static String shortText = "SOMEPHRASEWITHREALWORD";
    static String longText = "SOMEVERYVERYVERYVERYVERYLONGVERYVERYVERYVERYLONGveryveryveryverylongPHRASEWITHREALWORD";

    public static void main(String[] args) {

        TestTimedPrefix tpf = new TestTimedPrefix();

        tpf.testPrefixFunctions();

    }

    public void testPrefixFunctions() {
        String test1 = String.format("%s@%s", pattern, shortText);
        String test2 = String.format("%s@%s", pattern, longText);
        PrefixFunctions pf = new PrefixFunctions();

        results = new ArrayList<>();
        String head = String.format("%s,%s,%s,%s", "\"Repetitions\"", "\"Text length\"", "\"Duration\"", "\"Success\"");
        results.add(head);

        TestResultWriter trw = new TestResultWriter("PrefixFast.csv");

        System.out.println("\nFast");
        for (int i = 1000; i < 1_00_000_001; i *= 10) {
            System.out.printf("%.0e | %e\n", (double)i, testFast(pf, test1, i));
            System.out.printf("%.0e | %e\n", (double)i, testFast(pf, test2, i));
        }
        trw.writeResults(results);

        trw = new TestResultWriter("PrefixSlow.csv");
        results = new ArrayList<>();
        results.add(head);
        System.out.println("Slow");
        for (int i = 1000; i < 1_000_001; i *= 10) {
            System.out.printf("%.0e | %e\n", (double)i, testSlow(pf, test1, i));
            System.out.printf("%.0e | %e\n", (double)i, testSlow(pf, test2, i));
        }

        trw.writeResults(results);

    }


    double testSlow(PrefixFunctions pf, String testText,  int repetitions) {
        int[] index = new int[0];
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            index = pf.prefixFunctionSlow(testText);
        }
        double end = (System.nanoTime() - start) / 1e9 / repetitions;

        boolean found = check(index, testText);

        String res = String.format("%.0e,%d,%e,%s", (double) repetitions, testText.length(), end, found);
        results.add(res);

        return end;
    }


    double testFast(PrefixFunctions pf, String testText,  int repetitions) {
        int[] index = new int[0];

        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            index = pf.prefixFunctionFast(testText);
        }
        double end = (System.nanoTime() - start) / 1e9 / repetitions;

        boolean found = check(index, testText);
        String res = String.format("%.0e,%d,%e,%s", (double) repetitions, testText.length(), end, found);
        results.add(res);

        return end;
    }

    boolean check(int[] index, String testText) {
        boolean found = false;
        int pLen = pattern.length();
        for (int i = 0; i < index.length; i++) {
            if (index[i] == pLen) found = pattern.equals(testText.substring(i - pLen, i));
        }
        return found;
    }

}
