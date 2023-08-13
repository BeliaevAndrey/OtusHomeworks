import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class testPowering {

    static final String directory = "HomeWork003/testData/3.Power";
    static final String outDirectory = "HomeWork003/testsResults/3.Power.csv";


    public static void main(String[] args) {
        testPowering tp = new testPowering();
        tp.test(tp.loadTests(directory));

    }

    void test(ArrayList<double[]> tests) {
        ArrayList<HashMap<String, double[]>> testsResults = new ArrayList<>();

        Powering pwr = new Powering();
        String template = "%25.12f %12.0f %20.12f || " +
                "%18.11f %10.3e %8.3e | " +
                "%18.11f %10.3e %8.3e | " +
                "%18.11f %10.3e %8.3e |\n";
        System.out.printf("%25s %12s %20s || " +
                        "%18s %10s %9s | " +
                        "%18s %10s %9s | " +
                        "%18s %10s %9s |\n",
                "Number", "Power", "Answer",
                "Binary recursive", "time", "delta",
                "Binary iter. mlt.", "time", "delta",
                "Iterative", "time", "delta"
        );
        for (double[] test : tests) {

            double start0 = System.nanoTime();
            double result0 = (long) (pwr.getDegreeB(test[0], (long) test[1]) * 1e11) / 1e11;
            double end0 = (System.nanoTime() - start0) / 1e9;
            double delta0 = Math.abs(result0 - test[2]);

            double start2 = System.nanoTime();
            double result2 = pwr.powerIterMult(test[0], (long) test[1]);
            double end2 = (System.nanoTime() - start2) / 1e9;
            double delta2 = Math.abs(result2 - test[2]);

            double start3 = System.nanoTime();
            double result3 = pwr.getDegreeIter(test[0], (long) test[1]);
            double end3 = (System.nanoTime() - start3) / 1e9;
            double delta3 = Math.abs(result3 - test[2]);

            System.out.printf(template,
                    test[0], test[1], test[2],
                    result0, end0, delta0,
                    result2, end2, delta2,
                    result3, end3, delta3);

            testsResults.add(new HashMap<>() {{
                                 put("testData", test);
                                 put("Binary recursive", new double[]{result0, end0, delta0});
                                 put("Binary iter. mlt.", new double[]{result2, end2, delta2});
                                 put("Iterative", new double[]{result3, end3, delta3});
                             }}
            );

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
        String headLine = "Number;Power;Answer;" +
                "Binary recursive;time;delta;" +
                "Binary iter. mlt.;time;delta;" +
                "Iterative;time;delta;\n";
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(outDirectory), StandardOpenOption.APPEND)) {
            bw.write(headLine);
            StringBuilder sb = new StringBuilder();
            for (HashMap<String, double[]> results : testsResults) {
                for (double rst : results.get("testData")) {
                    sb.append(rst).append(";");
                }
                for (double rst : results.get("Binary recursive")) {
                    sb.append(rst).append(";");
                }
                for (double rst : results.get("Binary iter. mlt.")) {
                    sb.append(rst).append(";");
                }
                for (double rst : results.get("Iterative")) {
                    sb.append(rst).append(";");
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
