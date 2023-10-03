import java.util.Arrays;

import static util.shuffleArrayGen.setShuffle;

public class App010 {
    public static void main(String[] args) {
        BST bst1 = new BST();
//        BST bst2 = new BST();
        int length1 = (int) 1e2;
//        int length2 = (int) 1e6;

//        int[] arr1 = setShuffle(length1);
        int[] arr1 = new int[]{
                43, 17, 62, 60, 35, 2, 82, 9, 12, 71, 59, 27, 28, 40, 57, 5, 99, 7, 70, 23, 55, 42,
                32, 44, 79, 37, 36, 84, 22, 47, 72, 86, 91, 93, 58, 63, 74, 80, 6, 75, 94, 85, 33,
                10, 73, 39, 88, 64, 45, 98, 90, 50, 3, 0, 96, 15, 34, 66, 81, 48, 18, 54, 4, 19, 24,
                14, 16, 53, 49, 51, 87, 78, 1, 61, 38, 20, 41, 13, 69, 11, 56, 8, 31, 76, 52, 30, 83,
                77, 65, 68, 97, 46, 26, 67, 21, 92, 95, 89, 25, 29
        };
//        int[] arr2 = setShuffle(length1);

        bst1.init(arr1);
//        bst2.init(arr2);

        for (int key : arr1) {
            boolean out = bst1.search(key);
            if (!out) System.out.println(key);
        }

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(bst1.getSorted()));
        for (int x : arr1) {
            System.out.printf("\nx=%d\n", x);
            System.out.println(Arrays.toString(bst1.getSorted()));
            bst1.remove(x);
            System.out.println(Arrays.toString(bst1.getSorted()));
        }
    }
}
