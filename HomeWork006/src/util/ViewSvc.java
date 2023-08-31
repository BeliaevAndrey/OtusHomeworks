package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ViewSvc {
    public static void printHashMap(HashMap<Integer, TreeMap<String, Double>> results) {
        String[] srtNames = new String[]{
                "Bubble","Insertion","Insert Shift","Insert Binary","Shell", "Shell Knuth", "Shell Sedgewick"};
        String format = "%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s\n";
        System.out.printf(format,
                "Array length", "Bubble",
                "Insertion","Insert Shift","Insert Binary",
                "Shell", "Shell Knuth", "Shell Sedgewick");

        results.keySet().stream().sorted().forEach(key->{
            System.out.printf("%-16d", key);
            for (String sorter : srtNames)
                System.out.printf("%-16e",  results.get(key).get(sorter));
            System.out.println();
        });
    }
}
