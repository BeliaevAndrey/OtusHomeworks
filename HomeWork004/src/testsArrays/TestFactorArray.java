package testsArrays;

import model.FactorArray;
import model.IArray;
import model.SingleArray;

import java.util.Date;

public class TestFactorArray {

    public static void main(String[] args) {
        new TestFactorArray().testSingleArray();
    }

    void testSingleArray() {
        IArray factorArray = new FactorArray();
        testAddArray(factorArray, 10_000);
        System.out.println("\nadd testing");
        testAddPosArray(factorArray, 1000, "test 1");
        testAddPosArray(factorArray, 1000, "test 2");
        testAddPosArray(factorArray, factorArray.size(), "test tail");
        testAddPosArray(factorArray, 1000, "test 3");
        testAddPosArray(factorArray, 0, "test head");
        testAddPosArray(factorArray, 1001, "test 4");
        System.out.println("\nadd checking");
        for (int i = 999; i < 1010; i++) {
            System.out.println(i + " " + factorArray.get(i));
        }
        System.out.println(factorArray.get(0));
        System.out.println(factorArray.get(factorArray.size() - 1));

        System.out.println("\nremove testing");
        testDelPosArray(factorArray, 0);
        testDelPosArray(factorArray, 1000);
        testDelPosArray(factorArray, factorArray.size() - 1);
        testDelPosArray(factorArray, 1000);
        testDelPosArray(factorArray, 1000);
        System.out.println("\nremove checking " + factorArray.size());
        for (int i = 999; i < 1010; i++) {  // test 1 should stay
            System.out.println(i + " " + factorArray.get(i));
        }
        System.out.println("head " + factorArray.get(0));
        System.out.println("tail " + factorArray.get(factorArray.size() - 1));
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
