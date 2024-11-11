package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FReader {

    public String[] readAllLines(String filename ) {
        String filePath = String.join("/",
                System.getProperty("user.dir"),
                "HomeWork016",
                "testData",
                filename);

        String[] lines = readFile(filePath);
        if (lines == null) throw new RuntimeException("Error read file");
        return lines;
    }

    String[] readFile(String filePath) {
        Path path = Path.of(filePath);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String[] lines;
            ArrayList<String> list = new ArrayList<>();
            while (br.ready()) list.add(br.readLine().replaceAll("\s+", "\t"));
            int amt = list.size();
            lines = new String[amt];
            return list.toArray(lines);

        } catch (IOException e) { e.printStackTrace();}
        return null;
    }
}
