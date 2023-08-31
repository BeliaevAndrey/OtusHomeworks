import sortings.BubbleSort;
import sortings.InsertionSort;
import sortings.ShellSort;
import util.ArrayGen;
import util.FileWriteSvc;
import util.ViewSvc;

import java.util.HashMap;

public class SortingsSimpleTests {

    HashMap<Integer, HashMap<String, Double>> results = new HashMap<>();

    public static void main(String[] args) {
        SortingsSimpleTests sst = new SortingsSimpleTests();

        for (int i = 1; i < 10_000_0000; i *= 10) {
            sst.results.put(i, new HashMap<>());

            int[] array = ArrayGen.setRandom(i);
            sst.testBubble(array, i);

            array = ArrayGen.setRandom(i);
            sst.testInsertion(array, i);

            array = ArrayGen.setRandom(i);
            sst.testInsertionShift(array, i);

            array = ArrayGen.setRandom(i);
            sst.testInsertionShiftBinary(array, i);

            array = ArrayGen.setRandom(i);
            sst.testShell(array, i);

            array = ArrayGen.setRandom(i);
            sst.testShellKnuth(array, i);

            array = ArrayGen.setRandom(i);
            sst.testShellSedgewich(array, i);
        }
        ViewSvc.printHashMap(sst.results);
        FileWriteSvc.writeHashMap(sst.results, "SimpleTests.csv");
    }

    void testBubble(int[] array, int key) {
        if (key > 1000000)
        {
            results.get(key).put("Bubble", null);
            return;
        }
        BubbleSort sorter = new BubbleSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sort();
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Bubble", end);
    }

    void testInsertion(int[] array, int key) {
        if (key > 1000000)
        {
            results.get(key).put("Bubble", null);
            return;
        }
        InsertionSort sorter = new InsertionSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sort();
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Insertion", end);
    }

    void testInsertionShift(int[] array, int key) {
        if (key > 1000000)
        {
            results.get(key).put("Bubble", null);
            return;
        }
        InsertionSort sorter = new InsertionSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.shiftSort();
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Insert Shift", end);
    }

    void testInsertionShiftBinary(int[] array, int key) {
        InsertionSort sorter = new InsertionSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.shiftBinSearchSort();
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Insert Binary", end);
    }

    void testShell(int[] array, int key) {
        ShellSort sorter = new ShellSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sort();
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Shell", end);
    }

    void testShellKnuth(int[] array, int key) {
        ShellSort sorter = new ShellSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sortKnuth(key);
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Shell Knuth", end);
    }

    void testShellSedgewich(int[] array, int key) {
        ShellSort sorter = new ShellSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sortSedgewick86(key);
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Shell Sedgewick", end);
    }
}
