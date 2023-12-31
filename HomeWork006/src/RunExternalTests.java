import sortings.BubbleSort;
import sortings.InsertionSort;
import sortings.ShellSort;
import util.FileWriteSvc;
import util.TestLoader;

import java.util.Arrays;
import java.util.HashMap;

public class RunExternalTests {
    HashMap<String, HashMap<String, String>> results = new HashMap<>();

    public static void main(String[] args) {
        String[] tests = new String[]{"0.random", "1.digits", "2.sorted", "3.revers"};
        RunExternalTests ret = new RunExternalTests();

        for (String testDir : tests) {
            TestLoader tl = new TestLoader(testDir);
            ret.results.put(testDir, new HashMap<>());
            tl.tests_in.keySet().stream().sorted().forEach(key -> {
                ret.testShell(tl, key, testDir);
                ret.testShellKnuth(tl, key, testDir);
                ret.testShellSedgewick(tl, key, testDir);
                ret.testInsertSort(tl, key, testDir);
                ret.testInsertShift(tl, key, testDir);
                ret.testInsertBin(tl, key, testDir);
                ret.testBubble(tl, key, testDir);
            });
            FileWriteSvc.writeExtTestsResults(ret.results);
        }
    }

    void testShell(TestLoader tl, int key, String testDir) {
        ShellSort shell = new ShellSort();
        StringBuilder sb = new StringBuilder();
        shell.init(tl.tests_in.get(key));
        shell.sort();
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Shell Sort");
        results.get(testDir).put("ShellSort" + key, sb.toString());
    }

    void testShellKnuth(TestLoader tl, int key, String testDir) {
        ShellSort shell = new ShellSort();
        StringBuilder sb = new StringBuilder();
        shell.init(tl.tests_in.get(key));
        shell.sortKnuth(key);
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Shell Sort Knuth");
        results.get(testDir).put("ShellKnuth" + key, sb.toString());
    }

    void testShellSedgewick(TestLoader tl, int key, String testDir) {
        ShellSort shell = new ShellSort();
        StringBuilder sb = new StringBuilder();
        shell.init(tl.tests_in.get(key));
        shell.sortSedgewick86(key);
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Shell Sort Sedgewick");
        results.get(testDir).put("ShellSedgewick" + key, sb.toString());
    }

    void testInsertSort(TestLoader tl, int key, String testDir) {
        if (key >= 1000000) return;
        InsertionSort insert = new InsertionSort();
        StringBuilder sb = new StringBuilder();
        insert.init(tl.tests_in.get(key));
        insert.sort();
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Insertion Sort");
        results.get(testDir).put("Insertion" + key, sb.toString());

    }

    void testInsertBin(TestLoader tl, int key, String testDir) {
        if (key >= 1000000) return;
        InsertionSort insert = new InsertionSort();
        StringBuilder sb = new StringBuilder();
        insert.init(tl.tests_in.get(key));
        insert.shiftBinSearchSort();
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Insertion Binary Sort");
        results.get(testDir).put("InsertBinary" + key, sb.toString());
    }

    void testInsertShift(TestLoader tl, int key, String testDir) {
        if (key > 1000000) return;
        InsertionSort insert = new InsertionSort();
        StringBuilder sb = new StringBuilder();
        insert.init(tl.tests_in.get(key));
        insert.shiftSort();
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Insertion Shift Sort");
        results.get(testDir).put("InsertShift" + key, sb.toString());
    }

    void testBubble(TestLoader tl, int key, String testDir) {
        if (key >= 1000000) return;
        BubbleSort bubble = new BubbleSort();
        StringBuilder sb = new StringBuilder();
        bubble.init(tl.tests_in.get(key));
        bubble.sort();
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Bubble Sort");
        results.get(testDir).put("Bubble" + key, sb.toString());
    }

    void verify(TestLoader tl, int key, String result, String sorter) {
        System.out.println(sorter + ": " + result.equals(tl.tests_out.get(key)));
    }

}
