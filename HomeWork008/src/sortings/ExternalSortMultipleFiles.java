package sortings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class ExternalSortMultipleFiles {

    int maxValue = Integer.MIN_VALUE;
    int minValue = Integer.MAX_VALUE;

    String dataPath = "HomeWork008/data";


    public static void main(String[] args) {
        ExternalSortMultipleFiles esa = new ExternalSortMultipleFiles();

        int N = 1000;
        int T = 100000;

        if (!esa.checkPath()) return;

        try {
            esa.functionNT(N, T);
            esa.fileReader("randFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("min=%d max=%d", esa.minValue, esa.maxValue);
        try {
            esa.mergeFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void functionNT(int N, int T) throws IOException {
        FileOutputStream fos = new FileOutputStream(
                Path.of(dataPath, "randFile.txt").toString());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        Random rnd = new Random();
        for (int i = 0; i < N; i++) {
            bw.append(String.valueOf(rnd.nextInt(T))).append("\n");
        }
        bw.flush();
        bw.close();
        fos.close();
    }

    void fileReader(String fileName) throws IOException {
        String fileIn = Path.of(dataPath, fileName).toString();
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

    void mergeFiles() throws IOException {
        Path resultFile = Path.of(dataPath, "result.txt");
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

    boolean checkPath() {
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
