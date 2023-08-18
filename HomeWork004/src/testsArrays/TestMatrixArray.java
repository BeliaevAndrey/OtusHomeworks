package testsArrays;

import model.IArray;
import model.MatrixArray;
import model.VectorArray;

import java.util.Date;

public class TestMatrixArray {

    public static void main(String[] args) {
        new TestMatrixArray().testSingleArray();
    }

    void testSingleArray() {
        IArray<String> matrixArray = new MatrixArray<>();
        testAddArray(matrixArray, 100_000);

        System.out.println("\nremove testing");
        testDelPosArray(matrixArray, 49999);
        testDelPosArray(matrixArray, 0);
        testDelPosArray(matrixArray, matrixArray.size() - 1);
        testDelPosArray(matrixArray, 50000);
        testDelPosArray(matrixArray, 50000);
        testDelPosArray(matrixArray, 50000);
        System.out.println("\nremove checking " + matrixArray.size());
        for (int i = 49998; i < 50002; i++) {
            System.out.println(i + " " + matrixArray.get(i));
        }
        System.out.println("head " + matrixArray.get(0));
        System.out.println("tail " + matrixArray.get(matrixArray.size() - 1));
    }

    private void testAddArray(IArray<String> data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 0; j < total; j ++)
            data.add(j + ". " + new Date());

        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start) + "ms");
    }


    private void testAddPosArray(IArray<String> data, int position, String insert) {
        long start = System.currentTimeMillis();

        data.add(insert, position);
        System.out.println(data + " testAddPosArray: " +
                (System.currentTimeMillis() - start) + "ms");
    }

    private void testDelPosArray(IArray<String> data, int position) {
        long start = System.currentTimeMillis();

        Object removed = data.remove(position);
        System.out.println(data + " testDelPosArray: " +
                (System.currentTimeMillis() - start) + " ms; removed: " + removed);
    }
}
