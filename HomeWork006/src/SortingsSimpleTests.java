import sortings.BubbleSort;
import sortings.InsertionSort;
import sortings.ShellSort;
import util.ArrayGen;
import util.FileWriteSvc;
import util.ViewSvc;

import java.util.HashMap;
import java.util.TreeMap;

public class SortingsSimpleTests {

    private HashMap<Integer, TreeMap<String, Double>> results;
    private int upperLimit = 0;

    public static void main(String[] args) {
        SortingsSimpleTests sst = new SortingsSimpleTests(1000_000);
        String[] types = new String[]{"Random", "Digits", "Sorted", "Reversed"};
        Integer[] array;
        for (String arrayType : types) {
            sst.results = new HashMap<>();

            for (int i = 1; i <= 10_000_000; i *= 10) {
                sst.results.put(i, new TreeMap<>());
                System.out.print("array: " + i);

                array = ArrayGen.getArray(i, arrayType);
                sst.testBubble(array, i);

                array = ArrayGen.getArray(i, arrayType);
                sst.testInsertion(array, i);

                array = ArrayGen.getArray(i, arrayType);
                sst.testInsertionShift(array, i);

                array = ArrayGen.getArray(i, arrayType);
                sst.testInsertionBinary(array, i);

                array = ArrayGen.getArray(i, arrayType);
                sst.testShell(array, i);

                array = ArrayGen.getArray(i, arrayType);
                sst.testShellKnuth(array, i);

                array = ArrayGen.getArray(i, arrayType);
                sst.testShellSedgewich(array, i);
                System.out.println();
            }
            ViewSvc.printHashMap(sst.results);
            FileWriteSvc.writeHashMap(sst.results, "SimpleTests" + arrayType + ".csv");
        }
    }

    public SortingsSimpleTests(int upperLimit){
        this.upperLimit = upperLimit;
    }

    void testBubble(Integer[] array, int key) {
        Double end = null;
        System.out.print(" testBubble");
        if (upperLimit > 0 && key < upperLimit) {
            BubbleSort sorter = new BubbleSort();
            sorter.init(array);
            long start = System.nanoTime();
            sorter.sort();
            end = (System.nanoTime() - start) / 1e9;
        }
        results.get(key).put("Bubble", end);
        System.out.printf(" %e ", end);
    }

    void testInsertion(Integer[] array, int key) {
        Double end = null;
        System.out.print(" testInsertion");
        if (upperLimit > 0 && key < upperLimit)  {
            InsertionSort sorter = new InsertionSort();
            sorter.init(array);
            long start = System.nanoTime();
            sorter.sort();
            end = (System.nanoTime() - start) / 1e9;
        }
        results.get(key).put("Insertion", end);
        System.out.printf(" %e ", end);

    }

    void testInsertionShift(Integer[] array, int key) {
        Double end = null;
        System.out.print(" testInsertionShift");
        if (upperLimit > 0 && key <= upperLimit)  {
            InsertionSort sorter = new InsertionSort();
            sorter.init(array);
            long start = System.nanoTime();
            sorter.shiftSort();
            end = (System.nanoTime() - start) / 1e9;
        }
        results.get(key).put("Insert Shift", end);
        System.out.printf(" %e ", end);
    }

    void testInsertionBinary(Integer[] array, int key) {
        Double end = null;
        System.out.print(" testInsertionShiftBinary");
        if (upperLimit > 0 && key < upperLimit)  {
            InsertionSort sorter = new InsertionSort();
            sorter.init(array);
            long start = System.nanoTime();
            sorter.shiftBinSearchSort();
            end = (System.nanoTime() - start) / 1e9;
        }
        results.get(key).put("Insert Binary", end);
        System.out.printf(" %e ", end);
    }

    void testShell(Integer[] array, int key) {
        System.out.print(" testShell");
        ShellSort sorter = new ShellSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sort();
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Shell", end);
        System.out.printf(" %e ", end);
    }

    void testShellKnuth(Integer[] array, int key) {
        System.out.print(" testShellKnuth");
        ShellSort sorter = new ShellSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sortKnuth(key);
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Shell Knuth", end);
        System.out.printf(" %e ", end);
    }

    void testShellSedgewich(Integer[] array, int key) {
        System.out.print(" testShellSedgewich");
        ShellSort sorter = new ShellSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sortSedgewick86(key);
        double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Shell Sedgewick", end);
        System.out.printf(" %e ", end);
    }

    public HashMap<Integer, TreeMap<String, Double>> getResults() {
        return results;
    }
}
