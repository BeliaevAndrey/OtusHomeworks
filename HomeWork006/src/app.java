import sortings.BubbleSort;
import sortings.InsertionSort;
import util.ArrayGen;

import java.util.Arrays;

public class app {
    public static void main(String[] args) {

        BubbleSort bubble = new BubbleSort();
        InsertionSort insert = new InsertionSort();
        int[] arr1 = ArrayGen.setRandom(100_00);
        int[] arr2 = ArrayGen.setRandom(100_00);
        bubble.init(arr1);
        insert.init(arr2);

//        System.out.println(Arrays.toString(arr1));
        bubble.sort();
        System.out.println(isValid(arr1));
//        System.out.println(Arrays.toString(arr1));

//        System.out.println(Arrays.toString(arr2));
        insert.shiftBinSearchSort();
        System.out.println(isValid(arr2));
//        System.out.println(Arrays.toString(arr2));
    }

    static boolean isValid(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                System.out.println("i-1: " + (i - 1) + " i: " + i + " arr[i-1] " + arr[i - 1] + " arr[i] " + arr[i]);
                return false;
            }
        }
        return true;
    }
}
