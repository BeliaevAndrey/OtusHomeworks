import sortings.*;
import util.ArrayGen;
import util.FileWriteSvc;
import util.ViewSvc;

import java.util.HashMap;
import java.util.TreeMap;

public class SortingsSimpleTests {

    private HashMap<Integer, TreeMap<String, Double>> results;
    private int upperLimit;

    public static void main(String[] args) {
        SortingsSimpleTests sst = new SortingsSimpleTests(1000_000);
        String[] types = new String[]{"Random", "Digits", "Sorted", "Reversed"};
        int[] array;
        App007 app007 = new App007();
        for (String arrayType : types) {
            System.out.println(arrayType);
            sst.results = new HashMap<>();

            for (int i = 1; i <= 10_000_000; i *= 10) {
                sst.results.put(i, new TreeMap<>());
                System.out.print("array: " + i);

                array = ArrayGen.getArray(i, arrayType);
                sst.testSelection(array, i);
                System.out.print(" Ascending: " + app007.isAscending(array));

                array = ArrayGen.getArray(i, arrayType);
                sst.testHeapSort(array, i);
                System.out.print(" Ascending: " + app007.isAscending(array));

                System.out.println();
            }
            ViewSvc.printHashMap(sst.results);
            FileWriteSvc.writeHashMap(sst.results, "SimpleTests" + arrayType + ".csv");
        }
    }

    public SortingsSimpleTests(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    void testSelection(int[] array, int key) {
        Double end = null;
        System.out.print(" testSelection");
        if (upperLimit > 0 && key <= upperLimit) {
            SelectionSort sorter = new SelectionSort();
            sorter.init(array);
            long start = System.nanoTime();
            sorter.sort();
            end = (System.nanoTime() - start) / 1e9;
        }
        results.get(key).put("Selection Sort", end);
        System.out.printf(" %e ", end);
    }

    void testHeapSort(int[] array, int key) {
        System.out.print(" testHeapSort");
        HeapSort sorter = new HeapSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sort();
        Double end = (System.nanoTime() - start) / 1e9;
        results.get(key).put("Heap Sort", end);
        System.out.printf(" %e ", end);

    }


    public HashMap<Integer, TreeMap<String, Double>> getResults() {
        return results;
    }
}
