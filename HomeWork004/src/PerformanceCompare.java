import model.*;
import testsArrays.WrapperOverArrayList;
import testsArrays.WriteToFile;

import java.util.HashMap;

public class PerformanceCompare {
    static HashMap<String, Double[]> resultsAdd = new HashMap<>();
    static HashMap<String, Double[]> resultsAddTo0 = new HashMap<>();
    static HashMap<String, Double[]> resultsRmv = new HashMap<>();

    public static void main(String[] args) {

        PerformanceCompare tester = new PerformanceCompare();

        IArray<String> builtInAL = new WrapperOverArrayList<>();
        IArray<String> singleArray = new SingleArray<>();
        IArray<String> vectorArray = new VectorArray<>();
        IArray<String> factorArray = new FactorArray<>();
        IArray<String> matrixArray = new MatrixArray<>();

        resultsAdd.put("Numbers", new Double[5]);
        resultsAdd.put(builtInAL.getClass().getSimpleName(), new Double[5]);
        resultsAdd.put(singleArray.getClass().getSimpleName(), new Double[5]);
        resultsAdd.put(vectorArray.getClass().getSimpleName(), new Double[5]);
        resultsAdd.put(factorArray.getClass().getSimpleName(), new Double[5]);
        resultsAdd.put(matrixArray.getClass().getSimpleName(), new Double[5]);

        resultsAddTo0.put("Numbers", new Double[5]);
        resultsAddTo0.put(builtInAL.getClass().getSimpleName(), new Double[4]);
        resultsAddTo0.put(singleArray.getClass().getSimpleName(), new Double[4]);
        resultsAddTo0.put(vectorArray.getClass().getSimpleName(), new Double[4]);
        resultsAddTo0.put(factorArray.getClass().getSimpleName(), new Double[4]);
        resultsAddTo0.put(matrixArray.getClass().getSimpleName(), new Double[4]);

        resultsRmv.put("Numbers", new Double[5]);
        resultsRmv.put(builtInAL.getClass().getSimpleName(), new Double[4]);
        resultsRmv.put(singleArray.getClass().getSimpleName(), new Double[4]);
        resultsRmv.put(vectorArray.getClass().getSimpleName(), new Double[4]);
        resultsRmv.put(factorArray.getClass().getSimpleName(), new Double[4]);
        resultsRmv.put(matrixArray.getClass().getSimpleName(), new Double[4]);

        int upperLim = (int) 1e6;

        /*Test add(item)*/
        for (int i = 100; i <= upperLim; i *= 10) {
            if (i > 100) {   /*Clearing arrays*/
                builtInAL = new WrapperOverArrayList<>();
                singleArray = new SingleArray<>();
                vectorArray = new VectorArray<>();
                factorArray = new FactorArray<>();
                matrixArray = new MatrixArray<>();
            }
            resultsAdd.get("Numbers")[(int) Math.log10((double) i / 100)] = (double) i;
            tester.testAdd(builtInAL, i);
            tester.testAdd(singleArray, i);
            tester.testAdd(vectorArray, i);
            tester.testAdd(factorArray, i);
            tester.testAdd(matrixArray, i);

        }
        WriteToFile.writeToFile(resultsAdd, "PerformanceCompareAdd.csv", 5);
        System.out.println("Testing addition to end of array");
        tester.printResults(resultsAdd, 5);


        /*Test add(0) and remove(0) */
        upperLim = (int) 1e5;   // setting shorter limit for arrays
        for (int i = 100; i <= upperLim; i *= 10) {
            {  /*Clearing arrays*/
                builtInAL = new WrapperOverArrayList<>();
                singleArray = new SingleArray<>();
                vectorArray = new VectorArray<>();
                factorArray = new FactorArray<>();
                matrixArray = new MatrixArray<>();
            }

            resultsAddTo0.get("Numbers")[(int) Math.log10((double) i / 100)] = (double) i;
            resultsRmv.get("Numbers")[(int) Math.log10((double) i / 100)] = (double) i;
            tester.testAddTo0(builtInAL, i);
            tester.testRemove(builtInAL);
            tester.testAddTo0(singleArray, i);
            tester.testRemove(singleArray);
            tester.testAddTo0(vectorArray, i);
            tester.testRemove(vectorArray);
            tester.testAddTo0(factorArray, i);
            tester.testRemove(factorArray);
            tester.testAddTo0(matrixArray, i);
            tester.testRemove(matrixArray);
        }
        WriteToFile.writeToFile(resultsAddTo0, "PerformanceCompareAddTo0.csv", 4);
        System.out.println("\nTesting addition to begin of array");
        tester.printResults(resultsAddTo0, 4);
        WriteToFile.writeToFile(resultsRmv, "PerformanceCompareRmv.csv", 4);
        System.out.println("\nTesting removal from begin of array");
        tester.printResults(resultsRmv, 4);


    }

    void testAdd(IArray<String> array, int total) {
        double start = System.nanoTime();
        for (int i = 0; i < total; i++) {
            array.add(String.valueOf(i));
        }
        double end = (System.nanoTime() - start) / 1e9;
        resultsAdd.get(array.getClass().getSimpleName())[(int) Math.log10((double) total / 100)] = end;
    }

    void testAddTo0(IArray<String> array, int total) {
        double start = System.nanoTime();
        for (int i = 0; i < total; i++) {
            array.add(String.valueOf(i), 0);
        }
        double end = (System.nanoTime() - start) / 1e9;
        resultsAddTo0.get(array.getClass().getSimpleName())[(int) Math.log10((double) total / 100)] = end;
    }

    void testRemove(IArray<String> array) {
        int elementsAmt = array.size();
        double start = System.nanoTime();
        while (array.size() > 0){
            array.remove(0);
        }
        double end = (System.nanoTime() - start) / 1e9;
        resultsRmv.get(array.getClass().getSimpleName())[(int) Math.log10((double) elementsAmt / 100)] = end;
    }


    void printResults(HashMap<String, Double[]> results, int recordsAmt) {
        String format = "%-15s %-25s %-15s %-15s %-15s %-15s\n";
        System.out.printf(format,
                "Numbers",
                "WrapperOverArrayList",
                "SingleArray",
                "VectorArray",
                "FactorArray",
                "MatrixArray");
        for (int i = 0; i < recordsAmt; i++) {
            System.out.printf(format,
                    results.get("Numbers")[i],
                    results.get("WrapperOverArrayList")[i],
                    results.get("SingleArray")[i],
                    results.get("VectorArray")[i],
                    results.get("FactorArray")[i],
                    results.get("MatrixArray")[i]
            );
        }
    }
}
