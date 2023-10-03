package sortings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;


public class ExternalSortES3 {

    int buffLen = 100;  // pre-sort buffer length

    String dataPath = "HomeWork008/dataES3";
    String srcFilename;

    public void init(String filename) {
        srcFilename = filename;
        int N = (int) 5e3;
        int T = 10000;
        if (!fileCheck(srcFilename)) {
            functionNT(N, T);
//            System.out.println("File not found!");
//            return;
        }

    }

    public void sort() {
        QuickSort qs = new QuickSort();
        MergeSort ms = new MergeSort();
        String buffer = "bufferC.txt";
        preSortAndDistribute(srcFilename, buffer, qs);
    }


    private void preSortAndDistribute(String fileSrc, String fileDst, ISorter sorter) {

        Path source = Path.of(dataPath, fileSrc);
        Path lftDst = Path.of(dataPath, "lft_" + fileDst);
        Path rgtDst = Path.of(dataPath, "rgt_" + fileDst);

        try (BufferedReader br = Files.newBufferedReader(source);
             BufferedWriter bwLft = Files.newBufferedWriter(lftDst, StandardOpenOption.CREATE); //, StandardOpenOption.APPEND);
             BufferedWriter bwRgt = Files.newBufferedWriter(rgtDst, StandardOpenOption.CREATE) //, StandardOpenOption.APPEND);
        ) {
            int fileSw = 0;
            BufferedWriter bw = bwLft;

            int[] arrIn = new int[buffLen];
            while (br.ready()) {
                for (int i = 0; i < buffLen; i++) {
                    arrIn[i] = Integer.parseInt(br.readLine());
                }
                sorter.init(arrIn);
                sorter.sort();
                for (int e : arrIn) {
                    bw.append(String.valueOf(e)).append("\n");
                }
                bw.flush();
                fileSw = (fileSw + 1) % 2;
                bw = fileSw == 0 ? bwLft : bwRgt;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        mergeFiles("lft_" + fileDst, "rgt_" + fileDst, "bufferA.txt", "bufferB.txt");
    }

    private void mergeFiles(String leftFileIn, String rightFileIn, String leftFileOut, String rightFileOut) {

        Path lftSrc = Path.of(dataPath, leftFileIn);
        Path rgtSrc = Path.of(dataPath, rightFileIn);

        Path lftDst = Path.of(dataPath, leftFileOut);
        Path rgtDst = Path.of(dataPath, rightFileOut);

        try {
            if (Files.size(lftSrc) < 2) {
                writeResults(rgtSrc);
                return;
            }
            if (Files.size(rgtSrc) < 2) {
                writeResults(lftSrc);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try (BufferedWriter bwLft = Files.newBufferedWriter(lftDst);
             BufferedWriter bwRgt = Files.newBufferedWriter(rgtDst);
             BufferedReader brLft = Files.newBufferedReader(lftSrc);
             BufferedReader brRgt = Files.newBufferedReader(rgtSrc)
        ) {
            int srcSw = 0;
            int dstSw = 0;

            BufferedReader br = brLft;
            BufferedWriter bw = bwLft;

            // pre-distribution
            int flag = Integer.parseInt(br.readLine());
            if (!br.ready()) srcSw = (srcSw + 1) % 2;
            br = srcSw == 0 ? brLft : brRgt;
            int num = Integer.parseInt(br.readLine());
            int last = num;

            if (num <= flag) {
                bw.append(String.valueOf(num)).append("\n");
            } else {
                bw.append(String.valueOf(flag)).append("\n");
                last = flag;
                flag = num;
                srcSw = (srcSw + 1) % 2;
                br = brRgt;
            }
            bw.flush();

            // main sort-and-merge cycle
            while (br.ready()) {
                num = Integer.parseInt(br.readLine());
                if (num < last) {
                    dstSw = (dstSw + 1) % 2;
                    bw = dstSw == 0 ? bwLft : bwRgt;
                }
                if (num <= flag) {
                    bw.append(String.valueOf(num)).append("\n");
                    last = num;
                } else {
                    bw.append(String.valueOf(flag)).append("\n");
                    flag = num;
                    srcSw = (srcSw + 1) % 2;
                    br = srcSw == 0 ? brLft : brRgt;
                }
                bw.flush();
            }
            bw.append(String.valueOf(flag)).append("\n");
            bw.flush();

            srcSw = (srcSw + 1) % 2;
            br = srcSw == 0 ? brLft : brRgt;

            while (br.ready()) {
                num = Integer.parseInt(br.readLine());
                if (num < last) {
                    dstSw = (dstSw + 1) % 2;
                    bw = dstSw == 0 ? bwLft : bwRgt;
                }
                bw.append(String.valueOf(num)).append("\n");
                last = num;
                bw.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        mergeFiles(leftFileOut, rightFileOut, leftFileIn, rightFileIn);
    }

    private void writeResults(Path rstSource) {
        Path rstDest = Path.of(dataPath, "results.txt");

        try {
            Files.deleteIfExists(rstDest);
            Files.copy(rstSource, rstDest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.deleteIfExists(Path.of(dataPath, "bufferA.txt"));
            Files.deleteIfExists(Path.of(dataPath, "bufferB.txt"));
            Files.deleteIfExists(Path.of(dataPath, "lft_bufferC.txt"));
            Files.deleteIfExists(Path.of(dataPath, "rgt_bufferC.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private boolean fileCheck(String fileName) {
        return Files.exists(Path.of(dataPath, fileName));
    }


    /*Utility section*/

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

    void functionNTLinearMixed(int N) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < N; i++) {
            int k = i + rnd.nextInt(N - i);
            int x = arr[i];
            arr[i] = arr[k];
            arr[k] = x;
            sb.append(arr[i]).append(i < N - 1 ? "\n" : "");
        }
        try (BufferedWriter bw = new BufferedWriter(
                Files.newBufferedWriter(
                        Path.of(dataPath, "randFile.txt")))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
