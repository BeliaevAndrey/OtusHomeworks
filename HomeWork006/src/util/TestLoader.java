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
    HashMap<Integer, Integer[]> tests_in = new HashMap<>();
    HashMap<Integer, String> tests_out = new HashMap<>();

    public static void main(String[] args) {
        String basicPath = "/large/data2/Home/Andrew/Documents/OTUS/Algorithms/006_20230828/sorting-tests/0.random/";
        TestLoader tl = new TestLoader(basicPath);
        tl.loadTests();
        tl.printContents();
    }

    public TestLoader(String path) {
        this.path = Path.of(path);
    }

    void printContents() {
        for (int key: tests_in.keySet()) {
            System.out.println(key);
            System.out.println(Arrays.toString(tests_in.get(key)));
            System.out.println(tests_out.get(key));
        }
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
                Integer[] numArrayI = new Integer[numArrayS.length];
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
