import sortings.ExternalSortES1;
import sortings.ExternalSortES2;
import sortings.ExternalSortES3;
import util.FileWriteSvc;
import util.FunctionNT;
import util.ViewSvc;

import java.io.IOException;
import java.util.HashMap;


public class ESSimpleTests {

    private HashMap<String, HashMap<String, Double>> results;

    public static void main(String[] args) {
        ESSimpleTests ess = new ESSimpleTests();
        ess.results = new HashMap<>();

        for (int N = 100; N <= 1e6; N *= 10)
            for (int T = 10; T <= N; T += N - 10)
                ess.results.put(String.format("(%d, %d)", N, T), new HashMap<>());

        ess.checkES1();
        ess.checkES3();
        ess.checkES2();
        FileWriteSvc.writeHashMap(ess.results, "ExternalSortsTestResults.csv");
    }

    void checkES1() {
        String dataPath = "HomeWork008/dataES1";
        HashMap<String, String> files = new HashMap<>();
        ExternalSortES1 sortES1 = new ExternalSortES1();

        for (int N = 100; N <= 1e5; N *= 10) {
            for (int T = 10; T <= N; T += N - 10) {
                FunctionNT fnt = new FunctionNT();
                String fileName = String.format("srcES1_N_%07d_T_%07d.txt", N, T);
                fnt.init(dataPath, fileName);
                fnt.functionNT(N, T);
                files.put(String.format("(%d, %d)", N, T), fileName);
            }
        }

        files.keySet().stream().sorted().forEach(key -> {
            System.out.println("\nES1: " + key);
            String fileName = files.get(key);
            String outFile = fileName.replace("src", "rst");
            sortES1.init(fileName, outFile);
            long start = System.nanoTime();
            sortES1.sort();
            double end = (System.nanoTime() - start) / 1e9;
            results.get(key).put("ES1", end);
        });

        ViewSvc.printHashMap(results);
    }


    void checkES2() {
        String dataPath = "HomeWork008/dataES2";
        HashMap<String, String> files = new HashMap<>();
        ExternalSortES2 sortES2 = new ExternalSortES2();
        for (int N = 100; N <= 1e5; N *= 10) {      // 'java.lang.StackOverflowError' when upper limit >= 1e5
            for (int T = 10; T <= N; T += N - 10) {
                FunctionNT fnt = new FunctionNT();
                String fileName = String.format("srcES2_N_%07d_T_%07d.txt", N, T);
                fnt.init(dataPath, fileName);
                fnt.functionNT(N, T);
                files.put(String.format("(%d, %d)", N, T), fileName);
            }
        }

        files.keySet().stream().sorted().forEach(key -> {
            System.out.println("\nES2: " + key);
            String fileName = files.get(key);
            String outFile = fileName.replace("src", "rst");
            sortES2.init(fileName, outFile);
            long start = System.nanoTime();
            sortES2.sort();
            double end = (System.nanoTime() - start) / 1e9;
            results.get(key).put("ES2", end);
        });

        System.out.println();
        ViewSvc.printHashMap(results);
    }


    void checkES3() {
        String dataPath = "HomeWork008/dataES3";
        HashMap<String, String> files = new HashMap<>();
        ExternalSortES3 sortES3 = new ExternalSortES3();
        for (int N = 100; N <= 1e6; N *= 10) {
            for (int T = 10; T <= N; T += N - 10) {
                FunctionNT fnt = new FunctionNT();
                String fileName = String.format("srcES3_N_%07d_T_%07d.txt", N, T);
                fnt.init(dataPath, fileName);
                fnt.functionNT(N, T);
                files.put(String.format("(%d, %d)", N, T), fileName);
            }
        }

        files.keySet().stream().sorted().forEach(key -> {
            System.out.println("\nES3: " + key);
            String fileName = files.get(key);
            String outFile = fileName.replace("src", "rst");
            sortES3.init(fileName, outFile);
            long start = System.nanoTime();
            sortES3.sort();
            double end = (System.nanoTime() - start) / 1e9;
            results.get(key).put("ES3", end);
        });
        ViewSvc.printHashMap(results);
    }

    /*Utility*/
    void pseudoPause() {
        System.out.print("Press enter...");
        try {
            System.in.read();
        } catch (IOException e) {/*skip*/}
    }

}
