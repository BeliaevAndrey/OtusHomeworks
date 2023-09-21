package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.TreeMap;

public class FileWriteSvc {
    
    private final String moduleDir = "HomeWork009";
    private final Path path = Path.of(System.getProperty("user.dir"), "HomeWork009", "TestResults");


    boolean checkDir(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            System.out.println("Created: " + path);
        }
        return true;
    }

    public static void writeHashMap(HashMap<Integer, TreeMap<String, Double>> results, String fileName) {
        FileWriteSvc fws = new FileWriteSvc();
        if (!fws.checkDir(fws.path)) return;
        String out = makeString(results);
        Path outFile = Path.of(fws.path.toString(), fileName);
        try (BufferedWriter bw = Files.newBufferedWriter(outFile)) {
            bw.append(out);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String makeString(HashMap<Integer, TreeMap<String, Double>> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("Array length,Bucket Sort,Counting Sort,Radix Sort\n");
        results.keySet().stream().sorted().forEach(key -> {
            sb.append(key).append(",");
            for (String sorter : results.get(key).keySet())
                sb.append(results.get(key).get(sorter)).append(",");
            sb.replace(sb.length() - 1, sb.length(), "\n");
        });
        return sb.toString();
    }

    public static void writeExtTestsResults(HashMap<String, HashMap<String, String>> results) {
        FileWriteSvc fws = new FileWriteSvc();
        String basicPath = System.getProperty("user.dir");
        for (String key : results.keySet()) {
            fws.checkDir(Path.of(basicPath, fws.moduleDir, "TestsResultsExternal", key));
            for (String methodLen : results.get(key).keySet()) {
                Path outPath = Path.of(
                        basicPath, fws.moduleDir,
                        "TestsResultsExternal", key, "test" + methodLen + ".result");
                try (BufferedWriter bw = Files.newBufferedWriter(outPath)) {
                    bw.append(results.get(key).get(methodLen));
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
