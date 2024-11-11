package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class WriteResults {

    Path filePath;

    public WriteResults() {
        filePath = Path.of(System.getProperty("user.dir"),
                "HomeWork016",
                "testResults",
                "testResults.csv");
        checkNCreate(filePath);

    }

    private void checkNCreate(Path path) {
        if (Files.exists(path.getParent())) return;
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResultsTable(String[] results) {
        try (BufferedWriter br = Files.newBufferedWriter(filePath)
        ) {
            for(String line : results) br.append(line).append("\n");
            br.flush();
        } catch (IOException e) { e.printStackTrace(); }
    }

}
