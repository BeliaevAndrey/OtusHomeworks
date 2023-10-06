package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class ViewWriteSvc {

    static String[] headers = new String[]{
            "Array type", "Array length", "Fill Time (s)", "GetSorted Time (s)", "GetSorted Check",
            "Insert Time (s)", "Search Time (s)", "Search Check", "Remove Time (s)", "Check"};

    static public void printHashMap(HashMap<String, HashMap<String, Object>> results) {
        for (String row : headers) {
            System.out.printf("%20s ", row);
            for (String key : results.keySet()) {
                System.out.printf("%20s", results.get(key).get(row));
            }
            System.out.println();
        }
    }

    static public void writeResultsToFile(HashMap<String, HashMap<String, Object>> results) {
        Path path = Path.of("HomeWork010/TestResults", "BST_Test_results.csv");
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (String row : headers) {
                sb.append(row).append(",");
                for (String key : results.keySet())
                    sb.append(results.get(key).get(row)).append(",");
                sb.replace(sb.length() - 1, sb.length(), "\n");
            }
            bw.write(sb.toString());
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
