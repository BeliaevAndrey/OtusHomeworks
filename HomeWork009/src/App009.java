import sortings.CountingSort;
import sortings.BucketSortArraysBinSearch;
import sortings.RadixSort;
import util.ArrayGen;

public class App009 {

    public static void main(String[] args) {
        int limit = (int) 1e8;
        int[] arr = ArrayGen.setReverse(limit);

        App009 app = new App009();

        CountingSort countingSort = new CountingSort();
        BucketSortArraysBinSearch bucketSort = new BucketSortArraysBinSearch();
        RadixSort radixSort = new RadixSort();

        System.out.println("CountingSort");
        countingSort.init(arr);
        long start = System.nanoTime();
        countingSort.sort();
        double end1 = (System.nanoTime() - start) / 1e9;
        System.out.println(app.isAscending(arr));
        System.out.println(end1);

        System.out.println("\nBucketSort");
        arr = ArrayGen.setReverse(limit);
        bucketSort.init(arr);
        start = System.nanoTime();
        bucketSort.sort();
        double end2 = (System.nanoTime() - start) / 1e9;
        System.out.println(app.isAscending(arr));
        System.out.println(end2);

        System.out.println("\nRadixSort");
        arr = ArrayGen.setReverse(limit);
        radixSort.init(arr);
        start = System.nanoTime();
        radixSort.sort();
        double end3 = (System.nanoTime() - start) / 1e9;
        System.out.println(app.isAscending(arr));
        System.out.println(end3);


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
