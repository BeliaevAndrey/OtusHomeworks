package sortings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class ExternalSortES1 {

    int maxValue = Integer.MIN_VALUE;
    int minValue = Integer.MAX_VALUE;

    String dataPath = "HomeWork008/dataES1";
    String srcFilename;
    String dstFilename;

    public void init(String srcFilename, String dstFilename) {
        this.srcFilename = srcFilename;
        this.dstFilename = dstFilename;
    }

    public void sort() {
        try {
            numbersSeparation();
            mergeFiles();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private void numbersSeparation() throws IOException {
        String fileIn = Path.of(dataPath, srcFilename).toString();
        FileInputStream fis = new FileInputStream(fileIn);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) {
            int number = Integer.parseInt(line);
            maxValue = maxValue < number ? number : maxValue;
            minValue = minValue > number ? number : minValue;
            Path newFileName = Path.of(dataPath, number + ".txt");
            try (BufferedWriter bw = Files.newBufferedWriter(
                    newFileName,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                bw.append(String.valueOf(number)).append("\n").flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fis.close();
        br.close();
    }

    private void mergeFiles() throws IOException {
        Path resultFile = Path.of(dataPath, dstFilename);
        FileOutputStream fos = new FileOutputStream(resultFile.toString());
        for (int i = minValue; i <= maxValue; i++) {
            Path fileName = Path.of(dataPath, i + ".txt");
            if (Files.exists(fileName)) {
                try (FileInputStream fis = new FileInputStream(fileName.toString())) {
                    fos.write(fis.readAllBytes());
                }
            }
            Files.deleteIfExists(fileName);
        }
        fos.close();
    }

    private boolean checkPath() {
        if (Files.exists(Path.of(dataPath)))
            return true;
        try {
            Files.createDirectory(Path.of(dataPath));
        } catch (IOException exc) {
            System.out.printf("%s\n", exc.fillInStackTrace());
            return false;
        }
        return true;
    }
}
