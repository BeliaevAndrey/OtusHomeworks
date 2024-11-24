package tests;

import hw.FiniteAutomata;

public class TestAutomata {
    public static void main(String[] args) {
        TestAutomata ta = new TestAutomata();
        String pattern = "REAL";

        String testText1 = "SOMEPHRASEWITHREALWORD"; // ta.genText(126);
        String testText2 = "ASDFGTRWJMKGSJHFGKLKJHJFSFFYTVCNBFVHJGDJHGDCJGFDLKNMHJKUTJNHFHSOMEPHRASEWITHREALWORD";
        FiniteAutomata fa = new FiniteAutomata(pattern);
        int res = fa.search(testText2);
        System.out.println(res);
        for (int i = 10; i < 10001; i*=10) {
            System.out.printf("repetitions: %d, time: %e\n", i, (double)ta.testFA(fa, testText1, i, pattern));
            System.out.printf("repetitions: %d, time: %e\n", i, (double)ta.testFA(fa, testText2, i, pattern));
        }

    }

    long testFA(FiniteAutomata fa, String testText, int repetitions, String pattern) {
        int index = -10;
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) index = fa.search(testText);
        long end = System.nanoTime() - start;
        System.out.printf("start: %d, %s\n", index, pattern.equals(testText.substring(index, index + fa.getLength())));
        return end;
    }

}
