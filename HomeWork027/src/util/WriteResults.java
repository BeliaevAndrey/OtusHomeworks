package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class WriteResults {

    private final Path basicPath;

    String taskName;


    public WriteResults(String taskName) {
        basicPath = Path.of(System.getProperty("user.dir"), "HomeWork027", "TestResults", taskName);
        if (!Files.isDirectory(basicPath)) {
            try {

                Files.createDirectories(basicPath);
                System.out.println("Output directories created: " + basicPath);

            } catch (IOException e) { e.printStackTrace(); }

        }
        this.taskName = taskName;
    }

    public void writeFile(ArrayList<String> lines) {
        String fileOut = taskName + ".txt";
        Path outPath = Path.of(basicPath.toString(), fileOut);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outPath.toString()))) {
            bw.write("Results for task: ");
            bw.write(taskName);
            bw.append('\n');
            for (String s : lines) bw.write(s);
            bw.flush();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
