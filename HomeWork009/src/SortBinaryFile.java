import sortings.BucketSortSeminar;
import sortings.CountingSort;
import sortings.RadixSort;
import util.FileWriteSvc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class SortBinaryFile {

    int[] A;
    Path basicPath = Path.of(System.getProperty("user.dir"), "HomeWork009", "Data");
    final String sourceFile = "File1.bin";
    HashMap<String, Double> results;

    public static void main(String[] args) {
        SortBinaryFile sbf = new SortBinaryFile();

        sbf.results = new HashMap<>();

        sbf.readFile(sbf.sourceFile);
        sbf.results.put("Array length", (double) sbf.A.length);
        long start1 = System.nanoTime();
        sbf.arrayBucketSort(sbf.A);
        double end1 = (System.nanoTime() - start1) / 1e9;
        System.out.println("Time spent: " + end1);
        sbf.writeFile("BucketSort_out.bin");
        sbf.readFile("BucketSort_out.bin");
        if (sbf.validate()) sbf.results.put("Bucket Sort", end1);
        else sbf.results.put("Bucket Sort", null);
        System.out.println(sbf.results.get("Bucket Sort"));

        sbf.readFile(sbf.sourceFile);
        long start2 = System.nanoTime();
        sbf.arrayRadixSort(sbf.A);
        double end2 = (System.nanoTime() - start2) / 1e9;
        System.out.println("Time spent: " + end2);
        sbf.writeFile("RadixSort_out.bin");
        sbf.readFile("RadixSort_out.bin");
        if (sbf.validate()) sbf.results.put("Radix Sort", end2);
        else sbf.results.put("Radix Sort", null);
        System.out.println(sbf.results.get("Radix Sort"));

        sbf.readFile(sbf.sourceFile);
        long start3 = System.nanoTime();
        sbf.arrayCountingSort(sbf.A);
        double end3 = (System.nanoTime() - start3) / 1e9;
        System.out.println("Counting Sort Time spent : " + end3);
        sbf.writeFile("CountingSort_out.bin");
        sbf.readFile("CountingSort_out.bin");
        System.out.println(sbf.validate());
        if (sbf.validate()) sbf.results.put("Counting Sort", end3);
        else sbf.results.put("Counting Sort", null);
        System.out.println(sbf.results.get("Counting Sort"));
        sbf.writeResults();
    }

    boolean validate() {
        for (int i = 1; i < A.length; i++)
            if (A[i - 1] > A[i]) return false;
        return true;
    }

    void arrayBucketSort(int[] arr) {
        BucketSortSeminar bs = new BucketSortSeminar();
        bs.init(arr);
        bs.Bucket();
    }

    void arrayRadixSort(int[] arr) {
        RadixSort rs = new RadixSort();
        rs.init(arr);
        rs.sort();
    }

    void arrayCountingSort(int[] arr) {
        CountingSort cs = new CountingSort();
        cs.init(arr);
        cs.sort();
    }

    void writeFile(String filename) {
        Path filePath = Path.of(basicPath.toString(), filename);

        try (FileOutputStream fos = new FileOutputStream(filePath.toString());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(bos)
        ) {
            for (int j : A) dos.writeShort(j);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void readFile(String filename) {
        Path filePath = Path.of(basicPath.toString(), filename);
        System.out.println(filePath);

        try (FileInputStream fis = new FileInputStream(filePath.toString());
             BufferedInputStream bis = new BufferedInputStream(fis);
             DataInputStream dis = new DataInputStream(bis);
        ) {
            long size = Files.size(filePath) / 2;
            A = new int[(int) size];
            int j = 0;
            while (dis.available() > 0) {
                int num = dis.readShort() & 65535;
                A[j] = num;
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeResults() {
        Path resultFile = Path.of(
                basicPath.toString().replace("Data", "SortBinaryFileResults"),
                "SortBinaryFileResults.csv");

        StringBuilder sb = new StringBuilder();

        results.keySet().stream().sorted().forEach(header -> sb.append(header).append(","));
        sb.replace(sb.length() - 1, sb.length(), "\n");

        results.keySet().stream().sorted().forEach(header -> {
            if (header.equals("Array length")) sb.append(String.format("%s,", results.get(header)));
            else sb.append(results.get(header)).append(",");
        });
        sb.replace(sb.length() - 1, sb.length(), "\n");

        try (BufferedWriter bw = Files.newBufferedWriter(resultFile)) {
            bw.append(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
