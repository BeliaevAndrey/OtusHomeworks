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
        IArray matrixArray = new MatrixArray();
        testAddArray(matrixArray, 38);

//        System.out.println("\nadd testing");
//        testAddPosArray(vectorArray, 1000, "test 1");
//        testAddPosArray(vectorArray, 1000, "test 2");
//        testAddPosArray(vectorArray, vectorArray.size(), "test tail");
//        testAddPosArray(vectorArray, 1000, "test 3");
//        testAddPosArray(vectorArray, 0, "test head");
//        testAddPosArray(vectorArray, 1001, "test 4");
//        System.out.println("\nadd checking");
//        for (int i = 999; i < 1010; i++) {
//            System.out.println(i + " " + vectorArray.get(i));
//        }
//        System.out.println(vectorArray.get(0));
//        System.out.println(vectorArray.get(vectorArray.size() - 1));

        System.out.println("\nremove testing");
//        testDelPosArray(vectorArray, 0);
        testDelPosArray(matrixArray, 10);
        testDelPosArray(matrixArray, matrixArray.size() - 1);
        testDelPosArray(matrixArray, 10);
        testDelPosArray(matrixArray, 10);
        testDelPosArray(matrixArray, 10);
        System.out.println("\nremove checking " + matrixArray.size());
        for (int i = 0; i < matrixArray.size(); i++) {  // test 1 should stay
            System.out.println(i + " " + matrixArray.get(i));
        }
        System.out.println("head " + matrixArray.get(0));
        System.out.println("tail " + matrixArray.get(matrixArray.size() - 1));
    }

    private void testAddArray(IArray data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 0; j < total; j ++)
            data.add(j + ". " + new Date());

        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start));
    }


    private void testAddPosArray(IArray data, int position, Object insert) {
        long start = System.currentTimeMillis();

        data.add(insert, position);
        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start));
    }

    private void testDelPosArray(IArray data, int position) {
        long start = System.currentTimeMillis();

        Object removed = data.remove(position);
        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start) + "; removed: " + removed);
    }


}
