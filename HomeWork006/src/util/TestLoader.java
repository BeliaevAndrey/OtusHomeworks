package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class TestLoader {

    Path path;

    public HashMap<Integer, int[]> tests_in = new HashMap<>();
    public HashMap<Integer, String> tests_out = new HashMap<>();

    private final String basicPath = "/large/data2/Home/Andrew/Documents/OTUS/Algorithms/006_20230828/sorting-tests/";


    public TestLoader(String path, String testPath) {
        this.path = Path.of(path, testPath);
        loadTests();
    }

    public TestLoader(String testPath) {
        this.path = Path.of(this.basicPath, testPath);
        loadTests();
    }

    void printContents() {
        tests_in.keySet().stream().sorted().forEach(key -> {
            System.out.println(key);
            if (key < 1000) {
                StringBuilder sb = new StringBuilder();
                Arrays.stream(tests_in.get(key)).sorted().forEach(num -> sb.append(num).append(" "));

                sb.replace(sb.length() - 1, sb.length(), "");
                System.out.println("rst: " + sb.toString() + " out->" + tests_out.get(key));
                System.out.println(sb.toString().equals(tests_out.get(key)));
            }
        });
    }

    void loadTests() {
        ArrayList<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
            ds.forEach(fn -> {
                if (fn.toString().endsWith(".in")) files.add(fn);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(files);

        files.forEach(fn -> {
            try (BufferedReader brIn = Files.newBufferedReader(fn);
                 BufferedReader brOut = Files.newBufferedReader(
                         Path.of(fn.toString().replaceAll("\\.in", ".out")))
            ) {
                int test_size = Integer.parseInt(brIn.readLine());
                String[] numArrayS = brIn.readLine().split(" ");
                int[] numArrayI = new int[numArrayS.length];
                for (int i = 0; i < numArrayS.length; i++) {
                    numArrayI[i] = Integer.parseInt(numArrayS[i]);
                }
                this.tests_in.put(test_size, numArrayI);
                this.tests_out.put(test_size, brOut.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
