import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class testIsPrime {

    static final String directory = "HomeWork003/testData/5.Primes";
    static final String outPathFile = "HomeWork003/testsResults/5.Primes.csv";

    public static void main(String[] args) {
        testIsPrime testPrimes = new testIsPrime();
        testPrimes.runTests(testPrimes.loadTests(directory));

    }

    void runTests(ArrayList<double[]> tests) {
        ArrayList<HashMap<String, double[]>> testsResults = new ArrayList<>();
        IsPrime iPr = new IsPrime();
        String template = "%18.9e %12.0f || " +
                "%20.0f %10.3e %10.2e |" +
                "%20.0f %10.3e %10.2e |" +
                "%20.0f %10.3e %10.2e |" +
                "%20.0f %10.3e %10.2e" +
                "\n";
        System.out.printf("%18s %12s || " +
                        "%20s %10s %10s |" +
                        "%20s %10s %10s |" +
                        "%20s %10s %10s |" +
                        "%20s %10s %10s" +
                        "\n",
                "Number", "Answer",
                "Iter. Count O(N^0.5)", "Time", "Delta",
                "Eratosthenes", "Time", "Delta",
                "Eratosthenes O(N)", "Time", "Delta",
                "Iterations", "Time", "Delta"
        );
        for (double[] test : tests) {
            double start0 = System.nanoTime();
            double result0 = iPr.countPrimesOSqtrN((long) test[0]);
            double end0 = (System.nanoTime() - start0) / 1e9;
            double delta0 = result0 - test[1];

            double start1 = System.nanoTime();
            double result1 = iPr.eratosthenes((long) test[0]);
            double end1 = (System.nanoTime() - start1) / 1e9;
            double delta1 = result1 - test[1];

            double start2 = System.nanoTime();
            double result2 = iPr.eratosthenesON((long) test[0]);
            double end2 = (System.nanoTime() - start2) / 1e9;
            double delta2 = result1 - test[1];

            double result3;
            double end3;
            double delta3;

            if (test[0] < 2e6) {
                double start3 = System.nanoTime();
                result3 = iPr.countPrimes((long) test[0]);
                end3 = (System.nanoTime() - start3) / 1e9;
                delta3 = result3 - test[1];
            } else {
                result3 = Double.NaN;
                end3 = Double.NaN;
                delta3 = Double.NaN;
            }
            System.out.printf(template,
                    test[0], test[1],
                    result0, end0, delta0,
                    result1, end1, delta1,
                    result2, end2, delta2,
                    result3, end3, delta3);

            resultsToFileOneByOne(new HashMap<>() {{
                put("testData", new double[]{test[0], test[1]});
                put("countPrimes O(N^0.5)", new double[]{result0, end0, delta0});
                put("eratosthenes", new double[]{result1, end1, delta1});
                put("eratosthenes O(N)", new double[]{result2, end2, delta2});
                put("Iterations", new double[]{result3, end3, delta3});
            }});

        }
//        writeResultsToFile(testsResults);     // TODO: uncomment later
//        testsResults.forEach(x -> {
//                    x.keySet().forEach(y -> {
//                        System.out.print(y + ": ");
//                        System.out.println(Arrays.toString(x.get(y)));
//                        System.out.println("=".repeat(80));
//                    });
//                }
//        );
    }

    ArrayList<double[]> loadTests(String path) {
        ArrayList<Path> fileNames = new ArrayList<>();
        ArrayList<double[]> tests = new ArrayList<>();

        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Path.of(path))) {
            dirStream.forEach(file -> {
                if (file.toString().endsWith("in"))
                    fileNames.add(file);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileNames.sort((x, y) -> {
            int a = Integer.parseInt(x.toString().split("\\.")[2]);
            int b = Integer.parseInt(y.toString().split("\\.")[2]);
            return a - b;
        });
        for (Path file : fileNames) {
            tests.add(readDoublesFromFile(file.toString()));
        }
        return tests;
    }

    double[] readDoublesFromFile(String fileName) {
        double[] values = new double[3];
        try (BufferedReader brIn = new BufferedReader(new FileReader(fileName));
             BufferedReader brOut = new BufferedReader(
                     new FileReader(fileName.replaceAll("in", "out")))) {
            String line;
            int count = 0;
            while ((line = brIn.readLine()) != null) {
                values[count] = Double.parseDouble(line);
                count += 1;
            }
            values[count] = Double.parseDouble(brOut.readLine());
            return values;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    void writeResultsToFile(ArrayList<HashMap<String, double[]>> testsResults) {
        String headLine = "Number,Answer," +
                "countPrimes O(N^0.5),Time,Delta," +
                "eratosthenes,Time,Delta," +
                "eratosthenes O(N), mlt.,Time," +
                "Iterations,Time,Delta\n";
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(outPathFile),
                StandardOpenOption.CREATE)) {
            bw.write(headLine);
            StringBuilder sb = new StringBuilder();
            for (HashMap<String, double[]> results : testsResults) {
                for (double rst : results.get("testData")) {
                    sb.append(rst).append(",");
                }
                for (double rst : results.get("countPrimes O(N^0.5)")) {
                    sb.append(rst).append(",");
                }
                for (double rst : results.get("eratosthenes")) {
                    sb.append(rst).append(",");
                }
                for (double rst : results.get("eratosthenes O(N)")) {
                    sb.append(rst).append(",");
                }
                for (double rst : results.get("Iterations")) {
                    sb.append(rst).append(",");
                }
                sb.append("\n");
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void resultsToFileOneByOne(HashMap<String, double[]> testsResults) {
//        String headLine = "Number,Answer," +
//                "countPrimes O(N^0.5),Time,Delta," +
//                "eratosthenes,Time,Delta," +
//                "eratosthenes O(N),Time,Delta," +
//                "Iterations,Time,Delta\n";
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(outPathFile),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
//            bw.write(headLine);
            StringBuilder sb = new StringBuilder();

            for (double rst : testsResults.get("testData")) {
                sb.append(rst).append(",");
            }
            for (double rst : testsResults.get("countPrimes O(N^0.5)")) {
                sb.append(rst).append(",");
            }
            for (double rst : testsResults.get("eratosthenes")) {
                sb.append(rst).append(",");
            }
            for (double rst : testsResults.get("eratosthenes O(N)")) {
                sb.append(rst).append(",");
            }
            for (double rst : testsResults.get("Iterations")) {
                sb.append(rst).append(",");
            }
            sb.replace(sb.length() - 1, sb.length(), "\n");


            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
