package util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class FunctionNT {

    String dataPath;
    String fileName;

    public void init(String dataPath, String fileName) {
        this.dataPath = dataPath;
        this.fileName = fileName;
        if (!checkPath()) System.out.println("Check paths, filenames and then re-init.");
    }

    public void functionNT(int N, int T) {
        try (BufferedWriter bw = new BufferedWriter(
                Files.newBufferedWriter(
                        Path.of(dataPath, fileName)))
        ) {
            Random rnd = new Random();
            for (int i = 0; i < N; i++) {
                bw.append(String.valueOf(rnd.nextInt(T + 1))).append("\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean checkPath() {
        if (dataPath == null) return false;
        Path dataDir = Path.of(dataPath);
        if (Files.isDirectory(dataDir)) return true;
        try {
            Files.createDirectory(dataDir);
            System.out.printf("Created: '%s' directory\n", dataDir);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
