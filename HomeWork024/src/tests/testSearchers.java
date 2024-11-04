package tests;

import substringSearch.*;
import util.SampleTextsReader;

import java.util.ArrayList;
import java.util.HashMap;

public class testSearchers {

    public static void main(String[] args) {

        testSearchers runner = new testSearchers();
        SampleTextsReader sampleTextsReader  = new SampleTextsReader();

        HashMap<Integer, ArrayList<String>> samples = sampleTextsReader.getSamples();
        int count = sampleTextsReader.getCount();
        int repeats = 10;

        for (int i = 0; i < count; i++) {
            String sample = samples.get(i).get(0);
            System.out.printf("Sample:\n%s", sample);
            for (int j = 1; j < samples.get(i).size(); j++) {
                String pattern = samples.get(i).get(j);
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

    }

    void runTests(String sample, String pattern, int repeats, ISearcher searcher) {
        int pos = 0;
        String text = sample.replaceAll("\n", " ");
        pattern = pattern.strip();
        searcher.init(text, pattern);

        long start = System.nanoTime();
        for (int i = 0; i < repeats; i++) {
            pos = searcher.search();
        }
        long end = (System.nanoTime() - start) / repeats;

        int cmp = searcher.getCompares();
        System.out.printf("%-65s | pos: %4d; compares: %4d; repeats: %d; mean time (s): %3.2e\n",
                searcher.getHeader(), pos, cmp, repeats, end / 1e9);
    }

}
