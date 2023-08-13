import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class testFibonacci {

    static final String directory = "HomeWork003/testData/4.Fibo";
    static final String outPathFile = "HomeWork003/testsResults/4.Fibonacci.csv";

    public static void main(String[] args) {
        testFibonacci tf = new testFibonacci();
        tf.runTests(tf.loadTests(directory));

    }

    void runTests(ArrayList<double[]> tests){
        ArrayList<HashMap<String, double[]>> testsResults = new ArrayList<>();
        Fibonacci fib = new Fibonacci();
        String template = "%15f %20e  || " +
                "%20f %10.3e %10.2e |" +
                "%20f %10.3e %10.2e |" +
                "%20f %10.3e %10.2e |" +
                "%20f %10.3e %10.2e" +
                "\n";
        System.out.printf("%15s %20s  || " +
                        "%20s %10s %10s |" +
                        "%20s %10s %10s |" +
                        "%20s %10s %10s |" +
                        "%20s %10s %10s" +
                        "\n",
                "Position", "Answer",
                "Golden Section", "Time", "Delta",
                "Matrix pwr", "Time", "Delta",
                "Matrix pwr mlt", "Time", "Delta",
                "Iterations", "Time", "Delta"
        );
        for (double[] test : tests) {
            double start0 = System.nanoTime();
            double result0 = fib.goldenSectionFib((long) test[0]);
            double end0 = (System.nanoTime() - start0) / 1e9;
            double delta0 = result0 - test[1];

            double start1 = System.nanoTime();
            double result1 = fib.matMulFibBin((int) test[0]);
            double end1 = (System.nanoTime() - start1) / 1e9;
            double delta1 = result1 - test[1];

            double start2 = System.nanoTime();
            double result2 = fib.matMulFib((int) test[0]);
            double end2 = (System.nanoTime() - start2) / 1e9;
            double delta2 = result1 - test[1];

            double start3 = System.nanoTime();
            double result3 = fib.iterFibonacci((int) test[0]);
            double end3 = (System.nanoTime() - start3) / 1e9;
            double delta3 = result3 - test[1];

            System.out.printf(template,
                    test[0], test[1],
                    result0, end0, delta0,
                    result1, end1, delta1,
                    result2, end2, delta2,
                    result3, end3, delta3);

            testsResults.add(new HashMap<>() {{
                put("testData", test);
                put("Golden Section", new double[]{result0, end0, delta0});
                put("Matrix pwr",  new double[]{result1, end1, delta1});
                put("Matrix pwr mlt",  new double[]{result2, end2, delta2});
                put("Iterations",  new double[]{result3, end3, delta3});
            }});
        }
        writeResultsToFile(testsResults);
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
        String headLine = "Position,Answer,"+
                "Golden Section,Time,Delta,"+
                "Matrix pwr.,Time,Delta,"+
                "Matrix pwr. mlt.,Time,Delta," +
                "Iterations,Time,Delta\n";
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(outPathFile), StandardOpenOption.CREATE)) {
            bw.write(headLine);
            StringBuilder sb = new StringBuilder();
            for (HashMap<String, double[]> results : testsResults) {
                for (double rst : results.get("testData")) {
                    sb.append(rst).append(",");
                }
                for (double rst : results.get("Matrix pwr")) {
                    sb.append(rst).append(",");
                }
                for (double rst : results.get("Matrix pwr mlt")) {
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
}





