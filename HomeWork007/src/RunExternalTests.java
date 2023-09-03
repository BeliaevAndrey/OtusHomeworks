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

    void verify(TestLoader tl, Integer key, String result, String sorter) {
        System.out.println(sorter + ": " + result.equals(tl.tests_out.get(key)));
    }

}
