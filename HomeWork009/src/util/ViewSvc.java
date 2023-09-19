package util;

import java.util.HashMap;
import java.util.TreeMap;

public class ViewSvc {
    public static void printHashMap(HashMap<Integer, TreeMap<String, Double>> results) {
        String[] srtNames = new String[]{"Counting Sort", "Bucket Sort", "Radix Sort"};
        String format = "%-16s%-16s%-16s%-16s\n";
        System.out.printf(format, "Array length", "Counting Sort", "Bucket Sort", "Radix Sort");

        results.keySet().stream().sorted().forEach(key -> {
            System.out.printf("%-16d", key);
            for (String sorter : srtNames)
                System.out.printf("%-16e", results.get(key).get(sorter));
            System.out.println();
        });
    }

    public static void printExternalTestsResults(HashMap<String, HashMap<String, String>> results) {
        for (String key : results.keySet()) {
            for (String len : results.get(key).keySet()) {
                System.out.println(results.get(key).get(len));
            }
        }
    }
}
