import sortings.*;
import util.ArrayGen;
import util.FileWriteSvc;
import util.ViewSvc;

import java.util.HashMap;
import java.util.Objects;

public class QSMSSimpleTests {

    private HashMap<String, HashMap<String, Double>> results;
    private int upperLimit;

    public static void main(String[] args) {
        QSMSSimpleTests qsms = new QSMSSimpleTests(1_000_000);
        String[] types = new String[]{"Random", "Digits", "Sorted", "Reversed"};
        int[] array;
        App008 app008 = new App008();
        for (String arrayType : types) {
            System.out.println(arrayType);
            qsms.results = new HashMap<>();

            for (int i = 1; i <= 10_000_000; i *= 10) {
                qsms.results.put(String.valueOf(i), new HashMap<>());
                System.out.print("array: " + i);

                array = ArrayGen.getArray(i, arrayType);
                qsms.testMergeSort(array, i, arrayType);
                System.out.print(" Ascending: " + app008.isAscending(array));

                array = ArrayGen.getArray(i, arrayType);
                qsms.testQuickSort(array, i, arrayType);
                System.out.print(" Ascending: " + app008.isAscending(array));

                System.out.println();
            }
            ViewSvc.printHashMapQSMS(qsms.results);
            FileWriteSvc.writeHashMapQSMS(qsms.results, "QSMSSimpleTests" + arrayType + ".csv");
        }
    }

    public QSMSSimpleTests(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    void testMergeSort(int[] array, int key, String arrayType) {
        Double end = null;
        System.out.print(" testMergeSort");

        MergeSort sorter = new MergeSort();
        sorter.init(array);
        long start = System.nanoTime();
        sorter.sort();
        end = (System.nanoTime() - start) / 1e9;

        results.get(String.valueOf(key)).put("Merge Sort", end);
        System.out.printf(" %e ", end);
    }

    void testQuickSort(int[] array, int key, String arrayType) {
        System.out.print(" testQuickSort");
        Double end = null;
        if (!((arrayType.equals("Digits") && key > upperLimit) ||
                (arrayType.equals("Reversed")) && key >= upperLimit)) {
            QuickSort sorter = new QuickSort();
            sorter.init(array);
            long start = System.nanoTime();
            sorter.sort();
            end = (System.nanoTime() - start) / 1e9;
        }
        results.get(String.valueOf(key)).put("Quick Sort", end);
        System.out.printf(" %e ", end);

    }


    public HashMap<String, HashMap<String, Double>> getResults() {
        return results;
    }
}
