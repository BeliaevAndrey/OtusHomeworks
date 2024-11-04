package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class TestResultWriter {

    private final Path path;
    private final String fileName = "results.csv";


    public void writeResults(ArrayList<String> results) {
        if (path == null) throw new RuntimeException("Uninitialized writer!");

        String header = "Title,position,compares amount,repeats,mean time (s/repeats)\n";
        Path filePath = Path.of(path.toString(), fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE)) {

            bw.append(header);
            for (String line : results) {
                bw.append(line).append('\n');
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TestResultWriter() {
        Path nPath = Path.of(System.getProperty("user.dir"), "HomeWork024", "TestResults");
        if (!Files.isDirectory(nPath))
            try {
                makeDir(nPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        path = nPath;
    }

    private void makeDir(Path path) throws IOException {
        System.out.println("Creating directory(ies): ");
        System.out.println(path);
        Files.createDirectories(path);
    }

}
