package sortings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExternalSortES2 {

    int mergeCalls = 0;
    String dataPath = "HomeWork008/dataES2";
    String srcFilename;
    String dstFilename;

    public void init(String srcFilename, String dstFilename) {
        this.srcFilename = srcFilename;
        this.dstFilename = dstFilename;
        delBufferFiles();
    }

    public void sort() {
        String bufferA = "bufferA.txt";
        String bufferB = "bufferB.txt";
        distribution(bufferA, bufferB, srcFilename);
    }

    private void distribution(String leftFilename, String rightFilename, String fileSrc) {

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

        mergeFiles(leftFilename, rightFilename);
    }

    private void mergeFiles(String leftFilename, String rightFilename) {
        mergeCalls++;
        if (mergeCalls % 100 == 0) System.out.print(" ES2 " + mergeCalls);
        if (mergeCalls % 800 == 0) System.out.println();
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

    private void writeResults() {
        Path rstSource = Path.of(dataPath, "bufferC.txt");
        Path rstDest = Path.of(dataPath, dstFilename);

        try {
            Files.deleteIfExists(rstDest);
            Files.move(rstSource, rstDest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        delBufferFiles();
    }

    boolean fileCheck(String fileName) {
        return Files.exists(Path.of(dataPath, fileName));
    }


    private void delBufferFiles() {
        try {
            Files.deleteIfExists(Path.of(dataPath, "bufferA.txt"));
            Files.deleteIfExists(Path.of(dataPath, "bufferB.txt"));
            Files.deleteIfExists(Path.of(dataPath, "bufferC.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
