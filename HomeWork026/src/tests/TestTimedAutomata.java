package tests;

import hw.FiniteAutomata;
import util.TestResultWriter;

import java.util.ArrayList;
import java.util.Locale;

public class TestTimedAutomata {

    static {
        Locale.setDefault(Locale.US);
    }

    static ArrayList<String> results = new ArrayList<>();

    static String pattern = "REAL";

    static String shortText = "SOMEPHRASEWITHREALWORD";
    static String longText = "SOMEVERYVERYVERYVERYVERYLONGVERYVERYVERYVERYLONGveryveryveryverylongPHRASEWITHREALWORD";


    public static void main(String[] args) {
        TestTimedAutomata tta = new TestTimedAutomata();
        tta.testFiniteAutomata();

        TestResultWriter trw = new TestResultWriter("ResultsFA.csv");
        trw.writeResults(results);
    }


    public void testFiniteAutomata() {
        TestTimedAutomata ta = new TestTimedAutomata();
        FiniteAutomata fa = new FiniteAutomata(pattern);

//        System.out.printf("%15s|%15s|%15s|%15s\n", "Repetitions", "Text length", "Duration", "Success");
//        System.out.println(head);
        String head = String.format("%s,%s,%s,%s", "\"Repetitions\"", "\"Text length\"", "\"Duration\"", "\"Success\"");
        results.add(head);
        for (int i = 1000; i < 1_00_000_001; i *= 10) {
            ta.testFA(fa, shortText, i);
            ta.testFA(fa, longText, i);
        }

    }

    void testFA(FiniteAutomata fa, String testText, int repetitions) {
        int index = -10;
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) index = fa.search(testText);
        double end = (double) (System.nanoTime() - start) / 1e9 / repetitions;

        boolean found = index > 0 && pattern.equals(testText.substring(index, index + fa.getLength()));

        String res = String.format("%.0e,%d,%e,%s", (double) repetitions, testText.length(), end, found);
        results.add(res);

//        String out = String.format("%15d|%15d|%15e|%15s\n", repetitions, testText.length(), end, found);
//        System.out.print(out);

    }

}
