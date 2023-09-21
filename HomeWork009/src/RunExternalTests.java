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
                ret.testCountingSort(tl, key, testDir);
                ret.testBucketSort(tl, key, testDir);
                ret.testRadixSort(tl, key, testDir);
            });
            FileWriteSvc.writeExtTestsResults(ret.results);
        }
    }

    void testCountingSort(TestLoader tl, Integer key, String testDir) {
        CountingSort sorter = new CountingSort();
        StringBuilder sb = new StringBuilder();
        int[] tmp = new int[key];
        System.arraycopy(tl.tests_in.get(key), 0, tmp, 0, key);
        sorter.init(tmp);
        sorter.sort();
        Arrays.stream(tmp).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Counting Sort");
        results.get(testDir).put("Counting Sort" + key, sb.toString());
    }

    void testBucketSort(TestLoader tl, Integer key, String testDir) {
        BucketSortSeminar sorter = new BucketSortSeminar();
        StringBuilder sb = new StringBuilder();
        int[] tmp = new int[key];
        System.arraycopy(tl.tests_in.get(key), 0, tmp, 0, key);
        sorter.init(tmp);
        sorter.Bucket();
        Arrays.stream(tmp).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Bucket Sort");
        results.get(testDir).put("Bucket Sort" + key, sb.toString());
    }

    void testRadixSort(TestLoader tl, Integer key, String testDir) {
        RadixSort sorter = new RadixSort();
        StringBuilder sb = new StringBuilder();
        int[] tmp = new int[key];
        System.arraycopy(tl.tests_in.get(key), 0, tmp, 0, key);
        sorter.init(tmp);
        sorter.sort();
        Arrays.stream(tmp).forEach(num -> sb.append(num).append(" "));
        sb.replace(sb.length() - 1, sb.length(), "");
        verify(tl, key, sb.toString(), "Radix Sort");
        results.get(testDir).put("Radix Sort" + key, sb.toString());
    }

    void verify(TestLoader tl, Integer key, String result, String sorter) {
        System.out.println(sorter + ": " + result.equals(tl.tests_out.get(key)));
    }

}
