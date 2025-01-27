package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class ReadFile {

    private final Path basicPath;

    private final HashMap<Integer, Path[]> tests;

    public static void main(String[] args) {
        ReadFile rf = new ReadFile("peas");
        int n = rf.getTestAmt() - 1;
        for (;n != 0; n--) {
            String[] s = rf.getTest(n);
            System.out.printf("%s %s\n\n", s[0], s[1]);
        }
    }


    public ReadFile(String task_name) {
        basicPath = Path.of(System.getProperty("user.dir"), "HomeWork030", "TestData", task_name);
        if (!Files.isDirectory(basicPath)) throw new IllegalStateException("Wrong basic path");
        tests = new HashMap<>();
        enlistTests();
    }

    private void enlistTests() {
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(basicPath)) {
            ds.forEach(p -> {
                if (p.toString().endsWith(".in") || p.toString().endsWith(".out")) {
                    int test_num;
                    try {
                        test_num = Integer.parseInt(p.getFileName().toString().split("\\.")[1]);
                        if (!tests.containsKey(test_num)) tests.put(test_num, new Path[2]);
                        if (p.toString().endsWith("in")) tests.get(test_num)[0] = p;
                        if (p.toString().endsWith("out")) tests.get(test_num)[1] = p;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String[] getTest(int testNum) {

            Path[] testPath = tests.get(testNum);
            String[] testData = new String[2];

            try (BufferedReader br = new BufferedReader(new FileReader(testPath[0].toString()))) {
                testData[0] = br.readLine();
            } catch (IOException e) { e.printStackTrace(); }

            try (BufferedReader br = new BufferedReader(new FileReader(testPath[1].toString()))) {
                testData[1] = br.readLine();
            } catch (IOException e) { e.printStackTrace(); }

            return testData;
    }

    public int getTestAmt() { return tests.size(); }
}
