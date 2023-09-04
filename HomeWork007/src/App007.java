import sortings.HeapSort;
import sortings.SelectionSort;
import util.ArrayGen;

public class App007 {

    public static void main(String[] args) {
        int limit = (int) 1e5;
        int[] arr = ArrayGen.setReverse(limit);
        App007 app = new App007();
        SelectionSort sel = new SelectionSort();
        HeapSort heapSort = new HeapSort();
        sel.init(arr);
        long start = System.nanoTime();
        sel.sort();
        double end1 = (System.nanoTime() - start) / 1e9;
        System.out.println(app.isAscending(arr));
        System.out.println(end1);

        arr = ArrayGen.setReverse(limit);
        heapSort.init(arr);
        start = System.nanoTime();
        heapSort.sort();
        double end2 = (System.nanoTime() - start) / 1e9;
        System.out.println(app.isAscending(arr));
        System.out.println(end2);


    }

    boolean isAscending(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                System.out.printf("%d %d %d %d", i-1, arr[i-1], i, arr[i]);
                return false;
            }
        }
        return true;
    }

}
