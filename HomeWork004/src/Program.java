import model.FactorArray;
import model.IArray;
import model.MatrixArray;
import model.SingleArray;

import java.util.Date;

public class Program {

    public static void main(String[] args) {
        IArray singleArray = new SingleArray();
        IArray vectorArray = new FactorArray();
        IArray factorArray = new FactorArray();
        IArray matrixArray = new MatrixArray();
        testAddArray(singleArray, 10_000);
        testAddArray(vectorArray, 100_000);
        testAddArray(factorArray, 100_000);
        testAddArray(matrixArray, 100_000);

        // HW testing
        System.out.println("\nadd testing");
        testAddPosArray(singleArray, 1000, "test 1");
        testAddPosArray(singleArray, 1000, "test 2");
        testAddPosArray(singleArray, singleArray.size(), "test tail");
        testAddPosArray(singleArray, 1000, "test 3");
        testAddPosArray(singleArray, 0, "test head");
        testAddPosArray(singleArray, 1001, "test 4");
        System.out.println("\nadd checking");
        for (int i = 999; i < 1010; i++) {
            System.out.println(i + " " + singleArray.get(i));
        }
        System.out.println(singleArray.get(0));
        System.out.println(singleArray.get(singleArray.size() - 1));

        System.out.println("\nremove testing");
        testDelPosArray(singleArray, 0);
        testDelPosArray(singleArray, 1000);
        testDelPosArray(singleArray, singleArray.size() - 1);
        testDelPosArray(singleArray, 1000);
        testDelPosArray(singleArray, 1000);
        System.out.println("\nremove checking " + singleArray.size());
        for (int i = 999; i < 1010; i++) {  // test 1 should stay
            System.out.println(i + " " + singleArray.get(i));
        }
        System.out.println("head " + singleArray.get(0));
        System.out.println("tail " + singleArray.get(singleArray.size() - 1));



    }

    private static void testAddArray(IArray data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 0; j < total; j ++)
            data.add(new Date());

        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start));
    }

    private static void testAddPosArray(IArray data, int position, Object insert) {
        long start = System.currentTimeMillis();

        data.add(insert, position);
        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start));
    }

    private static void testDelPosArray(IArray data, int position) {
        long start = System.currentTimeMillis();

        Object removed = data.remove(position);
        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start) + "; removed: " + removed);
    }

}
