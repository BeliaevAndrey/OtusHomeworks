package tests;

import substringSearch.*;

public class testSearchers {

    // region sample

    private final String sampleText = """
            In computer science, the Boyer–Moore string-search algorithm is an
            efficient string-searching algorithm that is the standard benchmark
            for practical string-search literature.[1] It was developed by
            Robert S. Boyer and J Strother Moore in 1977.[2] The original paper
            contained static tables for computing the pattern shifts without an
            explanation of how to produce them. The algorithm for producing the
            tables was published in a follow-on paper; this paper contained errors
            which were later corrected by Wojciech Rytter in 1980.[3][4]
                        
            The algorithm preprocesses the string being searched for (the pattern),
            but not the string being searched in (the text). It is thus well-suited
            for applications in which the pattern is much shorter than the text or
            where it persists across multiple searches. The Boyer–Moore algorithm
            uses information gathered during the preprocess step to skip sections
            of the text, resulting in a lower constant factor than many other string
            search algorithms. In general, the algorithm runs faster as the pattern
            length increases. The key features of the algorithm are to match on the
            tail of the pattern rather than the head, and to skip along the text in
            jumps of multiple characters rather than searching every single character
            in the text.
            -----
            (c) Wikipedia
            source: https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm
            """;

    // endregion

    public static void main(String[] args) {

        testSearchers runner = new testSearchers();

        int repeats = 1_000_000;
//        String samplePattern = "1980";
//        String samplePattern = "persists across multiple searches.";
        String samplePattern = "match on the tail of the pattern rather than the head, and to skip";

        runner.runTests(new SeminarFullIterativeSearchStraight(), samplePattern, repeats );
        runner.runTests(new SeminarFullIterativeSearchReverse(),  samplePattern, repeats );
        runner.runTests(new FullIterativeSearchStraight(),        samplePattern, repeats );
        runner.runTests(new FullIterativeSearchReverse(),         samplePattern, repeats );
        runner.runTests(new BoyerMooreHorspoolShift1(),           samplePattern, repeats );
        runner.runTests(new BoyerMooreHorspool(),                 samplePattern, repeats );
        runner.runTests(new BoyerMoore(),                         samplePattern, repeats );

    }

    void runTests(ISearcher searcher, String pattern) {
        String text = sampleText.replaceAll("\n", " ");

        searcher.init(text, pattern);
        int pos = searcher.search();
        int cmp = searcher.getCompares();
        System.out.printf("\n%-65s | pos: %4d; compares: %4d\n",
                searcher.getHeader(), pos, cmp);
//        System.out.println("=".repeat(80));
    }

    void runTests(ISearcher searcher, String pattern, int repeats) {
        int pos = 0;
        String text = sampleText.replaceAll("\n", " ");

        searcher.init(text, pattern);

        long start = System.nanoTime();
        for (int i = 0; i < repeats; i++) {
            pos = searcher.search();
        }
        long end = (System.nanoTime() - start) / repeats;

        int cmp = searcher.getCompares();
        System.out.printf("%-65s | pos: %4d; compares: %4d; repeats: %d; mean time (s): %e\n",
                searcher.getHeader(), pos, cmp, repeats, end / 1e9);
//        System.out.println("=".repeat(80));
    }


    void printer() {
        String text = sampleText.replaceAll("\n", " ");
        System.out.println(text);
        System.out.println("\n\n");
        System.out.println(sampleText);

    }

}
