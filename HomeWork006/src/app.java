import sortings.BubbleSort;
import sortings.InsertionSort;
import util.ArrayGen;

import java.util.Arrays;

public class app {
    public static void main(String[] args) {

        BubbleSort bubble = new BubbleSort();
        InsertionSort insert = new InsertionSort();
        int[] arr1 = ArrayGen.setRandom(40);
        int[] arr2 = ArrayGen.setRandom(40);
        bubble.init(arr1);
        insert.init(arr2);

        System.out.println(Arrays.toString(arr1));
        bubble.sort();
        System.out.println(Arrays.toString(arr1));

        System.out.println(Arrays.toString(arr2));
        insert.shiftSort();
        System.out.println(Arrays.toString(arr2));
    }
}
