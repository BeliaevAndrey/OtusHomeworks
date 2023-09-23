package sortings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class ExternalSortTwoFiles {
    int mergeCalls = 0;

    String dataPath = "HomeWork008/dataES2";


    public static void main(String[] args) {
        ExternalSortTwoFiles esb = new ExternalSortTwoFiles();
        String srcFilename = "randFile.txt";
        String bufferA = "bufferA.txt";
        String bufferB = "bufferB.txt";

        if (!esb.fileCheck(srcFilename)) {
            int N = (int) 1e3;
            int T = 10000;
            esb.functionNT(N, T);
        }
        esb.distribution(bufferA, bufferB, srcFilename);
    }

    void distribution(String leftFilename, String rightFilename, String fileSrc) {

        Path source = Path.of(dataPath, fileSrc);
        Path left = Path.of(dataPath, leftFilename);
        Path right = Path.of(dataPath, rightFilename);

        try (BufferedReader br = Files.newBufferedReader(source);
             BufferedWriter bwLft = Files.newBufferedWriter(left);
             BufferedWriter bwRgt = Files.newBufferedWriter(right)
        ) {
            int fileSw = 0;
            String line = br.readLine();
            int flag = Integer.parseInt(line);
            BufferedWriter bw = bwLft;
            int last = Integer.MIN_VALUE;
            while ((line = br.readLine()) != null) {
                int num = Integer.parseInt(line);
                if (last > num) {
                    fileSw = (fileSw + 1) % 2;
                    bw = fileSw == 0 ? bwLft : bwRgt;
                }
                if (flag >= num) {
                    last = num;
                    bw.append(String.valueOf(num)).append("\n");
                    bw.flush();
                } else {
                    bw.append(String.valueOf(flag)).append("\n");
                    bw.flush();

                    last = flag;
                    flag = num;
                }
            }
            bw.append(String.valueOf(flag)).append("\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (mergeCalls > 2100) {
            System.out.printf("%d TOO MUCH merge recalls", mergeCalls);
            return;
        }

        mergeFiles(leftFilename, rightFilename);
    }

    void mergeFiles(String leftFilename, String rightFilename) {
        mergeCalls++;
        Path dest = Path.of(dataPath, "bufferC.txt");
        Path left = Path.of(dataPath, leftFilename);
        Path right = Path.of(dataPath, rightFilename);

        try {
            if (Files.size(left) < 2 || Files.size(right) < 2) {
                writeResults();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try (BufferedWriter bw = Files.newBufferedWriter(dest);
             BufferedReader brLft = Files.newBufferedReader(left);
             BufferedReader brRgt = Files.newBufferedReader(right)
        ) {
            int srcSw = 0;
            BufferedReader brCurrent = brLft;
            String line = brCurrent.readLine();
            int flag = Integer.parseInt(line);
            while ((line = brCurrent.readLine()) != null) {
//                System.out.println(line);
                int num = Integer.parseInt(line);
                if (num <= flag) {
                    bw.append(String.valueOf(num)).append("\n");
                } else {
                    bw.append(String.valueOf(flag)).append("\n");
                    flag = num;
                    srcSw = (srcSw + 1) % 2;
                    brCurrent = srcSw == 0 ? brLft : brRgt;
                }
                bw.flush();
            }
            bw.append(String.valueOf(flag)).append("\n");
            bw.flush();

            while ((line = brRgt.readLine()) != null) {
                bw.append(line).append("\n");
                bw.flush();
            }

            while ((line = brLft.readLine()) != null) {
                bw.append(line).append("\n");
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        distribution(leftFilename, rightFilename, "bufferC.txt");
    }

    void writeResults(boolean keepBuffers) {
        Path rstSource = Path.of(dataPath, "bufferC.txt");
        Path rstDest = Path.of(dataPath, "results.txt");

        try {
            Files.deleteIfExists(rstDest);
            Files.move(rstSource, rstDest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (keepBuffers) return;

        try {
            Files.deleteIfExists(Path.of(dataPath, "bufferA.txt"));
            Files.deleteIfExists(Path.of(dataPath, "bufferB.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeResults() {
        writeResults(false);
    }


    void functionNT(int N, int T) {
        try (BufferedWriter bw = new BufferedWriter(
                Files.newBufferedWriter(
                        Path.of(dataPath, "randFile.txt")))
        ) {
            Random rnd = new Random();
            for (int i = 0; i < N; i++) {
                bw.append(String.valueOf(rnd.nextInt(T))).append("\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    boolean fileCheck(String fileName) {
        return Files.exists(Path.of(dataPath, fileName));
    }

}
