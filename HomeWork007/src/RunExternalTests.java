import sortings.*;
import util.FileWriteSvc;
import util.TestLoader;     // module HomeWork 006

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
                ret.testSelection(tl, key, testDir);
                ret.testHeapSort(tl, key, testDir);
            });
            FileWriteSvc.writeExtTestsResults(ret.results);
        }
    }

    void testSelection(TestLoader tl, Integer key, String testDir) {
        if (key >= 1000000) return;
        SelectionSort sorter = new SelectionSort();
        StringBuilder sb = new StringBuilder();
        sorter.init(tl.tests_in.get(key));
        sorter.sort();
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Selection Sort");
        results.get(testDir).put("SelectionSort" + key, sb.toString());
    }

    void testHeapSort(TestLoader tl, Integer key, String testDir) {
        HeapSort sorter = new HeapSort();
        StringBuilder sb = new StringBuilder();
        sorter.init(tl.tests_in.get(key));
        sorter.sort();
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "SHeap Sort");
        results.get(testDir).put("HeapSort" + key, sb.toString());
    }


    /*
    void testShell(TestLoader tl, Integer key, String testDir) {
        ShellSort shell = new ShellSort();
        StringBuilder sb = new StringBuilder();
        shell.init(tl.tests_in.get(key));
        shell.sort();
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Shell Sort");
        results.get(testDir).put("ShellSort" + key, sb.toString());
    }

    void testShellKnuth(TestLoader tl, Integer key, String testDir) {
        ShellSort shell = new ShellSort();
        StringBuilder sb = new StringBuilder();
        shell.init(tl.tests_in.get(key));
        shell.sortKnuth(key);
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Shell Sort Knuth");
        results.get(testDir).put("ShellKnuth" + key, sb.toString());
    }

    void testShellSedgewick(TestLoader tl, Integer key, String testDir) {
        ShellSort shell = new ShellSort();
        StringBuilder sb = new StringBuilder();
        shell.init(tl.tests_in.get(key));
        shell.sortSedgewick86(key);
        Arrays.stream(tl.tests_in.get(key)).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Shell Sort Sedgewick");
        results.get(testDir).put("ShellSedgewick" + key, sb.toString());
    }

    void testInsertSort(TestLoader tl, Integer key, String testDir) {
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

    void testInsertBin(TestLoader tl, Integer key, String testDir) {
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

    void testInsertShift(TestLoader tl, Integer key, String testDir) {
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

    void testBubble(TestLoader tl, Integer key, String testDir) {
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
    */
    void verify(TestLoader tl, Integer key, String result, String sorter) {
        System.out.println(sorter + ": " + result.equals(tl.tests_out.get(key)));
    }

}
