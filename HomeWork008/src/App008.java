import sortings.MergeSort;
import sortings.QuickSort;
import util.ArrayGen;

import java.util.Random;

public class App008 {

    public static void main(String[] args) {

        App008 app = new App008();

        MergeSort ms = new MergeSort();
        int len = (int) 1e8;
        int[] arr = ArrayGen.getArray(len, "Random");
        ms.init(arr);
        long startMS = System.nanoTime();
        ms.sort();
        double endMS = (System.nanoTime() - startMS) / 1e9;
        System.out.println(app.isAscending(arr) + " " + endMS);

        QuickSort qs = new QuickSort();
        arr = ArrayGen.getArray(len, "Random");
        qs.init(arr);
        long startQS = System.nanoTime();
        qs.sort();
        double endQS = (System.nanoTime() - startQS) / 1e9;
        System.out.println(app.isAscending(arr) + " " + endQS);


    }



    boolean isAscending(int[] arr) {
        boolean flag = true;
        for (int i = 1; i < arr.length; i++) {
            String format = "%5s%5s%12s%12s\n";
            if (arr[i - 1] > arr[i]) {
                if (flag) System.out.printf(format, "i - 1", "i", "arr[i-1]", "arr[i]");
                System.out.printf(format, (i - 1), i, arr[i - 1], arr[i]);
                flag = false;
            }
        }
        return flag;
    }
}
