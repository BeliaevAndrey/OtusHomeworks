package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class FileWriteSvc {
    private Path path = Path.of(System.getProperty("user.dir"), "HomeWork006", "TestResults");

    public static void main(String[] args) {
        FileWriteSvc fws = new FileWriteSvc();
        fws.checkDir(fws.path);
    }

    void checkDir(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Created: " + path);
        }
    }

    public static void writeHashMap(HashMap<Integer, HashMap<String, Double>> results, String fileName) {
        FileWriteSvc fws = new FileWriteSvc();
        fws.checkDir(fws.path);
        String out = makeString(results);
        Path outFile = Path.of(fws.path.toString(), fileName);
        try (BufferedWriter bw = Files.newBufferedWriter(outFile)) {
            bw.append(out);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String makeString(HashMap<Integer, HashMap<String, Double>> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("Array length,Bubble,Insertion,Insert Shift,Insert Binary,Shell,Shell Knuth,Shell Sedgewick\n");
        results.keySet().stream().sorted().forEach(key -> {
            sb.append(key).append(",");
            for (String sorter : results.get(key).keySet())
                sb.append(results.get(key).get(sorter)).append(",");
            sb.replace(sb.length() - 1, sb.length(), "\n");
        });
        System.out.println(sb);
        return sb.toString();
    }
}
