package tests;

import hw.FiniteAutomata;
import hw.KMP;
import util.TestResultWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class TestTimedKMP {

    static {
        Locale.setDefault(Locale.US);
    }

    static ArrayList<String> results = new ArrayList<>();

    static String pattern = "REAL";

    static String shortText = "SOMEPHRASEWITHREALWORD";
    static String longText = "SOMEVERYVERYVERYVERYVERYLONGVERYVERYVERYVERYLONGveryveryveryverylongPHRASEWITHREALWORD";


    public static void main(String[] args) {
        TestTimedKMP tta = new TestTimedKMP();
        tta.testKMP();

        TestResultWriter trw = new TestResultWriter("ResultsKMP.csv");
        trw.writeResults(results);
    }


    public void testKMP() {
        String test1 = String.format("%s@%s", pattern, shortText);
        String test2 = String.format("%s@%s", pattern, longText);
        KMP kmp = new KMP();

//        System.out.printf("%15s|%15s|%15s|%15s\n", "Repetitions", "Text length", "Duration", "Success");
//        System.out.println(head);
        String head = String.format("%s,%s,%s,%s", "\"Repetitions\"", "\"Text length\"", "\"Duration\"", "\"Success\"");
        results.add(head);
        for (int i = 1000; i < 1_00_000_001; i *= 10) {
            test(kmp, test1, i);
            test(kmp, test2, i);
        }

    }

    void test(KMP kmp, String testText, int repetitions) {
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) kmp.createPi(testText);
        double end = (double) (System.nanoTime() - start) / 1e9;

        int[] index = kmp.getPi();
        boolean found = true;
        int patLen = kmp.getPatternLen();

        for (int i = 0; i < index.length; i++) {
            if (index[i] == patLen) {
                found = pattern.equals(testText.substring(i - patLen, i));
            }
        }

        String res = String.format("%.0e,%d,%e,%s", (double) repetitions, testText.length(), end, found);
        results.add(res);

//        String out = String.format("%15d|%15d|%15e|%15s\n", repetitions, testText.length(), end, found);
//        System.out.print(out);

    }

}

