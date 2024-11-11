package enhlishDictPrefixTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FReader {

    private final Path basicPath;

    FReader() {
        basicPath = Path.of(System.getProperty("user.dir"),
                "HomeWork016", "testData");
        if (!Files.exists(basicPath))
            throw new IllegalStateException("Test data directory not found");
    }
//
//    public String[] readAllLines() {
//        Path filePath = Path.of(basicPath.toString(), "lines.txt");
//
//
//        String[] lines = readFile(filePath);
//        if (lines == null) throw new RuntimeException("Error read file");
//        return lines;
//    }

    public String[] readAllLines(String filename) {
        Path filePath = Path.of(basicPath.toString(), filename);

        String[] lines = readFile(filePath);
        if (lines == null) throw new RuntimeException("Error read file");
        return lines;
    }

    String[] readFile(Path filePath) {
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String[] lines;
            ArrayList<String> list = new ArrayList<>();
            while (br.ready()) list.add(br.readLine().replaceAll("\\s+", "\t"));
            int amt = list.size();
            lines = new String[amt];
            return list.toArray(lines);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
