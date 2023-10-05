package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ViewSvc {

    public static void printHashMap(HashMap<String, HashMap<String, Double>> results) {
        String[] sorters = new String[]{"ES1", "ES2", "ES3"};
        String header = String.format("%20s %20s %20s %20s", "(N, T)", "ES1", "ES2", "ES3");
        System.out.println(header);
        results.keySet().stream().sorted().forEach(key -> {
            System.out.printf("%20s ", key);
            for (String srt : sorters)
                System.out.printf("%20s", results.get(key).get(srt));
            System.out.println();
        });

    }

    public static void printHashMapQSMS(HashMap<String, HashMap<String, Double>> results) {
        String[] sorters = new String[]{"Merge Sort", "Quick Sort"};
        String header = String.format("%20s %20s %20s", "Array Length", "Merge Sort", "Quick Sort");
        System.out.println(header);
        results.keySet().stream().sorted().forEach(key -> {
            System.out.printf("%20s ", key);
            for (String srt : sorters)
                System.out.printf("%20s", results.get(key).get(srt));
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
