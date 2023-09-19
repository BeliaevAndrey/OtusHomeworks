import sortings.BucketSortSeminar;
import sortings.CountingSort;
import sortings.RadixSort;
import util.ArrayGen;

public class App009 {

    public static void main(String[] args) {
        int length = (int) 1e7;
        int upperLim = 1000;
        int[] arr = ArrayGen.setRandom(length, upperLim);

        App009 app = new App009();

        BucketSortSeminar bucketSortList = new BucketSortSeminar();

        CountingSort countingSort = new CountingSort();
        RadixSort radixSort = new RadixSort();

        System.out.println("CountingSort");
        countingSort.init(arr);
        long start = System.nanoTime();
        countingSort.sort();
        double end1 = (System.nanoTime() - start) / 1e9;
        System.out.println(app.isAscending(arr));
        System.out.println(end1);

        System.out.println("\nBucketSortList");
        arr = ArrayGen.setRandom(length, upperLim);
        bucketSortList.init(arr);
        start = System.nanoTime();
        bucketSortList.Bucket();
        double end2 = (System.nanoTime() - start) / 1e9;
        System.out.println(app.isAscending(arr));
        System.out.println(end2);

        System.out.println("\nRadixSort");
        arr = ArrayGen.setRandom(length, upperLim);
        radixSort.init(arr);
        start = System.nanoTime();
        radixSort.sort();
        double end4 = (System.nanoTime() - start) / 1e9;
        System.out.println(app.isAscending(arr));
        System.out.println(end4);


    }

    boolean isAscending(int[] arr) {
        boolean flag = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                System.out.printf(
                        "(i-1): %d arr[i-1]: %d (i): %d arr[i]: %d\n",
                        i - 1, arr[i - 1], i, arr[i]);
                flag = false;
            }
        }
        return flag;
    }

}
