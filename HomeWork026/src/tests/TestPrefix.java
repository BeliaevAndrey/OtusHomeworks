package tests;

import hw.PrefixFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class TestPrefix {


    static {
        Locale.setDefault(Locale.US);
    }

    static ArrayList<String> results = new ArrayList<>();

    static String pattern = "REAL";

    static String shortText = "SOMEPHRASEWITHREALWORD";
    static String longText = "SOMEVERYVERYVERYVERYVERYLONGVERYVERYVERYVERYLONGveryveryveryverylongPHRASEWITHREALWORD";

    public static void main(String[] args) {

        TestPrefix tpf = new TestPrefix();

//        int[] res2 = pf.prefixFunctionSlow(testText);
//        System.out.printf("res3: %d  %s\n", res2.length, Arrays.toString(res2));
//
//        int[] res3 = pf.prefixFunctionFast(testText);
//        System.out.printf("res3: %d  %s\n", res3.length, Arrays.toString(res3));
        tpf.testPrefixFunctions();

    }

    public void testPrefixFunctions() {
        String test1 = String.format("%s@%s", pattern, shortText);
        String test2 = String.format("%s@%s", pattern, longText);
        PrefixFunctions pf = new PrefixFunctions();

//        System.out.printf("%15s|%15s|%15s|%15s\n", "Repetitions", "Text length", "Duration", "Success");
//        System.out.println(head);
        String head = String.format("%s,%s,%s,%s", "\"Repetitions\"", "\"Text length\"", "\"Duration\"", "\"Success\"");
        results.add(head);
        System.out.println("Slow");
        for (int i = 1000; i < 1_000_001; i *= 10) {
            System.out.printf("%.0e | %e\n", (double)i, testSlow(pf, test1, i));
            System.out.printf("%.0e | %e\n", (double)i, testSlow(pf, test2, i));
        }

        System.out.println("\nFast");
        for (int i = 1000; i < 1_00_000_001; i *= 10) {
            System.out.printf("%.0e | %e\n", (double)i, testFast(pf, test1, i));
            System.out.printf("%.0e | %e\n", (double)i, testFast(pf, test2, i));
        }

    }


    double testSlow(PrefixFunctions pf, String text,  int repetitions) {
        int[] res = null;
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            res = pf.prefixFunctionSlow(text);
        }
        double end = (System.nanoTime() - start) / 1e9;
        return end;
    }


    double testFast(PrefixFunctions pf, String text,  int repetitions) {
        int[] res = null;

        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            res = pf.prefixFunctionFast(text);
        }
        double end = (System.nanoTime() - start) / 1e9;
        return end;
    }

}
