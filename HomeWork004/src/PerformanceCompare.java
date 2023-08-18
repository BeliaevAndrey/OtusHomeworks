import model.*;
import testsArrays.WriteToFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PerformanceCompare {
    static HashMap<String, Double[]> results = new HashMap<>();

    public static void main(String[] args) {

        PerformanceCompare tester = new PerformanceCompare();
        ArrayList<String> builtInAL = new ArrayList<>();
        IArray<String> singleArray = new SingleArray<>();
        IArray<String> vectorArray = new VectorArray<>();
        IArray<String> factorArray = new FactorArray<>();
        IArray<String> matrixArray = new MatrixArray<>();

        results.put("Numbers", new Double[5]);
        results.put(builtInAL.getClass().getSimpleName(), new Double[5]);
        results.put(singleArray.getClass().getSimpleName(), new Double[5]);
        results.put(vectorArray.getClass().getSimpleName(), new Double[5]);
        results.put(factorArray.getClass().getSimpleName(), new Double[5]);
        results.put(matrixArray.getClass().getSimpleName(), new Double[5]);

        for (int i = 100; i <= 1e6; i *= 10) {
            results.get("Numbers")[(int) Math.log10((double) i / 100)] = (double) i;
            tester.testAdd(builtInAL, i);
            tester.testAdd(singleArray, i);
            tester.testAdd(vectorArray, i);
            tester.testAdd(factorArray, i);
            tester.testAdd(matrixArray, i);
        }
        WriteToFile.writeToFile(results);
        tester.printResults();
    }

    void testAdd(IArray<String> array, int total) {
        double start = System.nanoTime();
        for (int i = 0; i < total; i++) {
            array.add(String.valueOf(i));
        }
        double end = (System.nanoTime() - start) / 1e9;
        results.get(array.getClass().getSimpleName())[(int) Math.log10((double) total / 100)] = end;
    }

    void testAdd(ArrayList<String> array, int total) {
        double start = System.nanoTime();
        for (int i = 0; i < total; i++) {
            array.add(String.valueOf(i));
        }
        double end = (System.nanoTime() - start) / 1e9;
        results.get(array.getClass().getSimpleName())[(int) Math.log10((double) total / 100)] = end;
    }

    void printResults() {
        String format = "%-15s %-20s %-15s %-15s %-15s %-15s\n";
        System.out.printf(format,
                "Numbers",
                "ArrayList",
                "SingleArray",
                "VectorArray",
                "FactorArray",
                "MatrixArray");
        for (int i = 0; i < 5; i++) {
            System.out.printf(format,
                    results.get("Numbers")[i],
                    results.get("ArrayList")[i],
                    results.get("SingleArray")[i],
                    results.get("VectorArray")[i],
                    results.get("FactorArray")[i],
                    results.get("MatrixArray")[i]
            );


        }
    }

}
