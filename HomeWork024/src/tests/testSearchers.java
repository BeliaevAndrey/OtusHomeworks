package tests;

import substringSearch.*;
import util.SampleTextsReader;
import util.TestResultWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class testSearchers {

    static {
        Locale.setDefault(Locale.US);       // need '.' not ',' as decimal separator
    }
    private ArrayList<String> results;

    String sampleNo = "";
    String patternNo = "";

    public static void main(String[] args) {

        testSearchers runner = new testSearchers();
        SampleTextsReader sampleTextsReader  = new SampleTextsReader();
        runner.results = new ArrayList<>();
        TestResultWriter trw = new TestResultWriter();

        HashMap<Integer, ArrayList<String>> samples = sampleTextsReader.getSamples();
        int count = sampleTextsReader.getCount();
        int repeats = (int)1e5;

        for (int i = 0; i < count; i++) {
            String sample = samples.get(i).get(0);
            runner.sampleNo = String.valueOf(i + 1);
            System.out.printf("Sample:\n%s", sample);
            for (int j = 1; j < samples.get(i).size(); j++) {
                String pattern = samples.get(i).get(j);
                runner.patternNo = String.format("%d.%d", i + 1, j);
                System.out.printf("\nSearch for: %s\n", pattern);
                runner.runTests(sample, pattern, repeats, new SeminarFullIterativeSearchStraight());
                runner.runTests(sample, pattern, repeats, new SeminarFullIterativeSearchReverse());
                runner.runTests(sample, pattern, repeats, new FullIterativeSearchStraight());
                runner.runTests(sample, pattern, repeats, new FullIterativeSearchReverse());
                runner.runTests(sample, pattern, repeats, new BoyerMooreHorspoolShift1());
                runner.runTests(sample, pattern, repeats, new BoyerMooreHorspool());
                runner.runTests(sample, pattern, repeats, new BoyerMoore());
            }
        }
        trw.writeResults(runner.results);
    }

    private void runTests(String sample, String pattern, int repeats, ISearcher searcher) {
        int position = 0;
        String text = sample.strip().replaceAll("\n", " ");
        pattern = pattern.strip();
        searcher.init(text, pattern);

        long start = System.nanoTime();
        for (int i = 0; i < repeats; i++) {
            position = searcher.search();
        }
        double time = (double) (System.nanoTime() - start) / repeats;
        int compares = searcher.getCompares();

        boolean checked = check(text, pattern, position);

        System.out.printf("%-65s | pos: %4d; check passed: %s ;compares: %4d; repeats: %d; mean time (s): %3.2e\n",
                searcher.getHeader(), position, checked, compares, repeats, time / 1e9);

        appendResults(searcher.getHeader(),position, compares, repeats, time, checked);
    }

    private void appendResults(String header,int position, int compares, int repeats, double time, boolean checked) {
        String line = String.format("%s,%s,%s,%d,%s,%d,%1.0e,%1.2e",
                header, sampleNo, patternNo, position,  checked, compares, (double)repeats, time / 1e9);
        results.add(line);
    }

    private boolean check(String sample, String pattern, int pos) {
        return pattern.equals(sample.substring(pos, pos + pattern.length()));
    }

}
