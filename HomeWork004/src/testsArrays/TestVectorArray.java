package testsArrays;

import model.IArray;
import model.VectorArray;

import java.util.Date;

public class TestVectorArray {

    public static void main(String[] args) {
        new TestVectorArray().testSingleArray();
    }

    void testSingleArray() {
        IArray vectorArray = new VectorArray();
        testAddArray(vectorArray, 10_000);

        System.out.println("\nadd testing");
        testAddPosArray(vectorArray, 1000, "test 1");
        testAddPosArray(vectorArray, 1000, "test 2");
        testAddPosArray(vectorArray, vectorArray.size(), "test tail");
        testAddPosArray(vectorArray, 1000, "test 3");
        testAddPosArray(vectorArray, 0, "test head");
        testAddPosArray(vectorArray, 1001, "test 4");
        System.out.println("\nadd checking");
        for (int i = 999; i < 1010; i++) {
            System.out.println(i + " " + vectorArray.get(i));
        }
        System.out.println(vectorArray.get(0));
        System.out.println(vectorArray.get(vectorArray.size() - 1));

        System.out.println("\nremove testing");
        testDelPosArray(vectorArray, 0);
        testDelPosArray(vectorArray, 1000);
        testDelPosArray(vectorArray, vectorArray.size() - 1);
        testDelPosArray(vectorArray, 1000);
        testDelPosArray(vectorArray, 1000);
        System.out.println("\nremove checking " + vectorArray.size());
        for (int i = 999; i < 1010; i++) {  // test 1 should stay
            System.out.println(i + " " + vectorArray.get(i));
        }
        System.out.println("head " + vectorArray.get(0));
        System.out.println("tail " + vectorArray.get(vectorArray.size() - 1));
    }

    private void testAddArray(IArray data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 0; j < total; j ++)
            data.add(new Date());

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
